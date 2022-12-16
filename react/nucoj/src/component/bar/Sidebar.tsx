import React, {useEffect, useState} from 'react';
import ICON from '../../assets/icon/Favicon'
import ProblemIcon from "../../assets/icon/ProblemIcon";
import {NavLink, useLocation} from "react-router-dom";
import {Divider} from "antd";
import DiscussIcon from "../../assets/icon/DiscussIcon";
import ContestIcon from "../../assets/icon/ContestIcon";
import TrainIcon from "../../assets/icon/TarinIcon";
import SolutionIcon from "../../assets/icon/SolutionIcon";

interface Link {
    name: string,
    icon: any,
    link: string
}

const MenuLink: Array<Link> = [
    {
        name: '题库',
        icon: <ProblemIcon width={1} style={{margin: 'auto'}}/>,
        link: '/problem'
    },
    {
        name: '题单',
        icon: <TrainIcon width={1} style={{margin: 'auto'}}/>,
        link: '/train'
    },
    {
        name: '比赛',
        icon: <ContestIcon width={1} style={{margin: 'auto'}}/>,
        link: '/contest'
    },
    {
        name: '题解',
        icon: <SolutionIcon width={1} style={{margin: 'auto'}}/>,
        link: '/solution'
    },
    {
        name: '分享',
        icon: <DiscussIcon width={1} style={{margin: 'auto'}}/>,
        link: '/discuss'
    },
]

/**
 * 侧边栏
 * @constructor
 */
function SideBar() {
    const [url, setUrl] = useState<string>(useLocation().pathname)

    useEffect(() => {
        setUrl(window.location.pathname)
    }, [window.location.pathname])

    return (
        <>
            {
                url.startsWith("/contest/") ? null : <div style={{
                    backgroundColor: '#fff',
                    height: '100vh',
                    width: '3vw',
                    left: "0",
                    top: "0",
                    position: 'fixed',
                    boxShadow: "0 4px 4px 4px rgba(7, 17, 27, .15)",
                    zIndex: '1'
                }}>
                    {/*OJ标题*/}
                    <div style={{
                        width: '3vw',
                        height: '6vh',
                    }}>
                        <NavLink to={'/index'} style={{
                            width: '100%', height: '100%', display: 'flex',
                            alignItems: 'center',
                            justifyItems: 'center'
                        }}>
                            <div style={{
                                margin: 'auto'
                            }}>
                                <ICON/>
                            </div>
                        </NavLink>
                    </div>
                    {/*分割线*/}
                    <Divider></Divider>
                    {/*数组遍历生成菜单*/}
                    <div>
                        {
                            MenuLink.map(item => <div style={{
                                display: 'flex',
                                justifyItems: 'center',
                                alignItems: 'center'
                            }} key={item.link}>
                                <NavLink key={item.link} to={item.link} style={{
                                    color: 'black',
                                    width: '100%',
                                    height: '100%',
                                    marginBottom: '2vh',
                                    display: 'flex',
                                    alignItems: 'center',
                                    justifyItems: 'center',
                                    flexWrap: 'wrap'
                                }}>
                                    {item.icon}
                                    <p key={item.link} style={{flexBasis: '100%', textAlign: 'center'}}>{item.name}</p>
                                </NavLink>
                            </div>)
                        }
                    </div>
                </div>
            }
        </>
    );
}

export default SideBar;
