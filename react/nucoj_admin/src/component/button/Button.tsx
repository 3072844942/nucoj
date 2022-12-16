import * as React from 'react';
import {NavLink} from "react-router-dom";
import {useState} from "react";

interface ButtonInfo {
    context: string | number, // 文字内容
    color: string, // 底色
    fontColor: string, // 文字颜色
    size: number, // 按钮大小
    enter: boolean, // 进入特效
    link?: string // 点击链接,
    onClick?: Function,
    onKeyDown?: Function,
    style?: object
}

/**
 * 自定义按钮类
 * 不是很好用， 可能会全部替换
 * @param props
 * @constructor
 */
function Button(props: ButtonInfo) {
    const [enter, setEnter] = useState<boolean>(false);

    return (
        <div
            style={{
                ...props.style,
                width: props.size + "vw",
                height: props.size / 3 * 3 + "vh",
                lineHeight: props.size / 3 * 3 + "vh",
                textAlign: "center",
                alignContent: "center",
                backgroundColor: props.color,
                borderRadius: props.enter ? (enter ? props.size * 10 : props.size * 2) : props.size * 10,
                boxShadow: "0 2px 2px 2px rgba(7, 17, 27, .15)",
                transition: '1s',
                cursor: 'pointer',
                userSelect: 'none'
            }}
            onClick={() => {
                props.onClick != null && props.onClick()
            }}
            onKeyDown={() => {
                props.onKeyDown != undefined && props.onKeyDown()
            }}
            onMouseEnter={() => setEnter(props.enter)}
            onMouseLeave={() => setEnter(false)}
        >
            {
                // 这里Navlink可能导致页面不能跳转到其他页面，于是单独判断是否不同于当前页面
                props.link ? (
                    props.link?.startsWith("https") ? <a href={props.link} target={'_blank'}>
                        <p style={{
                            color: props.fontColor,
                            fontSize: props.size * 2 + "px",
                        }}>{props.context}</p>
                    </a> : <NavLink to={props.link}>
                        <p style={{
                            color: props.fontColor,
                            fontSize: props.size * 2 + "px",
                        }}>{props.context}</p>
                    </NavLink>
                ) : <p style={{
                    color: props.fontColor,
                    fontSize: props.size * 2 + "px",
                }}>{props.context}</p>

            }
        </div>
    );
}

export default Button;