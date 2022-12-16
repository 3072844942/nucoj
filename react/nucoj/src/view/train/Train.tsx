import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import axios from "axios";
import {connect} from "react-redux";
import {UserInfo} from "../../interface/user";
import {TrainInfo} from "../../interface/train";
import ProblemContainer from "../../component/container/ProblemContainer";

function Train(props: { userInfo: UserInfo }) {
    const params = useParams();
    const [data, setData] = useState<TrainInfo>();

    useEffect(() => {
        axios({
            url: '/api/train/' + params.trainId,
            method: 'get',
        }).then(res => {
            setData(res.data.data)
        })
    }, [])

    useEffect(() => {
        document.title = data?.title + ' | 题单'
    }, [data])

    return (
        <div>
            {/*顶部名称*/}
            <div style={{
                backgroundColor: '#f3f3f3',
                width: '100%',
                height: '20vh',
                paddingLeft: '6vw',
                paddingTop: '4.5vh',
                boxShadow: '0 2px 2px 2px rgb(7, 17, 27, .15)'
            }}>
                <h2 style={{
                    fontSize: '32px'
                }}>{data?.title}</h2>
            </div>
            <div style={{
                width: '85vw',
                marginLeft: '7vw',
                marginTop: '5vh',
                display: 'flex'
            }}>
                <div style={{
                    width: '65vw'
                }}>
                    {
                        data?.problems.map(item =>
                            <ProblemContainer key={item.id} problemInfo={item} status={0}/>)
                    }
                </div>
                {/*侧边栏很空*/}
                <div style={{
                    width: '17vw',
                    height: '100px',
                    marginLeft: '2vw',
                    paddingTop: '20px',
                    textAlign: 'center',
                    borderRadius: '20px',
                    boxShadow: '0 2px 2px 2px rgb(7, 17, 27, .15)',
                    position: 'sticky'
                }}>
                </div>
            </div>
        </div>
    );
}

const mapStateToProps = (state: any) => {
    return {
        userInfo: state.UserInfo
    }
}

const mapDispatchToProps = {}

export default connect(mapStateToProps, mapDispatchToProps)(Train);