import React from 'react';
import {Table} from "antd";
import ChampionIcon from "../../assets/icon/ChampionIcon";
import RunnerupIcon from "../../assets/icon/RunnerupIcon";
import ThirdPlaceIcon from "../../assets/icon/ThirdPlaceIcon";
import {UserInfo} from "../../interface/user";

/**
 * 主页排名表格
 * @constructor
 */
function HomeRankTable(props:{data:UserInfo[]}) {
    const getIcon = (text: number) => {
        if (text === 0) return <ChampionIcon/>
        else if (text === 1) return <RunnerupIcon/>
        else if (text === 2) return <ThirdPlaceIcon/>
        else return undefined
    }

    return (
        <Table<UserInfo> dataSource={props.data} pagination={false}>
            <Table.Column<UserInfo> key="#" title='#' dataIndex="rank" align={'center'}
                                          render={(text: number, record, index:number) => {
                                              return getIcon(index)
                                          }}/>
            <Table.Column<UserInfo> key="nickname" title="姓名" dataIndex="nickname" align={'center'}/>
            <Table.Column<UserInfo> key="grade" title="等级" dataIndex="grade" align={'center'}
                                          render={(text: number) => <p>lv.{Math.floor((text) / 100)} </p>}/>
        </Table>
    );
}

export default HomeRankTable;