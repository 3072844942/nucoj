import * as React from 'react';
import {Navigate, Route, Routes} from "react-router-dom";
import Login from "../view/login";
import load from "./Loading";
import {connect} from "react-redux";
import Index from "../view/404";

const menus = [
    {
        path: '/',
        component: load(() => import('../view/home')),
    },
    {
        path: '/notice/add',
        component: load(() => import('../view/notice/Notice'))
    },
    {
        path: '/notice/:noticeId',
        component: load(() => import('../view/notice/Notice'))
    },
    {
        path: '/notice/list',
        component: load(() => import('../view/notice/NoticeList'))
    },
    {
        path: '/solution/add',
        component: load(() => import('../view/solution/Solution'))
    },
    {
        path: '/solution/:solutionId',
        component: load(() => import('../view/solution/Solution'))
    },
    {
        path: '/solution/list',
        component: load(() => import('../view/solution/SolutionList'))
    },
    {
        path: '/solution/list/my',
        component: load(() => import('../view/solution/SolutionList'))
    },
    {
        path: '/discuss/add',
        component: load(() => import('../view/discuss/Discuss'))
    },
    {
        path: '/discuss/:discussId',
        component: load(() => import('../view/discuss/Discuss'))
    },
    {
        path: '/discuss/list',
        component: load(() => import('../view/discuss/DiscussList'))
    },
    {
        path: '/discuss/list/my',
        component: load(() => import('../view/discuss/DiscussList'))
    },
    {
        path: '/user/list',
        component: load(() => import('../view/user/UserList'))
    },
    {
        path: '/user/role',
        component: load(() => import('../view/user/RoleList'))
    },
    {
        path: '/info/link',
        component: load(() => import('../view/info/LinkList'))
    },
    {
        path: '/info/log',
        component: load(() => import('../view/info/LogList'))
    },
    {
        path: '/info/host',
        component: load(() => import('../view/info/JudgeHost'))
    },
    {
        path: '/setting',
        component: load(() => import('../view/setting'))
    },
    {
        path: '/problem/add',
        component: load(() => import('../view/problem/Problem'))
    },
    {
      path: '/problem/:problemId',
      component: load(() => import('../view/problem/Problem'))
    },
    {
        path: '/problem/list',
        component: load(() => import('../view/problem/ProblemList'))
    },
    {
        path: '/contest/list',
        component: load(() => import('../view/contest/ContestList'))
    },
    {
        path: '/contest/add',
        component: load(() => import('../view/contest/Contest'))
    },
    {
      path: '/contest/now',
      component: load(() => import('../view/contest/ContestList'))
    },
    {
        path: '/contest/:contestId',
        component: load(() => import('../view/contest/Contest'))
    },
    {
        path: '/contest/:contestId/problem/add',
        component: load(() => import('../view/problem/Problem'))
    },
    {
        path: '/contest/:contestId/problem/:problemId',
        component: load(() => import('../view/problem/Problem'))
    },
    {
        path: '/info/website',
        component: load(() => import('../view/info/Website'))
    }
]

/**
 * 路由
 * @constructor
 */
function RouterView(props:{id:string}) {
    return (
        <Routes>
            {/*{*/}
            {/*    // 开发版本*/}
            {/*    menus.map(item => <Route key={item.path} path={item.path} element={<item.component/>}></Route>)*/}
            {/*}*/}
            <Route key={'/login'} path={'/login'} element={<Login/>}></Route>
            {
                menus.map(item => <Route key={item.path} path={item.path} element={
                    props.id === '' ?
                        <Navigate to="/login"/>
                        : <item.component/>
                }></Route>
                )
            }
            <Route key={"*"} path={'*'} element={
                props.id === '' ?
                    <Navigate to="/login" />
                    : <Index/>
            }></Route>
        </Routes>
    );
}
const mapStateToProps = (status:any) => {
    return {
        id: status.UserInfo.id
    }
}

export default connect(mapStateToProps)(RouterView);