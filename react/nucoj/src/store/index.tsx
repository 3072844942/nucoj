import {configureStore} from "@reduxjs/toolkit";
import reactThunk from 'redux-thunk'
import reducer from '../reducer'
// import logger from 'redux-logger'
import {persistStore} from "redux-persist";

const preloadedState:any = {
    UserInfo: {
        id: "",
        nickname: "",
        avatar: "",
        grade: 0,
    },
    Controller: {
        login: false, // 是否显示登录框
        register: false, // 是否显示注册框
        forget: false, // 是否显示忘记密码框
        title: undefined,
        websocketUrl: undefined,
    }
}

const Store = configureStore({
    reducer,
    middleware: [reactThunk],
    preloadedState
})

const persistor = persistStore(Store)

export {Store, persistor}