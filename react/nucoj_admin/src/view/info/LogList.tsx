import React, {useEffect, useState} from 'react';
import Container from "../../component/container";
import {LogInfo} from "../../interface/log";
import axios from "axios";
import toast from "../../component/toast";
import {TableParams} from "../../interface/table";
import {TablePaginationConfig} from "antd/es/table";
import {FilterValue, SorterResult} from "antd/es/table/interface";
import {ArticleInfo} from "../../interface/article";
import LogTable from "../../component/table/LogTable";
import {SUCCESS} from "../../constant/controller";

/**
 * 所有日志
 * @constructor
 */
function LogList() {
    const [data, setData] = useState<LogInfo[]>([]);
    const [tableParams, setTableParams] = useState<TableParams>({
        pagination: {
            current: 1,
            pageSize: 10,
        },
    });

    const getData = () => {
        axios({
            url: '/api/log/list',
            method: 'post',
            data: {
                current: tableParams.pagination?.current,
                size: tableParams.pagination?.pageSize
            },
            timeout: 2000
        }).then(res => {
            if (res.data.code === SUCCESS) {
                setData(res.data.data.list)
                setTableParams({
                    ...tableParams,
                    pagination: {
                        ...tableParams.pagination,
                        total: res.data.data.total
                    },
                });
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

    const del = (id:string) => {
        axios({
            url: '/api/log/delete',
            method: 'post',
            data: {
                id: id
            },
            timeout: 2000
        }).then(res => {
            toast('success', '删除成功')
        })
        getData()
    }

    const mergedDate = data.map(col => {
        return {
            ...col,
            key: col.id
        }
    })

    // 这里当时遇到一个问题， 会不断更新
    // 不清楚发生了什么， 修改后没有出错
    useEffect(() => {
        getData()
    // }, [JSON.stringify(tableParams)])
    }, [tableParams.pagination?.current, tableParams.pagination?.pageSize])

    return (
        <Container style={{margin: '20px 0 0 20px'}} title={'日志'} width={'96%'}>
            <LogTable
                data={mergedDate}
                tableParams={tableParams}
                setTableParams={setTableParams}
                handleTableChange={handleTableChange}
                del={del}
            />
        </Container>
    );
}
export default LogList