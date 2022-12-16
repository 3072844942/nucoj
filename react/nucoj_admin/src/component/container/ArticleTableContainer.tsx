import React, {useEffect, useState} from 'react';
import {ArticleInfo} from "../../interface/article";
import {TableParams} from "../../interface/table";
import axios from "axios";
import {SUCCESS} from "../../constant/controller";
import {TablePaginationConfig} from "antd/es/table";
import {FilterValue, SorterResult} from "antd/es/table/interface";
import toast from "../toast";
import CorrectIcon from "../../assets/icon/CorrectIcon";
import ErrorIcon from "../../assets/icon/ErrorIcon";
import ArtilceTable from "../table/ArtilceTable";

/**
 * 公告/题解/分享表格容器
 * 因为三个基本一致
 * @param props
 * @constructor
 */
function ArticleTableContainer(props: {url: string, userId?:string}) {
    const [data, setData] = useState<ArticleInfo[]>([])
    const [tableParams, setTableParams] = useState<TableParams>({
        pagination: {
            current: 1,
            pageSize: 10,
        },
    });

    const getData = () => {
        axios({
            url: '/api/' + props.url + '/list',
            method: 'post',
            data: {
                current: tableParams.pagination?.current,
                size: tableParams.pagination?.pageSize,
                id: props.userId
            },
            timeout: 2000
        }).then(res => {
            if (res.data.code === SUCCESS) {
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

    const deleteArticle = (id: string | undefined) => {
        if (id === undefined) return
        axios({
            url: '/api/' + props.url + '/delete',
            method: 'post',
            data: {
                id: id
            },
            timeout: 2000,
        }).then(res => {
            if (res.data.code === SUCCESS) {
                toast('success', '删除成功', <CorrectIcon/>)
                getData()
            }
            else toast('fail', '删除失败', <ErrorIcon/>)
        })
    }

    useEffect(() => {
        getData()
    }, [JSON.stringify(tableParams)])

    return (
        <ArtilceTable
            url={props.url}
            data={data}
            tableParams={tableParams}
            setTableParams={setTableParams}
            deleteArticle={deleteArticle}
            handleTableChange={handleTableChange}
        ></ArtilceTable>
    );
}

export default ArticleTableContainer;