import {UserInfo} from "../interface/user";
import {
    LOGIN_USER, LOGOUT_USER, GET_MENU
} from "../constant/user";

const UserReducer = (preState: UserInfo, action:any) => {
    switch (action.type) {
        case LOGIN_USER:
            return {
                ...preState,
                id: action.payload.id,
                nickname: action.payload.nickname,
                avatar: action.payload.avatar,
                grade: action.payload.grade,
                role: action.payload.role,
            }
        case LOGOUT_USER:
            return {
                ...preState,
                id: '',
                nickname: '',
                avatar: '',
                grade: 0,
                role: -1
            }
        case GET_MENU:
            return {
                ...preState,
                menu: action.payload.menu
            }
        default:
            return {...preState}
    }
}

export default UserReducer