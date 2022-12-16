import {persistReducer} from 'redux-persist'
import storage from 'redux-persist/lib/storage'
import {combineReducers} from "redux";
import UserReducer from "./UserReducer";
import ControllerReducer from "./ControllerReducer";

const persistConfig = {
    key: 'root',
    storage,
}

const reducer = combineReducers({
    UserInfo: UserReducer,
    Controller: ControllerReducer
})
const persistedReducer = persistReducer(persistConfig, reducer)

export default persistedReducer