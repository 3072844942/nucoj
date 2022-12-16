import React, {useEffect, useState} from 'react';
import {connect} from "react-redux";
import axios from "axios";
import {Input} from "antd";
import {getDate} from "../../util";
import Button from "../button/Button";
import Editor from "./Editor";
import Container from "../container";
import {SUCCESS} from "../../constant/controller";
import toast from "../toast";

/**
 * 公告/题解/分享 容器
 * @param props
 * @constructor
 */
function ArticleEditor(props: any) {
    const [title, setTitle] = useState('');
    const [text, setText] = useState('');

    useEffect(() => {
        if (props.id !== undefined && props.id !== null) {
            axios({
                url: '/api/' + props.url + '/' + props.id,
                method: 'get'
            }).then(res => {
                setText(res.data.data.context)
                setTitle(res.data.data.title)
            })
        }
    }, [])

    return (
        <Container style={props.style} title={props.title} width={'96%'}>
            <div
                style={{
                    display: 'flex'
                }}
            >
                <Input
                    value={title}
                    onChange={(e) => {
                        setTitle(e.target.value)
                    }}
                    status={title.length ? undefined : 'error'}
                    style={{marginRight: '3vw'}}
                    placeholder={getDate(new Date().getTime())}
                    allowClear
                />
                <Button context={props.title} color={'#0081ff'} fontColor={'white'} size={7} enter={true}
                        onClick={() => {
                            axios({
                                url: '/api/' + props.url + '/update',
                                method: 'post',
                                data: {
                                    id: props.id,
                                    title: title,
                                    time: new Date().getTime(),
                                    author: props.userId,
                                    context: text,
                                },
                                timeout: 2000
                            }).then(res => {
                                if (res.data.code === SUCCESS) toast('success', "发布成功")
                                else toast("fail", res.data.message)
                            })
                        }}
                />
            </div>

            <Editor editorId={'default'} style={{
                marginTop: '5vh'
            }} text={text} setText={setText}/>
        </Container>
    );
}

const mapStateToProps = (status: any, preProps: {
    url: string,
    id: string | number | undefined,
    title: string,
    style?: object
}) => {
    return {
        nickName: status.UserInfo.nickName,
        userId: status.UserInfo.id,
        ...preProps
    }
}

export default connect(mapStateToProps)(ArticleEditor);