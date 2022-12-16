import {persistReducer} from 'redux-persist'
import storage from 'redux-persist/lib/storage'
import {combineReducers} from "redux";
import UserReducer from "./UserReducer";

const persistConfig = {
    key: 'root',
    storage,
}

const reducer = combineReducers({
    UserInfo: UserReducer
})
const persistedReducer = persistReducer(persistConfig, reducer)

export default persistedReducer