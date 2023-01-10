import {ControllerInfo} from "../interface/controller";
import {
    HIDDEN_FORGET,
    HIDDEN_LOGIN,
    HIDDEN_REGISTER,
    SHOW_FORGET,
    SHOW_LOGIN,
    SHOW_REGISTER,
    UPDATE_BLOG
} from "../constant/controller";

/**
 * 博客控制器
 * 控制一些和界面相关的全局变量
 * @param preState
 * @param action
 * @constructor
 */
const ControllerReducer = (preState: ControllerInfo, action:any) => {
    const newState = {...preState}
    switch (action.type) {
        case SHOW_LOGIN:
            newState.login = true
            return newState
        case HIDDEN_LOGIN:
            newState.login = false
            return newState
        case SHOW_REGISTER:
            newState.register = true
            return newState
        case HIDDEN_REGISTER:
            newState.register = false
            return newState
        case SHOW_FORGET:
            newState.forget = true
            return newState
        case HIDDEN_FORGET:
            newState.forget = false
            return newState
        case UPDATE_BLOG:
            newState.title = action.payload.title
            newState.websocketUrl = action.payload.websocketUrl
            return newState
        default:
            return {...preState}
    }
}

export default ControllerReducer