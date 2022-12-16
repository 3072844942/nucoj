import React from 'react';
import Container from "../../component/container";
import ArticleTableContainer from "../../component/container/ArticleTableContainer";

/**
 * 所有公告页面
 * @param props
 * @constructor
 */
function NoticeList() {

    return (
        <Container style={{margin: '10px 0 0 20px'}} title={'公告列表'} width={'96%'}>
            <ArticleTableContainer url={'notice'} />
        </Container>
    );
}

export default NoticeList;