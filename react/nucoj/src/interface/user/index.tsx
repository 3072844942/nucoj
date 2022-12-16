/**
 * 用户信息
 */
interface UserInfo {
    id: string,
    nickname: string,
    avatar: string,
    grade: number,
    role: number
}

/**
 * 管理员信息
 * 用于历届队员界面
 */
interface ManagerInfo {
    id:string,
    number: string,
    name: string,
    college: string,
    destination: string,
    contact: {
        qq?: string,
        github?: string,
        blog?: string
    }
}

export type {UserInfo, ManagerInfo}