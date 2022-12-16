import React from 'react';
import ArticleContainer from "../../component/editor/ArticleEditor";
import {useParams} from "react-router-dom";

/**
 * 创建/修改 分享页面
 * @constructor
 */
function Discuss() {
    const params = useParams()

    return (
        <ArticleContainer
            style={{margin: '10px 0 0 20px'}}
            id={params.discussId}
            title={'发布分享'}
            url={'discuss'}
        />
    );
}

export default Discuss;