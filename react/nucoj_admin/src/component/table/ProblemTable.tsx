import React from 'react';
import {TableParams} from "../../interface/table";
import {ProblemInfo} from "../../interface/problem";
import {Button, Popconfirm, Table} from "antd";
import ErrorIcon from "../../assets/icon/ErrorIcon";
import {getDate} from "../../util";
import CorrectIcon from "../../assets/icon/CorrectIcon";
import {Tag} from 'antd';
import {NavLink} from "react-router-dom";

/**
 * 题目信息表格
 * @param props
 * @constructor
 */
function ProblemTable(props: {
    data: ProblemInfo[],
    tableParams?: TableParams
    handleTableChange?: Function,
    deleteProblem: Function,
    url: string
}) {
    return (
        <Table dataSource={props.data} sticky={true} bordered={true} pagination={props.tableParams?.pagination}
            // @ts-ignore
               onChange={props.handleTableChange}>
            <Table.Column key={'id'} dataIndex={'id'} align={'center'} title={'编号'}/>
            <Table.Column key={'title'} dataIndex={'title'} align={'center'} title={'标题'}/>
            <Table.Column key={'time'} dataIndex={'time'} align={'center'} title={'创建时间'}
                          render={text => getDate(text)}/>
            <Table.Column key={'grade'} dataIndex={'grade'} align={'center'} title={'等级'}
                          render={text => <Tag color="blue">{text}</Tag>}/>
            <Table.Column key={'tags'} dataIndex={'tags'} align={'center'} title={'标签'}
                          render={text => text.map((item: string) => <span key={item} style={{marginRight: '2px'}}><Tag color="purple">{item}</Tag></span>)}/>
            <Table.Column key={'recordId'} dataIndex={'recordId'} align={'center'} title={'测试数据地址'}
                          render={text => 'https://www.static.snak.space/' + text + '/'}/>
            <Table.Column key={'isSpecial'} dataIndex={'isSpecial'} align={'center'} title={'特别判断'}
                          render={text => text === true ? <CorrectIcon/> : <ErrorIcon/>}/>
            <Table.Column key={'operation'} dataIndex={'operation'} align={'center'} title={'操作'} width={'15%'}
                          render={(_, record:ProblemInfo) => <div key={record.id} style={{
                              display: 'flex',
                              alignItems: 'center',
                              justifyContent: 'space-between'
                          }}>
                              <NavLink to={props.url + record.id}>
                                  <Button type="primary">编辑</Button>
                              </NavLink>
                              <Popconfirm title="确定删除?"
                                          onConfirm={() => props.deleteProblem(record.id)}>
                                  <Button type="dashed" danger>
                                      删除
                                  </Button>
                              </Popconfirm>
                          </div>}></Table.Column>
        </Table>
    );
}

export default ProblemTable;