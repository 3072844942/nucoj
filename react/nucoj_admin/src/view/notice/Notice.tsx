import React from 'react';
import {useParams} from "react-router-dom";
import ArticleContainer from "../../component/editor/ArticleEditor";

/**
 * 修改/创建 公告页面
 * @constructor
 */
function Notice() {
    const params = useParams()

    return (
        <ArticleContainer
            style={{margin: '10px 0 0 20px'}}
            id={params.noticeId}
            title={'发布公告'}
            url={'notice'}
        />
    );
}

export default Notice