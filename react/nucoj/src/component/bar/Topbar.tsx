import React from 'react';
import {connect} from "react-redux";
import Button from "../button/Button";
import {logout} from '../../action/user'
import Draggable from 'react-draggable';
import {Dropdown, Menu, Progress, Space} from 'antd';
import {Avatar} from 'antd';
import {showLogin} from "../../action/controller";

/**
 * 顶部栏
 * @param props
 * @constructor
 */
function TopBar(props: any) {
    const menu = (
        <Menu
            items={[
                {
                    key: 'name',
                    label: props.userInfo.nickname,
                    type: 'group'
                },
                {
                    key: 'self',
                    label: '个人管理',
                    onClick: () => {
                        window.open('https://www.admin.snak.space/login', '_blank')
                        // history('http://localhost:4000')
                    }
                },
                {
                    key: 'logout',
                    label: '注销',
                    onClick: () => {
                        props.logout()
                    }
                },
            ]}
        />
    );

    const getColor = (role: number) => {
        if (role === 0) return 'red'
        else if (role === 1) return 'green'
        else if (role === 2) return 'blue'
        else return 'black'
    }

    return (
        // 可拖动
        // <Draggable>
            <div style={{
                height: '6vh',
                position: 'absolute',
                top: 0,
                right: 0,
                display: 'inline-block',
                zIndex: '1'
            }}>
                {
                    // 当没有登陆时，显示登录注册按钮， 否则显示个人信息
                    props.userInfo.id === "" ? <div style={{
                        float: 'right',
                        marginTop: '1vh',
                        marginRight: '2vw'
                    }}>
                        <Button
                            color={'#fff'}
                            fontColor={'green'}
                            context={'登录/注册'}
                            enter={false}
                            size={6}
                            onClick={() => {
                                props.showLogin()
                            }}
                        />
                    </div> : <div style={{
                        width: '160px',
                        height: '100%',
                        float: 'right',
                        paddingRight: '1vw',
                        marginRight: '3vw',
                        marginTop: '1vh',
                        backgroundColor: '#fff',
                        borderRadius: '100px',
                        boxShadow: "0 4px 4px 4px rgba(7, 17, 27, .15)",
                        display: 'flex',
                        alignItems: 'center',
                        justifyItems: 'center',
                        justifyContent: 'space-between',
                    }}>
                        {/*进度条上等级信息*/}
                        <p style={{
                            position: 'relative',
                            left: '22px',
                            top: '-3px',
                            color: getColor(props.userInfo.role)
                        }}>
                            lv.{(props.userInfo.grade - props.userInfo.grade % 100) / 100}
                        </p>
                        {/*进度条， 以grade后两位表示*/}
                        <Progress strokeColor={{
                            from: '#108ee9',
                            to: '#87d068',
                        }} status="active" style={{width: '100px', marginRight: '10px'}}
                                  percent={props.userInfo.grade % 100} showInfo={false}/>
                        {/*// 当用户登录时 鼠标放上去下拉菜单*/}
                        <Dropdown overlay={menu}>
                            <Space>
                                <Avatar style={{
                                    boxShadow: "0 1px 1px 1px rgba(7, 17, 27, .1)",
                                }}
                                        src={props.userInfo.avatar.startsWith('https')
                                            ? props.userInfo.avatar
                                            : "https://joeschmoe.io/api/v1/random"}/>
                            </Space>
                        </Dropdown>
                    </div>
                }
            </div>
        // </Draggable>
    );
}

const mapStateToProps = (state: any) => {
    return {
        userInfo: state.UserInfo
    }
}

const mapDispatchToProps = {
    showLogin() {
        return showLogin();
    },

    logout() {
        return logout()
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(TopBar);