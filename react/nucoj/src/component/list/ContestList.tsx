import React, {useEffect, useState} from 'react';
import ContestTable from "../table/ContestTable";
import ScrollList from "./ScrollList";
import {ContestInfo} from "../../interface/contest";
import axios from "axios";
import {connect} from "react-redux";

function ContestList(props:any) {
    const [data, setDate] = useState<ContestInfo[]>([])
    const [list, setList] = useState<string[]>([])

    // 获取用户所有报名的  未结束的比赛名称
    useEffect(() => {
        axios({
            url: '/api/contest/entry/user',
            method: 'post',
            data: {
                id: props.userId
            }
        }).then(res => {
            setList(res.data.data)
        })
    }, [])

    return (
        <ScrollList url={'contest'} value={props.value} setData={(item:any) => {
            setDate(item)
        }} data={data}>
            <ContestTable data={data} entryList={list} userId={props.userId}/>
        </ScrollList>
    );
}

const mapStateToProps = (state: any, preState: {
    value:string
}) => {
    return {
        ...preState,
        userId: state.UserInfo.id
    }
}

export default connect(mapStateToProps)(ContestList);