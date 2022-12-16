import React, {useEffect, useState} from 'react';
import {connect} from "react-redux";
import {ContestInfo} from "../../interface/contest";
import {now} from "lodash";
import {useLocation, useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import Container from "../../component/container";
import Title from "../../component/title/Title";
import {Input, DatePicker, Button} from "antd";
import {getDate} from "../../util";
import type {DatePickerProps, RangePickerProps} from 'antd/es/date-picker';
import ProblemTable from "../../component/table/ProblemTable";
import {SUCCESS} from "../../constant/controller";
import toast from "../../component/toast";
import CorrectIcon from "../../assets/icon/CorrectIcon";
import ErrorIcon from "../../assets/icon/ErrorIcon";
import {UserInfo} from "../../interface/user";
import {TableParams} from "../../interface/table";
import {TablePaginationConfig} from "antd/es/table";
import {FilterValue, SorterResult} from "antd/es/table/interface";
import {ArticleInfo} from "../../interface/article";
import UserTable from "../../component/table/UserTable";
import moment from "moment"
import Editor from "../../component/editor/Editor";

const {RangePicker} = DatePicker;

/**
 * 创建/修改 比赛页面
 * @param props
 * @constructor
 */
function Contest(props: any) {
    const params = useParams()
    const history = useNavigate()
    const [data, setData] = useState<ContestInfo>({
        id: '',
        title: '',
        startTime: now(),
        context: '',
        time: 1000 * 60 * 60 * 5,
        author: props.userId,
        problems: []
    })
    const [context, setContext] = useState('')
    const [entryList, setEntryList] = useState<UserInfo[]>([])
    const [tableParams, setTableParams] = useState<TableParams>({
        pagination: {
            current: 1,
            pageSize: 10,
        },
    });

    const onOk = (value: DatePickerProps['value'] | RangePickerProps['value']) => {
        // 是给了初始值的， 所以也不会出现undefined现象
        setData({
            ...data,// @ts-ignore
            startTime: (value[0] === null || value[0] === undefined) ? now() : value[0].valueOf(),// @ts-ignore
            time: (value[1] === null || value[1] === undefined) ? 60 * 60 * 5 : value[1].valueOf() - value[0].valueOf()
        })
    };

    const getData = () => {
        axios({
            url: '/api/contest/entry/user/list',
            method: 'post',
            data: {
                contestId: params.contestId,
                current: tableParams.pagination?.current,
                size: tableParams.pagination?.pageSize
            },
            timeout: 2000
        }).then(res => {
            setEntryList(res.data.data.list)
            setTableParams({
                ...tableParams,
                pagination: {
                    ...tableParams.pagination,
                    total: res.data.data.total,
                    // 200 is mock data, you should read it from server
                    // total: data.totalCount,
                },
            });
        })
    }

    const handleTableChange = (
        pagination: TablePaginationConfig,
        filters: Record<string, FilterValue>,
        sorter: SorterResult<ArticleInfo>
    ) => {
        setTableParams({
            pagination,
            filters,
            ...sorter,
        });
    };

    useEffect(() => {
        setData({
            ...data,
            context: context
        })
    }, [context])

    useEffect(() => {
        if (params.contestId) getData()
    }, [JSON.stringify(tableParams)])

    const deleteProblem = (id: string) => {
        axios({
            url: '/api/contest/problem/delete',
            method: 'post',
            data: {
                id: id,
                contestId: params.contestId
            }
        }).then(res => {
            if (res.data.code === SUCCESS) toast('success', '', <CorrectIcon/>)
        })
    }

    useEffect(() => {
        if (params.contestId !== undefined) {
            axios({
                url: '/api/admin/contest',
                method: 'post',
                data: {
                    id: params.contestId
                },
                timeout: 2000
            }).then(res => {
                setData(res.data.data)
            })
        }
    }, [])

    return (
        <div>
            <Container title={'添加比赛'} width={'96%'} style={{margin: '10px 0 0 20px'}}>
                <Title>标题</Title>
                <Input placeholder={getDate(now())} value={data.title}
                       onChange={(e) => setData({...data, title: e.target.value})}></Input>
                <Title>时间</Title>
                <RangePicker
                    showTime={{format: 'HH:mm:ss'}}
                    format="YYYY-MM-DD HH:mm:ss"
                    onOk={onOk}
                    value={[
                        moment(moment(data.startTime),"YYYY-MM-DD HH:mm:ss"),
                        moment(moment(data.startTime + data.time),"YYYY-MM-DD HH:mm:ss")
                    ]}
                />
                <Title>比赛描述</Title>
                <Editor editorId={'default'} text={context} setText={setContext}/>
                {
                    data.id !== '' ? <div>
                        <Title>
                            题目列表
                            <Button style={{marginLeft: '86%'}}
                                    onClick={() => history('/contest/' + data.id + '/problem/add')}>添加</Button>
                        </Title>
                        <ProblemTable url={'/contest/' + params.contestId + '/problem/'} data={data.problems}
                                      deleteProblem={deleteProblem}></ProblemTable>
                    </div> : null
                }
                <div>
                    <Button
                        style={{
                            margin: '2vh 0'
                        }}
                        type={'primary'}
                        onClick={() => {
                            axios({
                                url: '/api/contest/update',
                                method: 'post',
                                data: {
                                    ...data
                                }
                            }).then(res => {
                                if (res.data.code === SUCCESS) {
                                    if (data.title === '') toast('fail', '请输入比赛标题', <ErrorIcon/>)
                                    else {
                                        toast('success', '', <CorrectIcon/>)
                                        history('/contest/list')
                                    }
                                }
                                else toast('fail', res.data.message, <ErrorIcon/>)
                            })
                        }}
                    >创建完成</Button>
                </div>
            </Container>
            {
                data.id !== '' ?<Container title={'报名人员'} width={'96%'} style={{margin: '10px 0 20px 20px'}}>
                    <UserTable
                        data={entryList}
                        tableParams={tableParams}
                        setTableParams={setTableParams}
                        handleTableChange={handleTableChange}
                        modify={false}
                    />
                </Container> : null
            }
        </div>
    );
}

const mapStateToProps = (status:any) => {
    return {
        userId: status.UserInfo.id
    }
}

export default connect(mapStateToProps)(Contest);