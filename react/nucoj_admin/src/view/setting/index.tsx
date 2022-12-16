import React, {useEffect, useState} from 'react';
import {connect} from "react-redux";
import Container from "../../component/container";
import {Button, message, Upload} from 'antd';
import type {UploadChangeParam} from 'antd/es/upload';
import type {RcFile, UploadFile, UploadProps} from 'antd/es/upload/interface';
import ImgCrop from 'antd-img-crop';
import axios from "axios";
import {SUCCESS} from "../../constant/controller";
import toast from "../../component/toast";
import {UserInfo} from "../../interface/user";
import {TextField} from "@mui/material";
import {Card} from 'antd';

/**
 * 个人中心
 * @param props
 * @constructor
 */
function Index(props: UserInfo) {
    const [data, setData] = useState<UserInfo>(props)

    const getBase64 = (img: RcFile, callback: (url: string) => void) => {
        const reader = new FileReader();
        reader.addEventListener('load', () => callback(reader.result as string));
        reader.readAsDataURL(img);
    };

    const handleChange: UploadProps['onChange'] = (info: UploadChangeParam<UploadFile>) => {
        if (info.file.status === 'uploading') {
            return;
        }
        if (info.file.status === 'done') {
            // Get this url from response in real world.
            getBase64(info.file.originFileObj as RcFile, (url) => {
                setData({
                    ...data,
                    avatar: url
                })
            });
        }
    };

    const beforeUpload = (file: RcFile) => {
        const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
        if (!isJpgOrPng) {
            message.error('You can only upload JPG/PNG file!');
        }
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
            message.error('Image must smaller than 2MB!');
        }
        return isJpgOrPng && isLt2M;
    };

    const onPreview = async (file: UploadFile) => {
        let src = file.url as string;
        if (!src) {
            src = await new Promise((resolve) => {
                const reader = new FileReader();
                reader.readAsDataURL(file.originFileObj as RcFile);
                reader.onload = () => resolve(reader.result as string);
            });
        }
        const image = new Image();
        image.src = src;
        const imgWindow = window.open(src);
        imgWindow?.document.write(image.outerHTML);
    };

    useEffect(() => {
        if (data.avatar !== props.avatar) {
            axios({
                url: '/api/user/avatar',
                method: 'post',
                data: {
                    userId: props.id,
                    keywords: data.avatar
                }
            }).then(res => {
                if (res.data.code === SUCCESS) toast('success', '')
            })
        }
    }, [data])

    return (
        <Container style={{margin: '10px 0 0 20px'}} title={'个人中心'} width={'96%'}>
            <div style={{
                width: '100%',
                display: 'flex'
            }}>
                <div style={{
                    width: '35%',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center'
                }}>
                    <div style={{
                        width: '20%'
                    }}>
                        <ImgCrop rotate>
                            <Upload
                                name="file"
                                listType="picture-card"
                                showUploadList={false}
                                action="/api/img/upload"
                                method={'post'}
                                beforeUpload={beforeUpload}
                                onChange={handleChange}
                                onPreview={onPreview}
                            >
                                <img src={data.avatar} alt="avatar" style={{width: '100%'}}/>
                            </Upload>
                        </ImgCrop>
                    </div>

                </div>
                <div style={{
                    width: '65%'
                }}>
                    <Card title={'个人信息'}>
                        <Card type={'inner'}>
                            <div>
                                <TextField id="nickname" label="昵称" variant="standard" value={data.nickname}
                                           onChange={(e) => setData({...data, nickname: e.target.value})}/>
                            </div>
                            <div>
                                <TextField id="name" label="姓名" variant="standard" value={data.name}
                                           onChange={(e) => setData({...data, name: e.target.value})}/>
                            </div>
                            <div>
                                <TextField id="number" label="学号" variant="standard" value={data.number}
                                           onChange={(e) => setData({...data, number: e.target.value})}/>
                            </div>
                            <div>
                                <TextField id="college" label="学院" variant="standard" value={data.college}
                                           onChange={(e) => setData({...data, college: e.target.value})}/>
                            </div>
                        </Card>

                        <Card title="联系方式" type={'inner'} style={{marginTop: 16}}>
                            <div>
                                <TextField id="qq" label="QQ" variant="standard" value={data.contact.qq}
                                           onChange={(e) => setData({
                                               ...data,
                                               contact: {...data.contact, qq: e.target.value}
                                           })}/>
                            </div>
                            <div>
                                <TextField id="github" label="Github" variant="standard" value={data.contact.github}
                                           onChange={(e) => setData({
                                               ...data,
                                               contact: {...data.contact, github: e.target.value}
                                           })}/>
                            </div>
                            <div>
                                <TextField id="blog" label="Blog" variant="standard" value={data.contact.blog}
                                           onChange={(e) => setData({
                                               ...data,
                                               contact: {...data.contact, blog: e.target.value}
                                           })}/>
                            </div>
                        </Card>
                        <Button style={{marginTop: 16}} type={'primary'} onClick={() => {
                            axios({
                                url: '/api/user/update',
                                method: 'post',
                                data: {
                                    ...data
                                }
                            }).then(res => {
                                if (res.data.code === SUCCESS) toast('success', '')
                            })
                        }}>完成</Button>
                    </Card>
                </div>
            </div>
        </Container>
    );
}

const mapStateToProps = (status: any) => {
    return {
        ...status.UserInfo
    }
}

export default connect(mapStateToProps)(Index);