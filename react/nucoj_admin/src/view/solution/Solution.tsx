import React from 'react';
import {useParams} from "react-router-dom";
import ArticleContainer from "../../component/editor/ArticleEditor";

/**
 * 修改/发布 题解页面
 * @param props
 * @constructor
 */
function Solution() {
    const params = useParams()

    return (
        <ArticleContainer
            style={{margin: '10px 0 0 20px'}}
            id={params.solutionId}
            title={'发布题解'}
            url={'solution'}
        />
    );
}

export default Solution