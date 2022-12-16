import {MenuInfo} from "../../interface/menu";

/**
 * 完整菜单， 方便调试
 */
const menus: MenuInfo[] = [
    {
        label: '首页',
        key: '/',
        icon: "HomeIcon",
        children: undefined
    },
    {
        label: '公告',
        key: '/notice',
        icon: "NoticeIcon",
        children: [
            {
                label: '添加公告',
                key: '/notice/add',
                icon: undefined,
                children: undefined
            },
            {
                label: '所有公告',
                key: '/notice/list',
                icon: undefined,
                children: undefined
            },
            // {
            //     label: '修改公告',
            //     key: '/article/{noticeId}',
            //     icon: undefined,
            //     children: undefined
            // }
        ]
    },
    {
        label: '题目',
        key: '/problem',
        icon: "ProblemIcon",
        children: [
            {
                label: '添加题目',
                key: '/problem/add',
                icon: undefined,
                children: undefined
            },
            {
                label: '所有题目',
                key: '/problem/all',
                icon: undefined,
                children: undefined
            },
            // {
            //     label: '修改题目',
            //     key: '/problem/{problemId}',
            //     icon: undefined,
            //     children: undefined
            // }
        ]
    },
    {
        label: '题单',
        key: '/train',
        icon: "TrainIcon",
        children: [
            {
                type: 'group',
                label: '个人',
                key: '/train/self',
                children: [
                    {
                        label: '添加题单',
                        key: '/train/add',
                        icon: undefined,
                        children: undefined
                    },
                    {
                        label: '我的题单',
                        key: '/train/list/my',
                        icon: undefined,
                        children: undefined
                    },
                    // {
                    //     label: '修改题单',
                    //     key: '/train/{trainId}',
                    //     icon: undefined,
                    //     children: undefined
                    // }
                ]
            },
            {
                type: 'group',
                label: '全部',
                key: '/train/whole',
                children: [
                    {
                        label: '所有题单',
                        key: '/train/list',
                        icon: undefined,
                        children: undefined
                    },
                    // {
                    //     label: '修改题单',
                    //     key: '/train/{trainId}',
                    //     icon: undefined,
                    //     children: undefined
                    // }
                ]
            }
        ]
    },
    {
        label: '比赛',
        key: '/contest',
        icon: "ContestIcon",
        children: [
            {
                label: '添加比赛',
                key: '/contest/add',
                icon: undefined,
                children: undefined
            },
            {
                label: '所有比赛',
                key: '/contest/list',
                icon: undefined,
                children: undefined
            },
            // {
            //     label: '修改比赛',
            //     key: '/contest/{contestId}',
            //     icon: undefined,
            //     children: undefined
            // }
            // {
            //     label: '修改比赛题目',
            //     key: '/contest/{contestId}/{problemId}',
            //     icon: undefined,
            //     children: undefined
            // }
            {
                label: '比赛实时',
                key: '/contest/now',
                icon: undefined,
                children: undefined
            }
        ]
    },
    {
        label: '题解',
        key: '/solution',
        icon: "SolutionIcon",
        children: [
            {
                type: 'group',
                label: '个人',
                key: '/solution/self',
                children: [
                    {
                        label: '添加题解',
                        key: '/solution/add',
                        icon: undefined,
                        children: undefined
                    },
                    {
                        label: '我的题解',
                        key: '/solution/list/my',
                        icon: undefined,
                        children: undefined
                    },
                    // {
                    //     label: '修改题单',
                    //     key: '/solution/{solutionId}',
                    //     icon: undefined,
                    //     children: undefined
                    // }
                ]
            },
            {
                type: 'group',
                label: '全部',
                key: '/solution/whole',
                children: [
                    {
                        label: '所有题解',
                        key: '/solution/list',
                        icon: undefined,
                        children: undefined
                    },
                    // {
                    //     label: '修改题单',
                    //     key: '/solution/{solutionId}',
                    //     icon: undefined,
                    //     children: undefined
                    // }
                ]
            }
        ]
    },
    {
        label: '分享',
        key: '/discuss',
        icon: "DiscussIcon",
        children: [
            {
                type: 'group',
                label: '个人',
                key: '/discuss/self',
                children: [
                    {
                        label: '添加分享',
                        key: '/discuss/add',
                        icon: undefined,
                        children: undefined
                    },
                    {
                        label: '我的分享',
                        key: '/discuss/list/my',
                        icon: undefined,
                        children: undefined
                    },
                    // {
                    //     label: '修改题单',
                    //     key: '/discuss/{discussId}',
                    //     icon: undefined,
                    //     children: undefined
                    // }
                ]
            },
            {
                type: 'group',
                label: '全部',
                key: '/discuss/whole',
                children: [
                    {
                        label: '所有分享',
                        key: '/discuss/list',
                        icon: undefined,
                        children: undefined
                    },
                    // {
                    //     label: '修改题单',
                    //     key: '/discuss/{discussId}',
                    //     icon: undefined,
                    //     children: undefined
                    // }
                ]
            }
        ]
    },
    {
        label: '用户',
        key: '/user',
        icon: "UserIcon",
        children: [
            {
                label: '所有用户',
                key: '/user/list',
                icon: undefined,
                children: undefined
            },
            {
                label: '角色管理',
                key: '/user/role',
                icon: undefined,
                children: undefined
            }
        ]
    },
    {
        label: '系统管理',
        key: '/info',
        icon: "SettingIcon",
        children: [
            {
                label: '网站管理',
                key: '/info/website',
                icon: undefined,
                children: undefined
            },
            {
                label: '友链管理',
                key: '/info/link',
                icon: undefined,
                children: undefined
            },
            {
                label: '日志管理',
                key: '/info/log',
                icon: undefined,
                children: undefined
            },
            {
                label: '判题服务',
                key: '/info/host',
                icon: undefined,
                children: undefined
            }
        ],
    },
    {
        label: '个人中心',
        key: '/setting',
        icon: "SelfIcon",
        children: undefined
    }
]

const rootSubmenuKeys = ['/', '/article', '/problem', '/train', '/contest', '/solution', '/discuss', '/user', '/info', '/setting'];

export {menus, rootSubmenuKeys}