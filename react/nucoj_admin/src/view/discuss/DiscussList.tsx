import React from 'react';
import {connect} from "react-redux";
import Container from "../../component/container";
import {useLocation} from "react-router-dom";
import ArticleTableContainer from "../../component/container/ArticleTableContainer";

/**
 * 所有分享
 * @param props
 * @constructor
 */
function DiscussList(props: any) {
    const location = useLocation()

    return (
        <Container style={{margin: '10px 0 0 20px'}} title={'分享列表'} width={'96%'}>
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

const mapDispatchToProps = {}

export default connect(mapStateToProps, mapDispatchToProps)(DiscussList);