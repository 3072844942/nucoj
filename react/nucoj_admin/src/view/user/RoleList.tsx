import React, {useEffect, useState} from 'react';
import {connect} from "react-redux";
import Container from "../../component/container";
import {RoleInfo, UserInfo} from "../../interface/user";
import {TableParams} from "../../interface/table";
import axios from "axios";
import {TablePaginationConfig} from "antd/es/table";
import {FilterValue, SorterResult} from "antd/es/table/interface";
import {ArticleInfo} from "../../interface/article";
import RoleTable from "../../component/table/RoleTable";

// todo 树形控件应该怎么修改
// https://ant.design/components/tree-cn/
// 现在的想法是：
// 1. 利用两个bool控制菜单和资源的显示和隐藏
// 2. 打开时传入角色名获取信息
// 3. 树形控件写死在代码中
// 4. 更新时重新创建一颗树
function RoleList() {
    const [data, setData] = useState<RoleInfo[]>([]);
    const [tableParams, setTableParams] = useState<TableParams>({
        pagination: {
            current: 1,
            pageSize: 10,
        },
    });

    const getData = () => {
        axios({
            url: '/api/role/list',
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
        <Container style={{margin: '10px 0 0 20px'}} title={'角色列表'} width={'96%'}>
            <RoleTable
                data={data}
                tableParams={tableParams}
                setTableParams={setTableParams}
                handleTableChange={handleTableChange}
            />
        </Container>
    );
}

export default RoleList