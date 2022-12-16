import React, {useEffect, useState} from 'react';
import Container from "../../component/container";
import {ProblemInfo} from "../../interface/problem";
import axios from "axios";
import {TableParams} from "../../interface/table";
import {SUCCESS} from "../../constant/controller";
import toast from "../../component/toast";
import {TablePaginationConfig} from "antd/es/table";
import {FilterValue, SorterResult} from "antd/es/table/interface";
import {ArticleInfo} from "../../interface/article";
import ProblemTable from "../../component/table/ProblemTable";

/**
 * 所有题目
 * @param props
 * @constructor
 */
function ProblemList() {
    const [data, setData] = useState<ProblemInfo[]>([]);
    const [tableParams, setTableParams] = useState<TableParams>({
        pagination: {
            current: 1,
            pageSize: 10,
        },
    });

    const getData = () => {
        axios({
            url: '/api/admin/problem/list',
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

    const deleteProblem = (id: string) => {
        axios({
            url: '/api/problem/delete',
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
        <Container style={{margin: '10px 0 0 20px'}} title={'题目列表'} width={'96%'}>
            <ProblemTable data={data} tableParams={tableParams} url={'/problem/'}
                          handleTableChange={handleTableChange} deleteProblem={deleteProblem}/>
        </Container>
    );
}

export default ProblemList