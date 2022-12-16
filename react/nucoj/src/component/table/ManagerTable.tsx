import React from 'react';
import type {ColumnsType} from 'antd/es/table';
import {Table} from 'antd';
import Button from "../button/Button";
import {ManagerInfo} from "../../interface/user";

const columns: ColumnsType<ManagerInfo> = [
    {
        title: '学号',
        dataIndex: 'number',
        key: 'number',
        align: 'center'
    },
    {
        title: '姓名',
        dataIndex: 'name',
        key: 'name',
        align: 'center'
    },
    {
        title: '学院',
        dataIndex: 'college',
        key: 'college',
        align: 'center'
    },
    {
        title: '毕业去向',
        dataIndex: 'destination',
        key: 'destination',
        render: text => text === "" ? "-" : text,
        align: 'center'
    },
    {
        title: '联系方式',
        dataIndex: 'contact',
        key: 'contact',
        render: item =>
            <div style={{
                display: "flex",
                justifyContent: 'space-around'
            }}>
                {item.qq != null &&
                    <Button context={"QQ"} color={"#A0D8EF"} fontColor={"#f0908d"} size={3} link={"https://wpa.qq.com/msgrd?v=3&uin=" + item.qq + "&site=qq&menu=yes"} enter={false}></Button>}
                {item.github != null &&
                    <Button context={"Github"} color={"#17184b"} fontColor={"#e3edc8"} size={3} link={item.github} enter={false}></Button>}
                {item.blog != null &&
                    <Button context={"Blog"} color={"#FFFFE5"} fontColor={"#D2A4C8"} size={3} link={item.blog} enter={false}></Button>}
            </div>,
        align: 'center'
    }
];

/**
 * 历届队员页面成员表格
 * 暂时搁浅
 * @param props
 * @constructor
 */
function ManagerTable(props: { list: Array<ManagerInfo>, style?: object}) {
    return (
        <Table
            style={{...props.style}}
            columns={columns}
            dataSource={props.list}
            pagination={false}
        />
    );
}

export default ManagerTable;