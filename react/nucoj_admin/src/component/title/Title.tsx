import React, {ReactNode} from 'react';

/**
 * 标题
 * 无意义的简单抽象
 * @param props
 * @constructor
 */
function Title(props: {
    value?:string,
    children?: ReactNode
}) {

    return (
        <h2 style={{
            fontWeight: 'bolder',
            margin: '1vh 0'
        }}>
            {props.value}
            {props.children}
        </h2>
    );
}

export default Title;