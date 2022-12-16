import React from 'react';
import {useParams} from "react-router-dom";
import TextContainer from "../../component/container/TextContainer";

/**
 * 分享详情页面
 * @constructor
 */
function Discuss() {
    const params = useParams()

    return (
        <TextContainer url={'/api/discuss/' + params.discussId} title={'讨论'}/>
    );
}

export default Discuss;