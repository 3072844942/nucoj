import React from 'react';
import {useParams} from "react-router-dom";
import TextContainer from "../../component/container/TextContainer";

/**
 * 公告详情页面
 * @param props
 * @constructor
 */
function Index(props: any) {
    const params = useParams()

    return (
        <TextContainer url={'/api/notice/' + params.noticeId} title={'公告'}/>
    );
}

export default Index;