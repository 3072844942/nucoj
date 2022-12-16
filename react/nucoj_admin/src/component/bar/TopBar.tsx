import {Breadcrumb} from 'antd';
import React, {useEffect} from 'react';
import {useLocation, Link, useNavigate} from "react-router-dom";
import {connect} from "react-redux";
import {MenuInfo} from "../../interface/menu";
import toast from "../toast";

// number 指代 数字
const breadcrumbNameMap: Record<string, string> = {
    '/notice': '公告',
    '/notice/list': '所有公告',
    '/notice/add': '添加公告',
    '/notice/number': '修改公告',
    '/solution': '题解',
    '/solution/list': '所有题解',
    '/solution/list/my': '我的题解',
    '/solution/add': '添加题解',
    '/solution/number': '修改题解',
    '/discuss': '分享',
    '/discuss/list': '所有分享',
    '/discuss/list/my': '我的题解',
    '/discuss/add': '添加分享',
    '/discuss/number': '修改分享',
    '/user': '用户',
    '/user/list': '所有用户',
    '/user/role': '所有角色',
    '/info': '系统',
    '/info/link': '友情链接',
    '/info/log': '日志中心',
    '/info/host': '判题服务',
    '/setting': '个人中心',
    '/problem': '题目',
    '/problem/add': '添加题目',
    '/problem/number': '修改题目',
    '/problem/list': '所有题目',
    '/contest': '比赛',
    '/contest/list': '比赛列表',
    '/contest/add': '添加比赛',
    '/contest/number': '修改比赛'
};

/**
 * 定位栏
 * @constructor
 */
function TopBar(props: {menu: MenuInfo[]}) {
    const location = useLocation();
    const history = useNavigate()
    const pathSnippets = location.pathname.split('/').filter(i => i);

    const extraBreadcrumbItems = pathSnippets.map((str, index) => {
        let url = '';
        for (let i = 0; i < index + 1; i ++ ) {
            if (isNaN(Number(pathSnippets[i])))
                url += '/' + pathSnippets[i]
            else url += '/' + 'number'
        }
        return (
            <Breadcrumb.Item key={url}>
                <Link to={url}>{breadcrumbNameMap[url]}</Link>
            </Breadcrumb.Item>
        );
    });

    const breadcrumbItems = [
        <Breadcrumb.Item key="home">
            <Link to="/">首页</Link>
        </Breadcrumb.Item>,
    ].concat(extraBreadcrumbItems);

    useEffect(() => {
        let url = '/' + pathSnippets[0]
        if (url !== '/undefined' && props.menu.filter(i => i.key === url).length === 0) {
            history('/')
            toast('', '你没有权限')
        }
    }, [location.pathname])

    return (
        <div
            style={{
                height: '5vh',
                paddingLeft: '3vw'
            }}
        >
            <Breadcrumb
                style={{fontSize: '16px'}}
                separator=">"
            >
                {breadcrumbItems}
            </Breadcrumb>
        </div>
    );
}

const mapStateToProps = (state: any) => {
    return {
        menu: state.UserInfo.menu
    }
}

export default connect(mapStateToProps)(TopBar)