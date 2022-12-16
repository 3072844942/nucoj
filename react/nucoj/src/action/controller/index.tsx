import {
    HIDDEN_FORGET,
    HIDDEN_LOGIN,
    HIDDEN_REGISTER,
    SHOW_FORGET,
    SHOW_LOGIN,
    SHOW_REGISTER, SUCCESS, UPDATE_BLOG
} from "../../constant/controller";
import toast from "../../component/toast";
import axios from "axios";

const showLogin = () => {
    return {
        type: SHOW_LOGIN
    }
}

const hiddenLogin = () => {
    return {
        type: HIDDEN_LOGIN
    }
}

const showRegister = () => {
    return {
        type: SHOW_REGISTER
    }
}

const hiddenRegister = () => {
    return {
        type: HIDDEN_REGISTER
    }
}

const showForget = () => {
    return {
        type: SHOW_FORGET
    }
}

const hiddenForget = () => {
    return {
        type: HIDDEN_FORGET
    }
}

const getBlogInfo = () => {
    return (dispatch: any) => {
        axios({
            url: '/api/',
            method: 'get'
        }).then(res => {
            if (res.data.code === SUCCESS) {
                dispatch({
                    type: UPDATE_BLOG,
                    payload: {...res.data.data}
                })
            }
            else toast("fail", res.data.message)
        })
    }
}

export {
    showLogin,
    hiddenLogin,
    showRegister,
    hiddenRegister,
    showForget,
    hiddenForget,
    getBlogInfo
}