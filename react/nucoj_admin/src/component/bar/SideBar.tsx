import React, {useEffect, useState} from 'react';
import {connect} from "react-redux";
import {getMenu} from "../../action/user";
import type { MenuProps } from 'antd';
import { Menu } from 'antd';
import {useLocation, useNavigate} from "react-router-dom";
import {MenuInfo} from "../../interface/menu";
import {getIcon} from "../../util";
import OpenIcon from "../../assets/icon/OpenIcon";
import StowIcon from "../../assets/icon/StowIcon";

/**
 * 侧边栏
 * @param props
 * @constructor
 */
function SideBar(props:any) {
    const history = useNavigate()
    const location = useLocation();

    const [openKeys, setOpenKeys] = useState('/'+location.pathname.split('/')[1]);

    const onOpenChange: MenuProps['onOpenChange'] = keys => {
        setOpenKeys(keys[1])
    };

    const onClick: MenuProps['onClick'] = e => {
        history(e.key.toString())
    };

    const check = function (menus:MenuInfo[]) {
        menus.map(item => {
            if (item.icon !== null && item.icon !== undefined && item.icon !== '') {
                item.icon = getIcon(item.icon)
            }

            if (item.children !== null && item.children !== undefined )
                item.children = check(item.children)
        })
        return menus
    }

    useEffect(() => {
        if (props.id !== '') {
            props.getMenus(props.id)
        }
    }, [props.id])

    return (
        <div style={{
            width: props.collapsed ? '3vw' : '12vw',
            height: '100vh',
        }}>
            <Menu
                onClick={onClick}
                defaultSelectedKeys={[location.pathname]}
                defaultOpenKeys={['/'+location.pathname.split('/')[1]]}
                openKeys={[openKeys]}
                onOpenChange={onOpenChange}
                mode="inline"
                items={check(props.menu)}
                // items={check(menus)}
                inlineCollapsed={props.collapsed}
                style={{
                    width: props.collapsed ? '3vw' : '12vw',
                    height: '100%',
                    position: 'fixed'
                }}
            />
            {/*收起/打开*/}
            <div
                style={{
                    width: '2vw',
                    height: '4vh',
                    boxShadow: "0 1px 1px 0 rgba(7, 17, 27, .15)",
                    position: 'fixed',
                    top: 0,
                    left: props.collapsed ? '3vw' : '12vw',
                    transition: 'left .2s ease-out'
                }}
                onClick={() => props.setCollapsed(!props.collapsed)}
            >
                {
                    props.collapsed ? <OpenIcon/> : <StowIcon/>
                }
            </div>
        </div>
    );
}

const mapStateToProps = (status:any, preProps:{
    collapsed: boolean,
    setCollapsed: Function
}) => {
    return {
        id: status.UserInfo.id,
        menu: status.UserInfo.menu,
        ...preProps
    }
}

const mapDispatchToProps = {
    getMenus(id:string) {
        return getMenu(id)
    }
}


export default connect(mapStateToProps, mapDispatchToProps)(SideBar);