import React, {ReactNode} from 'react';
import {connect} from "react-redux";

/**
 * 文本容器
 * 用法与基本容器一致， 但是内容只能是字符串
 * @param props
 * @constructor
 */
function TextContainer(props: {
    title: string,
    style?: object
    width: string | number
    children: ReactNode
}) {

    return (
        <div style={{
            width: props.width,
            padding: '20px',
            display: 'block',
            position: 'relative',
            boxShadow: "0 4px 4px 4px rgba(7, 17, 27, .05)",
            borderRadius: '10px',
            ...props.style
        }}>
            <div style={{
                display: 'flex',
                // position: 'absolute',
                // left: 0,
                borderLeft: '3px solid #0081ff',
                fontSize: '16px',
            }}>
                <p style={{marginLeft: '2vw'}}>{props.title}</p>:
                <p style={{marginLeft: '2vw', width: "60%"}}>{props.children}</p>
            </div>
        </div>
    );
}

export default TextContainer;