import React from 'react';

/**
 * 页脚
 * @constructor
 */
function Footer() {
    return (
        <div style={{
            width: '100%',
            height: '5vh',
            display: 'inline-block',
            marginTop: '6vh',
            marginBottom: 0,
            backgroundColor: '#f5f5f5'
        }}>
            <p style={{float: 'right'}}>Power By: Snak</p>
        </div>
    );
}

export default Footer;