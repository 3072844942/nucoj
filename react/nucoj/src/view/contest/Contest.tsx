import React, {useEffect, useState} from 'react';
import {ContestInfo} from "../../interface/contest";
import axios from "axios";
import {NavLink, useLocation, useNavigate, useParams} from "react-router-dom";
import {connect} from "react-redux";
import BG from '../../assets/img/contest.png'
import {getDate, markdownToHtml} from "../../util";
import {Button, Card, Tabs} from "antd";
import dayjs from "dayjs";
import moment from "moment";
import {SUCCESS} from "../../constant/controller";
import toast from "../../component/toast";
import ErrorIcon from "../../assets/icon/ErrorIcon";
import ContestContainer from "../../component/container/ContestContainer";

/**
 * 比赛详情
 * @constructor
 */
function Contest(props: { userId: string }) {
    const [data, setData] = useState<ContestInfo>();
    const [time, setTime] = useState(0);
    const params = useParams();
    const history = useNavigate()

    const getData = () => {
        axios({
            url: '/api/contest/' + params.contestId,
            method: 'post',
            data: {
                id: props.userId
            }
        }).then(res => {
            setData(res.data.data)
            if (res.data.data.startTime > moment().utc().valueOf() + 32 * 60 * 60 * 1000)
                setTime(res.data.data.startTime - moment().utc().valueOf() - 32 * 60 * 60 * 1000)
        })
    }

    useEffect(() => {
        document.title = '比赛详情'
    }, [])

    useEffect(() => {
        getData()
    }, [props.userId])

    useEffect(() => {
        if (time > -32 * 60 * 60 * 1000) {
            const timer = setTimeout(() => {
                let newTime = time - 1000
                if (newTime < -32 * 60 * 59 * 1000) {
                    getData()
                }
                setTime(newTime)
                clearTimeout(timer)
            }, 1000)
        }
    }, [time])

    return (
        <div style={{
            width: '100vw'
        }}>
            {/*上半部分显示标题*/}
            <div style={{
                width: '100vw',
                height: '36vh',
                background: 'url(' + BG + ') center center / cover no-repeat',
                paddingLeft: '16vw',
                paddingTop: '2vh'
            }}>
                <div style={{position: 'absolute', top: 0, left: 0, color: 'white'}}>
                    <Button type={'link'} ghost onClick={() => history(-1)}>返回</Button>
                </div>
                <div>
                    <div style={{color: 'white', fontSize: '5em'}}>{data?.title}</div>

                    <div style={{color: 'white', fontSize: '1em'}}>{getDate(data?.startTime)} 至
                        {// @ts-ignore
                            getDate(data?.time + data?.startTime)}</div>
                    <div style={{color: 'white', fontSize: '1em'}}>报名人数： {data?.number}</div>
                </div>
            </div>
            {/*中间有个时间倒计时*/}
            {
                data?.startTime !== undefined && moment().utc().valueOf() < data?.startTime ? <div style={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    marginTop: '5vh'
                }}>
                    <div style={{width: '34vw', height: '32vh', textAlign: 'center'}}>
                        <p style={{fontSize: '1.1em'}}>距离比赛开始还有</p>
                        <div style={{
                            display: 'flex',
                            alignItems: 'center',
                            justifyContent: 'space-between',
                            margin: '1vh 0',
                            userSelect: "none"
                        }}>
                            <div style={{
                                backgroundColor: '#c6c6c6',
                                width: '8vw',
                                height: '20vh',
                                borderRadius: '10%',
                                textAlign: 'center',
                                position: 'relative'
                            }}>
                                <p style={{color: 'white', fontSize: '500%'}}>{moment(time).date()}</p>
                                <p style={{color: 'white', position: 'relative', bottom: '9vh'}}>天</p>
                            </div>
                            <div style={{
                                backgroundColor: '#c6c6c6',
                                width: '8vw',
                                height: '20vh',
                                borderRadius: '10%',
                                textAlign: 'center',
                                position: 'relative'
                            }}>
                                <p style={{color: 'white', fontSize: '500%'}}>{moment(time).hour()}</p>
                                <p style={{color: 'white', position: 'relative', bottom: '9vh'}}>时</p>
                            </div>
                            <div style={{
                                backgroundColor: '#c6c6c6',
                                width: '8vw',
                                height: '20vh',
                                borderRadius: '10%',
                                textAlign: 'center',
                                position: 'relative'
                            }}>
                                <p style={{color: 'white', fontSize: '500%'}}>{moment(time).minute()}</p>
                                <p style={{color: 'white', position: 'relative', bottom: '9vh'}}>分</p>
                            </div>
                            <div style={{
                                backgroundColor: '#c6c6c6',
                                width: '8vw',
                                height: '20vh',
                                borderRadius: '10%',
                                textAlign: 'center',
                                position: 'relative'
                            }}>
                                <p style={{color: 'white', fontSize: '500%'}}>{moment(time).second()}</p>
                                <p style={{color: 'white', position: 'relative', bottom: '9vh'}}>秒</p>
                            </div>
                        </div>
                        {
                            data !== undefined && data.entry
                                ? <Button type="dashed" block disabled>已报名</Button>
                                : <Button type="primary" block onClick={() => {
                                    if (props.userId === '') {
                                        toast('fail', '请先登录')
                                        return
                                    }
                                    axios({
                                        url: '/api/contest/entry',
                                        method: 'post',
                                        data: {
                                            id: props.userId,
                                            contestId: params.contestId
                                        }
                                    }).then(res => {
                                        if (res.data.code === SUCCESS) toast('success', '')
                                        else toast('fail', res.data.message, <ErrorIcon/>)
                                    })
                                }}>点击加入</Button>
                        }
                    </div>
                </div> : null
            }
            {/*下半部分主显示*/}
            <div style={{marginTop: '3vh'}}>
                {
                    data !== undefined ? moment().utc().valueOf() < data?.startTime ? <div
                        dangerouslySetInnerHTML={{__html: markdownToHtml(data?.context)}}
                        style={{
                            width: '76vw',
                            margin: '0 auto',
                            padding: '1vh 1vw',
                            borderRadius: '10px',
                            backgroundColor: '#EEE'
                        }}
                    ></div> : <div style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>

                        <ContestContainer // @ts-ignore
                            contestId={params.contestId} style={{width: '76vw'}} data={data}/>
                    </div> : null
                }
            </div>

        </div>
    );
}

const mapStatusToProps = (status: any) => {
    return {
        userId: status.UserInfo.id
    }
}

export default connect(mapStatusToProps)(Contest);