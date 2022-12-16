import * as React from 'react';

import './Loading.scss'

/**
 * 加载界面
 * @constructor
 */
function Loading() {
    return (
        <div className="Loading">
            <div className="point"></div>
            <div className="point"></div>
            <div className="point"></div>
            <div className="point"></div>
            <div className="point"></div>
        </div>
    );
}

export default Loading;