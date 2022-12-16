import {configureStore} from "@reduxjs/toolkit";
import reactThunk from 'redux-thunk'
import reducer from '../reducer'
import {persistStore} from "redux-persist";

const preloadedState:any = {
    UserInfo: {
        id: '',
        role: -1,
        nickName: '',
        grade: 0,
        avatar: '',
        name: '',
        number: '',
        college: '',
        destination: '',
        contact: {
            qq: '',
            github: '',
            blog: '',
        },
        menu: [
            {
                label: '登录',
                key: '/login',
                icon: undefined,
                children: undefined
            }
        ],
    }
}

const Store = configureStore({
    reducer,
    middleware: [reactThunk],
    preloadedState
})

const persistor = persistStore(Store)

export {Store, persistor}