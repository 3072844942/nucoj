import React from 'react';
import {useParams} from "react-router-dom";
import TextContainer from "../../component/container/TextContainer";

/**
 * 题解详情页面
 * @constructor
 */
function Solution() {
    const params = useParams()

    return (
        <TextContainer url={'/api/solution/' + params.solutionId} title={'题解'}/>
    );
}

export default Solution;