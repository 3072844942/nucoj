import * as React from 'react';
import JPG from '../../assets/img/404.jpg'
import {useEffect} from "react";

/**
 * 404页面
 * @constructor
 */
function Index() {
    useEffect(() => {
        document.title = '找不到啦！！！'
    }, [])

    return (
        <div style={{
            paddingLeft: '3vw',
        }}>
            <div style={{
                width: '100%',
                height: '100vh',
                background: 'url(' + JPG + ') center center / cover no-repeat',
            }}>
            </div>
        </div>
    );
}

export default Index;