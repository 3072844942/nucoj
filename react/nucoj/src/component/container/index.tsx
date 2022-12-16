import React, {ReactNode} from 'react';

interface container {
    title: string,
    icon?: ReactNode,
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
            boxShadow: "0 4px 4px 4px rgba(7, 17, 27, .05)",
            borderRadius: '10px',
            ...props.style
        }}>
            <div style={{
                borderRadius: '10px 10px 0 0',
                backgroundColor:'#f3f4f5',
                height: 40,
                display: 'flex',
                paddingTop: '.5%'
            }}>
                <div style={{ display: 'flex', margin: 'auto', justifyContent: 'space-between'}}>
                    {props.icon}&nbsp;&nbsp;&nbsp;&nbsp;
                    <p style={{fontSize: '24px', position: 'relative', top: '-3px'}}>{props.title}</p>
                </div>
            </div>
            <div style={{
                borderRadius: '0 0 10px 10px',
                minHeight: '40px',
                padding: '10px'
            }}>
                {props.children}
            </div>
        </div>
    );
}

export default Index;