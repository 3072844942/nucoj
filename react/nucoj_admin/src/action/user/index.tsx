/**
 * 登入
 * @param email
 * @param password
 * @response 0 | 成功；1 | 账号密码不匹配；2 | 不存在
 */
import axios from "axios";
import {GET_MENU, LOGIN_USER, LOGOUT_USER} from "../../constant/user";
import {SUCCESS} from "../../constant/controller";
import toast from "../../component/toast";
import ErrorIcon from "../../assets/icon/ErrorIcon";
import NoWifiIcon from "../../assets/icon/NoWifiIcon";

const login = (email: string, password: string) => {
    return (dispatch: any) => {
        axios({
            url: '/api/user/login',
            method: 'post',
            data: {
                email: email,
                password: password
            },
            timeout: 2000
        }).then(res => {
            if (res.data.code === 20000) {
                // 更新用户
                dispatch({
                    type: LOGIN_USER,
                    payload: {...res.data.data}
                })
                toast("success", "登录成功")
                localStorage.setItem("email", email)
                localStorage.setItem("password", password)
                // 将token放入axios自带
                axios.defaults.headers.common['token'] = res.data.data.token
            }
            else toast("fail", '登录失败，' + res.data.message, <ErrorIcon/>)
        }).catch(err => {
            toast("fail", '请检查网络连接', <NoWifiIcon/>)
        })
    }
}

/**
 * 登出
 */
const logout = () => {
    localStorage.clear()
    return (dispatch: any) => {
        axios({
            url: '/api/user/logout',
            method: 'post',
            timeout: 2000
        }).then(res => {
            dispatch({
                type: LOGOUT_USER
            })
            if (res.data.code === SUCCESS) {
                toast('success', "注销成功")
                delete axios.defaults.headers.common['token']
            }
            else toast("fail", res.data.msg)
        })
    }
}

const getMenu = (id:string) => {
    return (dispatch: any) => {
        axios({
            url: '/api/user/menus',
            method: 'post',
            data: {
                email: id
            },
            timeout: 2000
        }).then(res => {
            dispatch({
                type: GET_MENU,
                payload: {
                    menu: res.data.data
                }
            })
        })
    }
}

export {login, logout, getMenu}