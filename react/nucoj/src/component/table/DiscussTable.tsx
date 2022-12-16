import React from 'react';
import {DiscussInfo} from "../../interface/discuss";
import {Table} from "antd";
import {NavLink} from "react-router-dom";
import {SolutionInfo} from "../../interface/solution";
import {getDate} from "../../util";

function DiscussTable(props:{data:DiscussInfo[]}) {
    return (
        <Table<DiscussInfo> dataSource={props.data} pagination={false} sticky>
            <Table.Column<DiscussInfo> key="id" title='编号' dataIndex="id" align={'center'} width={'10%'}/>
            <Table.Column<DiscussInfo> key="title" title="标题" dataIndex="title" align={'center'}
                render={(text:string, record:DiscussInfo) => <NavLink to={'/discuss/' + record.id}>{text}</NavLink>}/>
            <Table.Column<SolutionInfo> key="time" title="发布时间" dataIndex="time" align={'center'}
                                        render={text => getDate(text)}/>
            <Table.Column<DiscussInfo> key="author" title="作者" dataIndex="author" align={'center'} width={'10%'}/>
        </Table>
    );
}

export default DiscussTable;