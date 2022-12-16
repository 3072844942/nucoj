import React, {ReactElement, useEffect, useState} from 'react';
import axios from "axios";
import { Divider } from 'antd';
import Button from "../button/Button";

function ScrollList(props: {
    url: string,
    children: ReactElement<any, any>,
    value: string,
    data: any[],
    setData: Function,
    tags?: string[]
}) {
    const [current, setCurrent] = useState(1)
    const [data, setData] = useState<any[]>([])
    const [size, setSize] = useState(0)

    useEffect(() => {
        setCurrent(1)
        loadMoreData()
    }, [props.value, props.tags])

    useEffect(() => {
        props.setData(data)
    }, [data])

    const loadMoreData = () => {
        // 更新数据
        axios({
            url: '/api/' + props.url + '/list',
            method: 'post',
            data: {
                size: 10,
                current: current,
                keywords: props.value,
                tags: props?.tags
            }
        }).then((res: any) => {
            // 如果没有更多的数据
            if (res.data.code === 50000) {
                return ;
            }

            // 如果不是第一次请求
            if (current !== 1) {
                let newData = [...data]
                setData(newData.concat(res.data.data.list))
            }
            else setData(res.data.data.list)
            setSize(res.data.data.total)
        })
    };

    return (
        <div>
            {props.children}
            <Divider orientation="center">
                {
                    data.length < size ? <Button
                        context={'加载更多'}
                        color={'#fff'}
                        fontColor={'black'}
                        size={6}
                        enter={false}
                        onClick={() => {
                            loadMoreData()
                        }}/>
                        : <p>It is all, nothing more 🤐</p>
                }
            </Divider>
        </div>
    );
}

export default ScrollList;