import React, {useEffect, useState} from 'react';
import {ContestInfo} from "../../interface/contest";
import {TableParams} from "../../interface/table";
import axios from "axios";
import {SUCCESS} from "../../constant/controller";
import toast from "../../component/toast";
import {TablePaginationConfig} from "antd/es/table";
import {FilterValue, SorterResult} from "antd/es/table/interface";
import {ArticleInfo} from "../../interface/article";
import Container from "../../component/container";
import ContestTable from "../../component/table/ContestTable";

/**
 * 所有比赛
 * @constructor
 */
function ContestList() {
    const [data, setData] = useState<ContestInfo[]>([])
    const [tableParams, setTableParams] = useState<TableParams>({
        pagination: {
            current: 1,
            pageSize: 10,
        },
    });

    const getData = () => {
        axios({
            url: '/api/contest/list',
            method: 'post',
            data: {
                current: tableParams.pagination?.current,
                size: tableParams.pagination?.pageSize
            },
            timeout: 2000
        }).then(res => {
            setData(res.data.data.list)
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

    const deleteContest = (id: string) => {
        axios({
            url: '/api/contest/delete',
            method: 'post',
            data: {
                id: id
            },
            timeout: 2000
        }).then(res => {
            if (res.data.code === SUCCESS) {
                toast('success', '删除成功')
                getData()
            }
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
        getData()
    }, [JSON.stringify(tableParams)])

    return (
        <Container style={{margin: '10px 0 0 20px'}} title={'比赛列表'} width={'96%'}>
            <ContestTable data={data} tableParams={tableParams} setTableParams={setTableParams}
                          handleTableChange={handleTableChange} deleteContest={deleteContest}/>
        </Container>
    );
}

export default ContestList;
