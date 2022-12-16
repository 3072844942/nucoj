import React, {useEffect, useState} from 'react';
import Container from "../../component/container";
import axios from "axios";
import toast from "../../component/toast";
import {Card, Progress, Layout, Descriptions} from 'antd';

/**
 * 判题核心 数据页面
 * @constructor
 */
function JudgeHost() {
    // 直接给出数据， 防止给到空数据
    const [data, setData] = useState({
        code: '',
        message: '',
        request: '', // 不清楚是什么
        data: {
            workPath: {},
            scriptPath: '',
            resolutionPath: '',
            port: 0,
            workingAmount: 0,
            cpuCoreAmount: 0,
            memoryCostPercentage: 0,
            cpuCostPercentage: 0,
            queueAmount: 0,
            maxWorkingAmount: 0,
            version: ''
        }
    });

    useEffect(() => {
        axios({
            url: '/api/common/connection_test',
            method: 'get',
            timeout: 5000
        }).then(res => {
            setData(res.data.data)
            toast(res.data.data.message, res.data.data.data.version)
        })
    }, [])

    return (
        <Container title={'判题核心'} width={'96%'} style={{margin: '10px 0 0 20px'}}>
            <div style={{display: 'flex'}}>
                <div>
                    <Card title="CPU占用率" >
                        <Progress type="circle" percent={data.data.cpuCoreAmount} strokeColor={{ '0%': 'green', '100%': 'red' }} />
                    </Card>
                    <Card title="内存占用率" >
                        <Progress type="circle" percent={data.data.memoryCostPercentage} strokeColor={{ '0%': 'green', '100%': 'red' }} />
                    </Card>
                </div>
                <div style={{marginLeft: '2vw', height: '100%'}}>
                    <Descriptions title="JudgeHost" bordered column={{ xxl: 3, xl: 2, lg: 2, md: 2, sm: 1, xs: 1 }}>
                        <Descriptions.Item label="判题脚本目录">{data.data.scriptPath}</Descriptions.Item>
                        <Descriptions.Item label="解决方案(正确测试点)目录">{data.data.resolutionPath}</Descriptions.Item>
                        <Descriptions.Item label="判题机端口">{data.data.port}</Descriptions.Item>
                        <Descriptions.Item label="当前工作数目">{data.data.workingAmount}</Descriptions.Item>
                        <Descriptions.Item label="判题机cpu核心数目">{data.data.cpuCoreAmount}</Descriptions.Item>
                        <Descriptions.Item label="判题机等候判题队列最大数目">{data.data.queueAmount}</Descriptions.Item>
                        <Descriptions.Item label="判题机最大工作数目">{data.data.maxWorkingAmount}</Descriptions.Item>
                    </Descriptions>
                </div>
            </div>
        </Container>
    );
}

export default JudgeHost;