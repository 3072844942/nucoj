import * as React from 'react';
import RouterView from "./router/RouterView";
import './App.scss'
import './assets/js/hidden'
import {connect} from "react-redux";
import Login from "./component/login/Login";
import Register from "./component/login/Register";
import Forget from "./component/login/Forget";
import {Menu} from "antd";
import ProblemIcon from "./assets/icon/ProblemIcon";
import 'antd/dist/antd.min.css'
import 'highlight.js/styles/atom-one-light.css'
import './assets/css/markdown.scss'
import {login, logout} from "./action/user";
import {useEffect} from "react";
import Topbar from "./component/bar/Topbar";
import Sidebar from "./component/bar/Sidebar";
import BasicSpeedDial from "./component/menu/SpeedDial";
import dayjs from "dayjs";

// TODO 右键菜单 暂时搁浅
const menu = (
    <Menu
        items={[
            {
                label: '1st menu item',
                key: '1',
                children: [{label: '子菜单项1', key: 'submenu-item-1'},
                    {label: '子菜单项2', key: 'submenu-item-2'}],
                onClick: () => {
                }
            },
            {
                label: '2nd menu item',
                key: '2',
                icon: <ProblemIcon/>
            },
            {
                type: 'divider',
            },
            {
                label: '3rd menu item',
                key: '3',
                disabled: true
            },
        ]}
    />
);

function App(props: any) {
    useEffect(() => {
        const email = localStorage.getItem("email")
        const password = localStorage.getItem("password")
        if (email !== null && password !== null) {
            props.loginUser(email, password)
        }
    }, [])

    // 关闭页面时自动退出？
    // useEffect(() => {
    //     const listener = () => {
    //         props.logout()
    //     };
    //     window.addEventListener('beforeunload', listener);
    //     return () => {
    //         window.removeEventListener('beforeunload', listener)
    //     }
    // }, []);

    return (
        // 右键菜单
        // <Dropdown overlay={menu} trigger={['contextMenu']}>
        //     <div className="site-dropdown-context-menu">

        <div style={{
            width: '100vw',
            height: '100vh'
        }}>
            <Topbar/>
            <Sidebar/>
            <div style={{
                width: '100vw',
                height: '100vh',
                position: 'absolute',
                top: 0,
                left: 0
            }}>
                <RouterView/>
            </div>
            {props.login === true && <Login/>}
            {props.register === true && <Register/>}
            {props.forget === true && <Forget/>}
            <BasicSpeedDial/>
        </div>
             // </div>
        // </Dropdown>
    );
}

const mapStateToProps = (state: any) => {
    return {
        login: state.Controller.login,
        register: state.Controller.register,
        forget: state.Controller.forget
    }
}

const mapDispatchToProps = {
    loginUser(email: string, password: string) {
        return login(email, password);
    },
    logout() {
        return logout()
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(App);