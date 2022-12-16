/**
 * 用户信息
 */
interface UserInfo {
    id: string,
    nickname: string,
    avatar: string,
    grade: number,
    role: number,
    name: string,
    number: string,
    college: string,
    contact: {
        qq: string,
        blog: string,
        github: string
    }
}

interface MenuInfo {
    component: string,
    icon: string,
    path: string,
}

interface RoleInfo {

}

export type {UserInfo, MenuInfo, RoleInfo}