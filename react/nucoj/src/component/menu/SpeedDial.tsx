import * as React from 'react';
import Box from '@mui/material/Box';
import SpeedDial from '@mui/material/SpeedDial';
import SpeedDialIcon from '@mui/material/SpeedDialIcon';
import SpeedDialAction from '@mui/material/SpeedDialAction';
import BackTopIcon from "../../assets/icon/BackTopIcon";

import Draggable from 'react-draggable';
const actions = [
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
    }
];

/**
 * 右下角菜单
 * @constructor
 */
export default function BasicSpeedDial() {
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
