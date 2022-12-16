import React from 'react';

/**
 * 背景图
 * @param props
 * @constructor
 */
function Background(props:{
    color: string,
    onClick?: Function
}) {
    return (
        <div style={{
            width: '100vw',
            height: '100vh',
            opacity: '.46',
            backgroundColor: props.color,
            position: 'absolute',
            top: '0',
            left: '0',
            zIndex: '999'
        }} onMouseDown={() => props.onClick && props.onClick()}></div>
    );
}

export default Background;