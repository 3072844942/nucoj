import React from 'react';
import {connect} from "react-redux";
import {SubmitInfo} from "../../interface/contest";
import Background from "../background";
import {getDate, markdownToHtml} from "../../util";
import {Divider} from "antd/es";
import {Button} from "antd";
import {copy} from "clipboard";

/**
 * 提交记录容器
 * 类似于codeforces的查看提交记录
 * @param props
 * @constructor
 */
function SubmitInfoContainer(props: {
    data: SubmitInfo,
    onClick: Function
}) {

    return (
        <div style={{
            width: '100vw',
            height: '100vh',
            display: 'flex',
            position: 'fixed',
            left: '0',
            top: '0',
            zIndex: '5'
        }}>
            <Background color={'rgb(33, 33, 33)'} onClick={props.onClick}/>
            <div style={{
                backgroundColor: '#ffffff',
                width: '80vw',
                height: '86vh',
                opacity: '1',
                marginTop: '12vh',
                marginLeft: '10vw',
                padding: '3vh 1.5vw 0 1.5vw',
                borderRadius: '20px',
                animation: 'emergence 0.2s ease-in',
                zIndex: '1000',
            }}>
                <p style={{position: 'relative'}}>
                    By：{props.data.user}，problem：{props.data.problemId}，create on: {getDate(props.data.time)}，
                    <Button type={'link'} onClick={() => {
                        copy(props.data.code)
                    }}>Copy</Button>
                </p>
                <Divider/>
                <div
                    style={{backgroundColor: '#e6e6e6'}}
                    dangerouslySetInnerHTML={{__html: markdownToHtml('```\n' + props.data.code + '\n```')}}
                ></div>
            </div>
        </div>
    );
}

export default SubmitInfoContainer;