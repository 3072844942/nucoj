import React from 'react';
import {SolutionInfo} from "../../interface/solution";
import {Table} from "antd";
import {NavLink} from "react-router-dom";
import {NoticeInfo} from "../../interface/notice";
import {getDate} from "../../util";

function SolutionTable(props: { data: SolutionInfo[] }) {
    return (
        <Table<SolutionInfo> dataSource={props.data} pagination={false} sticky>
            <Table.Column<SolutionInfo> key="id" title='编号' dataIndex="id" align={'center'} width={'10%'}/>
            <Table.Column<SolutionInfo> key="title" title="标题" dataIndex="title" align={'center'}
                                        render={(text: string, record: SolutionInfo) => <NavLink
                                            to={'/solution/' + record.id}>{text}</NavLink>}/>
            <Table.Column<SolutionInfo> key="time" title="发布时间" dataIndex="time" align={'center'}
                                      render={text => getDate(text)}/>
            <Table.Column<SolutionInfo> key="author" title="作者" dataIndex="author" align={'center'} width={'10%'}/>
        </Table>
    );
}

export default SolutionTable;