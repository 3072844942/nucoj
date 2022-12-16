import React from 'react';
import {RoleInfo} from "../../interface/user";
import {Button, Table, Tag} from "antd";
import {TableParams} from "../../interface/table";
import {connect} from "react-redux";
import toast from "../toast";
import ErrorIcon from "../../assets/icon/ErrorIcon";

/**
 * 角色表格
 * 未来要在这里完成权限的管理， 但是找不到好的管理方案。。
 * （不太会写
 * @param props
 * @constructor
 */
function RoleTable(props: {
    data: RoleInfo[],
    tableParams: TableParams,
    setTableParams: Function,
    handleTableChange: Function
}) {
    return (
        <Table dataSource={props.data} sticky={true} bordered={true} pagination={props.tableParams.pagination}
            // @ts-ignore
               onChange={props.handleTableChange}>
            <Table.Column key={'name'} title={'角色名'} align={'center'} dataIndex={'role'}
                render={text => <Tag color="green">{text}</Tag>}></Table.Column>
            <Table.Column key={'operation'} title={'操作'} align={'center'} dataIndex={'operation'} width={300}
                render={text=> <div style={{display: 'flex', justifyContent: 'space-around'}}>
                    <Button onClick={() => {
                        toast('菜单权限修改', '待完成', <ErrorIcon/>)
                    }}>菜单权限</Button>
                    <Button onClick={() => {
                                toast('资源权限修改', '待完成', <ErrorIcon/>)
                    }}>资源权限</Button>
                </div>}></Table.Column>
        </Table>
    );
}

export default RoleTable;