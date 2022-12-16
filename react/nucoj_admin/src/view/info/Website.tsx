import React, {useEffect, useState} from 'react';
import Container from "../../component/container";
import {TextField} from "@mui/material";
import {Button} from "antd";
import axios from "axios";
import {SUCCESS} from "../../constant/controller";
import toast from "../../component/toast";

/**
 * 网站设置
 * 未来 webscoket也在这里修改
 * @constructor
 */
function Website() {
    const [title, setTitle] = useState('')
    useEffect(() => {
        axios({
            url: '/api',
            method: 'get'
        }).then(res => {
            setTitle(res.data.data.title)
        })
    }, [])

    return (
        <Container title={'网站信息'} width={'96%'} style={{margin: '10px 0 0 20px'}}>
            <div style={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center'
            }}>
                <div style={{
                    width: '20vw',
                    height: '30vh'
                }}>
                    <TextField id="blog" label="标题" variant="standard" value={title} onChange={(e) => setTitle(e.target.value)}/>
                    <div style={{marginTop: '5vh'}}>
                        <Button type={'primary'} onClick={() => {
                            axios({
                                url: '/api/info/update',
                                method: 'post',
                                data: {
                                    title: title
                                }
                            }).then(res => {
                                if (res.data.code === SUCCESS) toast('success', '')
                            })
                        }}>完成</Button>
                    </div>
                </div>
            </div>
        </Container>
    );
}

export default Website;