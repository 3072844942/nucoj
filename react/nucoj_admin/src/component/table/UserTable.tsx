import React from 'react';
import {UserInfo} from "../../interface/user";
import {Button, Popconfirm, Select, Table, Tag, Typography} from "antd";
import {TableParams} from "../../interface/table";
import {connect} from "react-redux";

/**
 * 角色表格
 * @param props
 * @constructor
 */
function UserTable(props: any) {
    const getRole = (role: number, userId: string, modify: boolean) => {
        if (modify) {
            return <Select
                disabled={role <= props.role}
                defaultValue={role === 0 ? 'root' : role === 1 ? 'manager' : role === 2 ? 'member' : 'user'}
                style={{width: 100}}
                onChange={(value) => {
                    props.updateRole(userId, value)
                }}
                options={[
                    {
                        value: 0,
                        disabled: true,
                        label: 'root',
                    },
                    {
                        value: 1,
                        label: 'manager',
                    },
                    {
                        value: 2,
                        label: 'member',
                    },
                    {
                        value: 3,
                        label: 'user',
                    },
                ]}
            />
        }
        else {
            return <Tag color={role === 0 ? '#f50' : role === 1 ? '#2db7f5' : role === 2 ? '#87d068' : '#108ee9'}>
                {role === 0 ? 'root' : role === 1 ? 'manager' : role === 2 ? 'member' : 'user'}
            </Tag>
        }
    }

    return (
        <Table dataSource={props.data} sticky={true} bordered={true} pagination={props.tableParams.pagination}
            // @ts-ignore
               onChange={props.handleTableChange}>
            <Table.Column<UserInfo> key="avatar" dataIndex={"avatar"} title={"头像"} align={'center'} width={'100px'}
                                    render={text => <img width={32} height={32} src={text}></img>}></Table.Column>
            <Table.Column<UserInfo> key="nickname" title={"昵称"} dataIndex={"nickname"}
                                    align={'center'}></Table.Column>
            <Table.Column<UserInfo> key="role" title={"角色"} dataIndex={"role"} align={'center'}
                                    render={(text: number, record: UserInfo) => getRole(text, record.id, props.modify)}></Table.Column>
            <Table.Column<UserInfo> key="number" title={"学号"} dataIndex={"number"} align={'center'}></Table.Column>
            <Table.Column<UserInfo> key="name" title={"姓名"} dataIndex={"name"} align={'center'}></Table.Column>
            <Table.Column<UserInfo> key="college" title={"所属学院"} dataIndex={"college"}
                                    align={'center'}></Table.Column>
            <Table.Column<UserInfo> key="qq" title={"QQ"} dataIndex={["contact", "qq"]} align={'center'}></Table.Column>
            <Table.Column<UserInfo> key="blog" title={"Blog"} dataIndex={["contact", "blog"]}
                                    align={'center'}></Table.Column>
            <Table.Column<UserInfo> key="github" title={"Github"} dataIndex={["contact", "github"]}
                                    align={'center'}></Table.Column>
            {
                props.modify ? <Table.Column<UserInfo> key={'option'} title={'操作'} dataIndex={''} align={'center'}
                                                       render={(_, record) => <Typography.Link disabled={record.role <= props.role} style={{marginRight: 12}}>
                                                           <Popconfirm title="确定删除?"
                                                                       onConfirm={() => props.del(record.id)}>
                                                               <Button type="text" disabled={record.role <= props.role}>删除</Button>
                                                           </Popconfirm>
                                                       </Typography.Link>}></Table.Column> : null
            }
        </Table>
    );
}

const mapStateToProps = (status: any, preProps: {
    data: UserInfo[],
    tableParams: TableParams,
    setTableParams: Function,
    handleTableChange: Function,
    modify: boolean
}) => {
    return {
        ...preProps,
        role: status.UserInfo.role
    }
}

const mapDispatchToProps = {}

export default connect(mapStateToProps, mapDispatchToProps)(UserTable);