import React from 'react';
import {TrainInfo} from "../../interface/train";
import {Table} from "antd";
import {NavLink} from "react-router-dom";

function TrainTable(props:{data:TrainInfo[]}) {
    return (
        <Table<TrainInfo> dataSource={props.data} pagination={false} sticky>
            <Table.Column<TrainInfo> key="id" title='#' dataIndex="id" align={'center'} width={'8vw'}/>
            <Table.Column<TrainInfo> key="name" title="标题" dataIndex="title" align={'center'}
                render={(text:string, record:TrainInfo) => <NavLink to={'/train/' + record.id}>{text}</NavLink>}/>
            {/*<Table.Column<TrainInfo> key="count" title="完成度" align={'center'} render={(text: number, record: TrainInfo) =>*/}
            {/*                              <Progress showInfo={false}*/}
            {/*                                        percent={record.completedQuantity / record.quantity * 100}*/}
            {/*                                        steps={record.quantity}/>}/>*/}
            <Table.Column<TrainInfo> key="author" title="作者" dataIndex="author" align={'center'} width={'10vw'}/>
        </Table>
    );
}

export default TrainTable;