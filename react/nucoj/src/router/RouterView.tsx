import * as React from 'react';
import {Route, Routes} from "react-router-dom";
import Index from "../view/404";
import loading from "./Loading";

const RouterList: any[] = [
    {
        path: '/',
        component: loading(() => import('../view/cover')),
        meta: {
            title: '封面'
        }
    },
    {
        path: '/index',
        component: loading(() => import('../view/home')),
        meta: {
            title: 'OJ主页'
        },
    },
    {
        path: '/contest',
        component: loading(() => import('../view/contest')),
        meta: {
            title: 'OJ比赛'
        },
    },
    {
        path: '/problem',
        component: loading(() => import('../view/problem')),
        meta: {
            title: 'OJ题库'
        },
    },
    {
        path: '/train',
        component: loading(() => import('../view/train')),
        meta: {
            title: 'OJ题单'
        },
    },
    {
        path: '/solution',
        component: loading(() => import('../view/solution')),
        meta: {
            title: 'OJ题解'
        },
    },
    {
        path: '/discuss',
        component: loading(() => import('../view/discuss')),
        meta: {
            title: 'OJ分享'
        },
    },
    {
        path: '/notice/:noticeId',
        component: loading(() => import('../view/notice')),
        meta: {
            title: '公告详情'
        }
    },
    {
        path: '/contest/:contestId',
        component: loading(() => import('../view/contest/Race')),
        meta: {
            title: '比赛详情'
        }
    },
    {
        path: '/problem/:problemId',
        component: loading(() => import('../view/problem/Problem')),
        meta: {
            title: '题目详情'
        }
    },
    {
        path: '/train/:trainId',
        component: loading(() => import('../view/train/Train')),
        meta: {
            title: '题单详情'
        }
    },
    {
        path: '/solution/:solutionId',
        component: loading(() => import('../view/solution/Solution')),
        meta: {
            title: '题解详情'
        }
    },
    {
        path: '/discuss/:discussId',
        component: loading(() => import('../view/discuss/Discuss')),
        meta: {
            title: '分享详情'
        }
    },
    {
        path: '/contest/:contestId/problem/:problemId',
        component: loading(() => import('../view/problem/Problem'))
    },
    {
        path: '/thank',
        component: loading(() => import('../view/thank'))
    }
]

/**
 * 路由
 * @constructor
 */
function RouterView() {
    return (
        <Routes>
            {
                RouterList.map(item => (
                    <Route
                        key={item.path}
                        path={item.path}
                        element={<item.component/>}
                    ></Route>
                ))
            }
            <Route path={"*"} element={<Index/>}></Route>
        </Routes>
    );
}

export default RouterView;