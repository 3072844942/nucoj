import React, {useEffect, useState} from 'react';
import Container from "../../component/container";
import NoticeTable from "../../component/table/NoticeTable";
import NoticeIcon from "../../assets/icon/NoticeIcon";
import RankTable from "../../component/table/RankTable";
import RankIcon from "../../assets/icon/RankIcon";
import WordTable from "../../component/table/WordTable";
import WordIcon from "../../assets/icon/WordIcon";
import ContestTable from "../../component/table/ContestTable";
import ContestIcon from "../../assets/icon/ContestIcon2";
import LinkTable from "../../component/table/LinkTable";
import LinkIcon from "../../assets/icon/LinkIcon";
import ProblemTable from "../../component/table/ProblemTable";
import UpdateIcon from "../../assets/icon/UpdateIcon2";
import {ContestInfo} from "../../interface/contest";
import axios from "axios";
import {NoticeInfo} from "../../interface/notice";
import {ProblemInfo} from "../../interface/problem";
import {UserInfo} from "../../interface/user";
import {LinkInfo} from "../../interface/link";
import {connect} from "react-redux";
import {getBlogInfo} from "../../action/controller";

/**
 * oj主页
 * @constructor
 */
function Index(props: any) {
    useEffect(() => {
        document.title = "Online Judge"
    }, [])

    const [noticeData, setNoticeData] = useState<NoticeInfo[]>([])
    const [contestData, setContestData] = useState<ContestInfo[]>([])
    const [problemData, setProblemData] = useState<ProblemInfo[]>([])
    const [rankData, setRankData] = useState<UserInfo[]>([])
    const [linkData, setLinkData] = useState<LinkInfo[]>([])
    const [list, setList] = useState<string[]>([])
    useEffect(() => {
        axios.get('/api/notice/recent?size=3')
            .then(res => {
                setNoticeData(res.data.data)
            })
        axios.get('/api/contest/recent?size=5')
            .then(res => {
                setContestData(res.data.data)
            })
        axios({
            url: '/api/contest/entry/user',
            method: 'post',
            data: {
                id: props.userId
            }
        }).then(res => {
            setList(res.data.data)
        })
        axios.get('/api/problem/recent?size=5')
            .then(res => {
                setProblemData(res.data.data)
            })
        axios.get('/api/user/rank?size=5')
            .then(res => {
                setRankData(res.data.data)
            })
        axios.get('/api/link/all')
            .then(res => {
                setLinkData(res.data.data.list)
            })
    }, [])

    return (
            <div style={{
                display: 'flex',
                margin: '3vh 4vw 3vh 3vw'
            }}>
                <div style={{
                }}>
                    <Container width={'60vw'} title={'公告'} icon={<NoticeIcon/>}>
                        <NoticeTable data={noticeData}/>
                    </Container>
                    <Container style={{marginTop: '5vh'}} width={'60vw'} title={'近期比赛'} icon={<ContestIcon/>}>
                        <ContestTable data={contestData} entryList={list} userId={props.userId}/>
                    </Container>
                    <Container style={{marginTop: '5vh'}} width={'60vw'} title={'最近更新'} icon={<UpdateIcon/>}>
                        <ProblemTable data={problemData}/>
                    </Container>
                </div>
                <div style={{
                    marginLeft: '2vw',
                }}>
                    <Container width={'30vw'} title={'排名'} icon={<RankIcon/>}>
                        <RankTable data={rankData}/>
                    </Container>
                    <Container style={{marginTop: '5vh'}} width={'30vw'} title={'一言（ヒトコト）'} icon={<WordIcon/>}>
                        <WordTable/>
                    </Container>
                    <Container style={{marginTop: '5vh'}} width={'30vw'} title={'友情链接'} icon={<LinkIcon/>}>
                        <LinkTable data={linkData}/>
                    </Container>
                </div>
            </div>
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
export default connect(mapStateToProps)(Index);