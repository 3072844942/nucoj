import React from 'react';
import {Popconfirm, Table} from "antd";
import {ArticleInfo} from "../../interface/article";
import {NavLink} from "react-router-dom";
import {getDate} from "../../util";
import {TableParams} from "../../interface/table";
import {Button, Space} from 'antd';

/**
 * 公告/题解/分享 表格
 * @param props
 * @constructor
 */
function ArticleTable(props: {
    url: string,
    data: ArticleInfo[],
    tableParams: TableParams,
    setTableParams: Function,
    deleteArticle: Function,
    handleTableChange: Function
}) {
    return (
        // 这里的onChango有一个参数错误， 但是官网就是这么写的
        // https://ant.design/components/table-cn/#components-table-demo-ajax 远程加载数据
        <Table dataSource={props.data} sticky={true} bordered={true} pagination={props.tableParams.pagination}
            // @ts-ignore
               onChange={props.handleTableChange}>
            <Table.Column<ArticleInfo> key="id" title='编号' dataIndex="id" align={'center'} width={'10%'}/>
            <Table.Column<ArticleInfo> key="title" title="标题" dataIndex="title" align={'center'}/>
            <Table.Column<ArticleInfo> key="time" title="发布时间" dataIndex="time" align={'center'}
                                       render={text => getDate(text)}/>
            <Table.Column<ArticleInfo> key="author" title="作者" dataIndex="author" align={'center'} width={'10%'}/>
            <Table.Column<ArticleInfo> key="#" title="#" dataIndex="#" align={'center'} width={'15%'}
                                       render={(text: any, record: ArticleInfo) => <div style={{
                                           display: 'flex',
                                           alignItems: 'center',
                                           justifyContent: 'space-between'
                                       }}>
                                           <NavLink to={'/' + props.url + '/' + record.id}>
                                               <Button type="primary">编辑</Button>
                                           </NavLink>
                                           <Popconfirm title="确定删除?"
                                                       onConfirm={() => props.deleteArticle(record.id)}>
                                               <Button type="dashed" danger>
                                                   删除
                                               </Button>
                                           </Popconfirm>
                                       </div>}/>
        </Table>
    );
}

export default ArticleTable;