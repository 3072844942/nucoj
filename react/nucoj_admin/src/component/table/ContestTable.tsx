import React from 'react';
import {Button, Popconfirm, Table} from "antd";
import {NavLink} from "react-router-dom";
import {ContestInfo} from "../../interface/contest";
import {getDate, getTime} from "../../util";
import {ProblemInfo} from "../../interface/problem";
import {TableParams} from "../../interface/table";

/**
 * 主页最近比赛表格
 * @constructor
 */
function ContestTable(props:{
    data:ContestInfo[],
    tableParams: TableParams,
    setTableParams: Function,
    handleTableChange: Function,
    deleteContest: Function
}) {
    return (
        <Table<ContestInfo> dataSource={props.data} sticky={true} bordered={true} pagination={props.tableParams.pagination}
            // @ts-ignore
            onChange={props.handleTableChange} >
            <Table.Column<ContestInfo> key="id" title='编号' dataIndex="id" align={'center'}/>
            <Table.Column<ContestInfo> key="title" title="名称" dataIndex="title" align={'center'}/>
            <Table.Column<ContestInfo> key="startTime" title="开始时间" dataIndex="startTime" align={'center'}
                                       render={text => getDate(text)}/>
            <Table.Column<ContestInfo> key="time" title="时长" dataIndex="time" align={'center'}
                                       render={text => getTime(text)}/>
            <Table.Column<ContestInfo> key="author" title="发布人" dataIndex="author" align={'center'}/>
            <Table.Column key={'operation'} dataIndex={'operation'} align={'center'} title={'操作'} width={'15%'}
                          render={(_, record:ProblemInfo) => <div key={record.id} style={{
                              display: 'flex',
                              alignItems: 'center',
                              justifyContent: 'space-between'
                          }}>
                              <NavLink to={'/contest/' + record.id}>
                                  <Button type="primary">编辑</Button>
                              </NavLink>
                              <Popconfirm title="确定删除?"
                                          onConfirm={() => props.deleteContest(record.id)}>
                                  <Button type="dashed" danger>
                                      删除
                                  </Button>
                              </Popconfirm>
                          </div>}></Table.Column>
        </Table>
    );
}

export default ContestTable;