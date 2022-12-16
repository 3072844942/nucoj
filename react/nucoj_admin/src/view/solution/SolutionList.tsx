import React from 'react';
import {connect} from "react-redux";
import Container from "../../component/container";
import {useLocation} from "react-router-dom";
import ArticleTableContainer from "../../component/container/ArticleTableContainer";

/**
 * 所有题解
 * @param props
 * @constructor
 */
function SolutionList(props: any) {
    const location = useLocation()

    return (
        <Container style={{margin: '10px 0 0 20px'}} title={'题解列表'} width={'96%'}>
            <ArticleTableContainer url={'solution'} userId={location.pathname.split('/').length > 3 ? props.userId : undefined}/>
        </Container>
    );
}

const mapStateToProps = (status: any, preProps: any) => {
    return {
        userId: status.UserInfo.id,
        ...preProps
    }
}

export default connect(mapStateToProps)(SolutionList);