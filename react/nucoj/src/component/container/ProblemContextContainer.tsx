import React, {useEffect, useState} from 'react';
import {ProblemInfo} from "../../interface/problem";
import {Divider, Tag} from "antd";
import {markdownToHtml} from "../../util";
import WordContainer from "./WordContainer";

/**
 * 题目信息容器
 * @param props
 * @constructor
 */
function ProblemContextContainer(props: {data: ProblemInfo | undefined}) {
    return (
        <div style={{height: '97vh', overflowY: 'scroll'}}>
            <div style={{display: 'flex', width: '100%'}}>
                <h2 >{props.data?.id}&nbsp;&nbsp;{props.data?.title}</h2>
                <span><Tag style={{
                    borderRadius: '10px',
                    position: 'relative',
                    top: 0,
                    left: '20px'
                }}>{props.data?.grade}</Tag></span>
                <span style={{
                    fontSize: '0.8125em',
                    position: 'relative',
                    bottom: '-40px',
                    right: '-70%',
                    color: '#e3e3e6'
                }}>中北大学</span>
            </div>
            <Divider/>
            <div style={{padding: '0 3px'}}>
                <div
                    dangerouslySetInnerHTML={{__html: markdownToHtml(props.data?.context)}}
                    style={{
                        fontSize: '1.1em',
                    }}
                ></div>
                <p style={{fontSize: '1.6em', fontWeight: 'bolder'}}>输入格式 :</p>
                <WordContainer>
                    <div
                        dangerouslySetInnerHTML={{__html: markdownToHtml(props.data?.inputContext)}}
                    ></div>
                </WordContainer>
                <p style={{fontSize: '1.6em', fontWeight: 'bolder'}}>输出格式 :</p>
                <WordContainer>
                    <div
                        dangerouslySetInnerHTML={{__html: markdownToHtml(props.data?.outputContext)}}
                    ></div>
                </WordContainer>
                <p style={{fontSize: '1.6em', fontWeight: 'bolder'}}>输入样例 :</p>
                {
                    props.data?.examples.map(item => <WordContainer key={item.test}>
                        <div
                            style={{width: '100%', height: '100%'}}
                            dangerouslySetInnerHTML={{__html: markdownToHtml(item.test)}}
                        ></div>
                    </WordContainer>)
                }
                <p style={{fontSize: '1.6em', fontWeight: 'bolder'}}>输出样例 :</p>
                {
                    props.data?.examples.map(item => <WordContainer key={item.answer}>
                        <div
                            dangerouslySetInnerHTML={{__html: markdownToHtml(item.answer)}}
                        ></div>
                    </WordContainer>)
                }
                <WordContainer style={{
                    fontSize: '1em',
                    color: '#737373'
                }}>
                    <div>
                        <span>时间限制</span>
                        <span style={{float: 'right'}}>{props.data?.timeRequire} ms</span>
                    </div>
                    <div style={{marginTop: '1vh'}}>
                        <span>内存限制</span>
                        <span style={{float: 'right'}}>{props.data?.memoryRequire} kb</span>
                    </div>
                </WordContainer>
            </div>
        </div>
    );
}

export default ProblemContextContainer;