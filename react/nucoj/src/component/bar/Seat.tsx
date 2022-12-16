import React from 'react';

/**
 * 底部
 * 原本想放一些信息， 但是现在被用来占位
 * @param props
 * @constructor
 */
function Seat(props:{height:string | number}) {
    return (
        <div style={{
            minHeight: props.height,
            width: '100%'
        }}></div>
    );
}

export default Seat;