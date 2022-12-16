import React from 'react';
import {Table} from "antd";
import {NavLink} from "react-router-dom";
import {ProblemInfo} from "../../interface/problem";
import Button from "../button/Button";
import {getDate} from "../../util";

function ProblemTable(props:{data:ProblemInfo[]}) {
    return (
        <Table<ProblemInfo> dataSource={props.data} pagination={false}>
            <Table.Column<ProblemInfo> key="id" title="题号" dataIndex="id" align={'center'}/>
            <Table.Column<ProblemInfo>
                key="title"
                title='题目'
                dataIndex="title"
                align={'center'}
                render={(text:string, record: ProblemInfo) => <NavLink to={'/problem/' + record.id}>{text}</NavLink>}
            />
            <Table.Column<ProblemInfo> key="time" title="更新时间" dataIndex="time" align={'center'}
                                       render={text => getDate(text)}/>
            <Table.Column<ProblemInfo> key="grade" title="难度" dataIndex="grade" align={'center'}/>
            <Table.Column<ProblemInfo> key="tags" title="标签" dataIndex="tags" align={'center'}
                render={(tags: string[]) => (
                    <div style={{
                        display: "flex",
                        justifyContent: 'space-around',
                    }}>
                        {
                            tags.map((tag:string) => (
                                <Button context={tag} color={'green'} fontColor={'white'} size={4} enter={false}/>
                            ))
                        }
                    </div>
                )}/>
        </Table>
    );
}

export default ProblemTable;