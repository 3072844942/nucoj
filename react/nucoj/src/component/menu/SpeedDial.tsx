import * as React from 'react';
import Box from '@mui/material/Box';
import SpeedDial from '@mui/material/SpeedDial';
import SpeedDialIcon from '@mui/material/SpeedDialIcon';
import SpeedDialAction from '@mui/material/SpeedDialAction';
import BackTopIcon from "../../assets/icon/BackTopIcon";

import Draggable from 'react-draggable';
import {useNavigate} from "react-router-dom";
import ProblemIcon from "../../assets/icon/ProblemIcon";
import TrainIcon from "../../assets/icon/TarinIcon";
import ContestIcon from "../../assets/icon/ContestIcon";
import SolutionIcon from "../../assets/icon/SolutionIcon";
import DiscussIcon from "../../assets/icon/DiscussIcon";
import BackIcon from "../../assets/icon/BackIcon";


/**
 * 右下角菜单
 * @constructor
 */
export default function BasicSpeedDial() {
    const history = useNavigate()

    const actions = [
        {
            icon: <BackIcon/>,
            name: '返回',
            onClick: () => {
                history(-1)
            }
        },
        {
            icon: <BackTopIcon/>,
            name: '回到顶部',
            onClick: () => {
                let timer = setInterval(() => {
                    let isScroll = true;
                    // 距内容区最顶部的距离
                    let osTop = document.documentElement.scrollTop || document.body.scrollTop;
                    // 改变回到顶部的速度（越来越慢）
                    let speed = Math.ceil(-osTop / 1.05);
                    document.body.scrollTop = document.documentElement.scrollTop -= (osTop + speed);
                    if (speed == 0) {
                        clearInterval(timer);
                        isScroll = false;
                    }
                }, 10)
                return () => {
                    clearInterval(timer)
                }
            }
        },
        {
            icon: <ProblemIcon/>,
            name: '题库',
            onClick: () => {
                history('/problem')
            }
        },
        {
            icon: <TrainIcon/>,
            name: '题单',
            onClick: () => {
                history('/train')
            }
        },
        {
            icon: <ContestIcon/>,
            name: '比赛',
            onClick: () => {
                history('/contest')
            }
        },
        {
            icon: <SolutionIcon/>,
            name: '题解',
            onClick: () => {
                history('/solution')
            }
        },
        {
            icon: <DiscussIcon/>,
            name: '分享',
            onClick: () => {
                history('/discuss')
            }
        }
    ];
    return (
        <Draggable>
            <Box sx={{ transform: 'translateZ(0px)', flexGrow: 1 }} style={{position: 'fixed', right: '0', bottom: '0'}}>
                <SpeedDial
                    ariaLabel="SpeedDial basic example"
                    sx={{ position: 'absolute', bottom: 16, right: 16 }}
                    icon={<SpeedDialIcon />}
                >
                    {actions.map((action) => (
                        <SpeedDialAction
                            key={action.name}
                            icon={action.icon}
                            tooltipTitle={action.name}
                            onClick={() => action.onClick !== undefined && action.onClick()}
                        />
                    ))}
                </SpeedDial>
            </Box>
        </Draggable>
    );
}
