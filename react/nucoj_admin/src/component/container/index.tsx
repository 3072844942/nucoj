import React, {ReactNode} from 'react';

interface container {
    title: string,
    style?: object
    width: string | number
    children: ReactNode
}

/**
 * 容器
 * @param props
 * @constructor
 */
function Index(props:container) {
    return (
        <div style={{
            width: props.width,
            padding: '20px',
            display: 'block',
            position: 'relative',
            boxShadow: "0 4px 4px 4px rgba(7, 17, 27, .05)",
            borderRadius: '10px',
            minHeight: '60vh',
            ...props.style
        }}>
            <div style={{
                position: 'absolute',
                left: 0,
                borderLeft: '3px solid #0081ff',
                fontSize: '16px'
            }}>
                <p style={{marginLeft: '2vw', height: '2vh'}}>{props.title}</p>
            </div>
            <div style={{
                marginTop: '6vh',
                position: 'relative'
            }}>
                {props.children}
            </div>
        </div>
    );
}

export default Index;