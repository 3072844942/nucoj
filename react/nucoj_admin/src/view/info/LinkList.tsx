import Container from "../../component/container";
import React, {useEffect, useState} from 'react';
import {LinkInfo} from "../../interface/link";
import axios from "axios";
import LinkTable from "../../component/table/LinkTable";
import toast from "../../component/toast";

/**
 * 所有友链
 * @constructor
 */
function LinkList() {
    const [data, setData] = useState<LinkInfo[]>([]);

    const getData = () => {
        axios({
            url: '/api/link/all',
            method: 'get',
            timeout: 2000
        }).then(res => {
            setData(res.data.data.list)
        })
    }

    const updateData = (data: LinkInfo[]) => {
        axios({
            url: '/api/link/update',
            method: 'post',
            data: {
                list: data
            },
            timeout: 2000
        }).then(res => {
            toast('success', '更新成功')
        })
    }

    useEffect(() => {
        getData()
    }, [])

    return (
        <Container style={{margin: '20px 0 0 20px'}} title={'友情链接'} width={'96%'}>
            <LinkTable data={data} setData={(data:LinkInfo[]) => {
                setData(data)
                updateData(data)
            }}/>
        </Container>
    );
}

export default LinkList
