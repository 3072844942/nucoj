import React, {useEffect, useState} from 'react';
import Container from "../../component/container";
import {UserInfo} from "../../interface/user";
import axios from "axios";
import {TableParams} from "../../interface/table";
import {TablePaginationConfig} from "antd/es/table";
import {FilterValue, SorterResult} from "antd/es/table/interface";
import {ArticleInfo} from "../../interface/article";
import UserTable from "../../component/table/UserTable";
import {SUCCESS} from "../../constant/controller";
import toast from "../../component/toast";

/**
 * 所有用户
 * @constructor
 */
function UserList() {
    const [data, setData] = useState<UserInfo[]>([]);
    const [tableParams, setTableParams] = useState<TableParams>({
        pagination: {
            current: 1,
            pageSize: 10,
        },
    });

    const getData = () => {
        axios({
            url: '/api/user/list',
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

    const del = (id: string) => {
        axios({
            url: '/api/user/delete',
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

    const updateRole = (id: string, role: number) => {
        axios({
            url: '/api/user/role/update',
            method: 'post',
            data: {
                id: id,
                role: role
            }
        }).then(res =>{
            if (res.data.code === SUCCESS) toast('success', '修改成功')
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
        <Container style={{margin: '10px 0 0 20px'}} title={'用户列表'} width={'96%'}>
            <UserTable
                data={data}
                tableParams={tableParams}
                setTableParams={setTableParams}
                handleTableChange={handleTableChange}
                del={del}
                updateRole={updateRole}
                modify
            />
        </Container>
    );
}

export default UserList