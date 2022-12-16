import React, {useEffect, useState} from 'react';
import {ColumnsType} from "antd/es/table";
import {Table} from "antd";
import axios from "axios";
import {NavLink} from "react-router-dom";
import {NoticeInfo} from "../../interface/notice";
import {getDate} from "../../util";

/**
 * 公告表格
 * @constructor
 */
function NoticeTable(props:{data:NoticeInfo[]}) {
    return (
        <Table<NoticeInfo> dataSource={props.data} pagination={false}>
            <Table.Column<NoticeInfo>
                key="title"
                title='标题'
                dataIndex="title"
                align={'center'}
                render={(text:string, record: NoticeInfo) => <NavLink to={'/notice/' + record.id}>{text}</NavLink>}
            />
            <Table.Column<NoticeInfo> key="time" title="发布时间" dataIndex="time" align={'center'}
                render={text => getDate(text)}/>
            <Table.Column<NoticeInfo> key="author" title="发布者" dataIndex="author" align={'center'}/>
        </Table>
    );
}

export default NoticeTable;