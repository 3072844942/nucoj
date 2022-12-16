import React, {ReactNode} from 'react';
import {connect} from "react-redux";

/**
 * 文字容器
 * @param props
 * @constructor
 */
function WordContainer(props: {
    children: ReactNode,
    style?: object
}) {

    return (
        <div style={{
            width: '94%',
            fontSize: '1.2em',
            margin: '3% 0',
            padding: '1% 3%',
            borderRadius: '6px',
            border: '1px solid #E8E8EA',
            backgroundColor: '#F6F7F9',
            ...props.style
        }}>
            {props.children}
        </div>
    );
}

export default WordContainer;