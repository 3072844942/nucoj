import * as React from 'react'
import Loading from "../view/loading/Loading";
import Loadable from 'react-loadable';

/**
 * 过渡函数
 */
const loading = () => <Loading/>

export default (loader: any,load = loading)=>{
    return Loadable({
        loader: loader,
        loading: load
    });
}
