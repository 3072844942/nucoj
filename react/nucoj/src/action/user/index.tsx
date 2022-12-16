import axios from "axios";
import {LOGIN_USER, LOGOUT_USER, MODIFY_USER, REGISTER_USER, SEND_EMAIL,} from "../../constant/user";
import {HIDDEN_FORGET, HIDDEN_LOGIN, HIDDEN_REGISTER, SHOW_LOGIN, SUCCESS} from "../../constant/controller";
import toast from "../../component/toast";
import ErrorIcon from "../../assets/icon/ErrorIcon";
import NoWifiIcon from "../../assets/icon/NoWifiIcon";

/**
 * 登入
 * @param email
 * @param password
 * @response 0 | 成功；1 | 账号密码不匹配；2 | 不存在
 */
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
                // 关闭登录
                dispatch({
                    type: HIDDEN_LOGIN
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

/**
 * 发送验证码
 * @param email
 * @response 0 | 成功；1 | 发送错误？各种问题
 */
const sendEmail = (email: string) => {
    return (dispatch: any) => {
        axios({
            url: '/api/user/code',
            method: 'post',
            data: {
                email: email,
            },
            timeout: 2000
        }).then(res => {
            if (res.data.code === 20000) toast("success", "邮件发送成功")
            else toast("fail", "邮件发送失败, " + res.data.message, <ErrorIcon/>)
        }).catch(err => {
            toast("fail", '请检查网络连接', <NoWifiIcon/>)
        })
    }
}

/**
 * 注册用户
 * @param email
 * @param password
 * @param code
 * @response 0 | 成功；1 | 用户名被注册；2 | 验证码错误
 */
const register = (email: string, password: string, code: string) => {
    return (dispatch: any) => {
        axios({
            url: '/api/user/register',
            method: 'post',
            data: {
                email: email,
                password: password,
                code: code
            },
            timeout: 2000
        }).then(res => {
            if (res.data.code === 20000) {
                // dispatch({
                //     type: REGISTER_USER,
                //     payload: {...res.data}
                // })
                toast("success", "注册成功")
                // 关闭注册
                dispatch({
                    type: HIDDEN_REGISTER
                })
                // 打开登录
                dispatch({
                    type: SHOW_LOGIN
                })
            }
            else toast("fail", res.data.message, <ErrorIcon/>)
        }).catch(err => {
            toast("fail", '请检查网络连接', <NoWifiIcon/>)
        })
    }
}

/**
 * 修改用户信息
 * @param email
 * @param password
 * @param code
 * @response 0 | 成功；1 | 验证码错误；2 | 修改错误？没有权限
 */
const modify = (email: string, password: string, code: string) => {
    return (dispatch: any) => {
        axios({
            url: '/api/user/modify',
            method: 'post',
            data: {
                email: email,
                password: password,
                code: code
            },
            timeout: 2000,
        }).then(res => {
            if (res.data.code === 20000) {
                // dispatch({
                //     type: MODIFY_USER,
                //     payload: {...res.data}
                // })
                // 关闭忘记密码
                dispatch({
                    type: HIDDEN_FORGET
                })
                // 打开登录
                dispatch({
                    type: SHOW_LOGIN
                })
                toast("success", "修改成功")
            }
            else toast("fail", res.data.message, <ErrorIcon/>)
        }).catch(err => {
            toast("fail", '请检查网络链连接', <NoWifiIcon/>)
        })
    }
}

export {
    login,
    logout,
    sendEmail,
    register,
    modify
}