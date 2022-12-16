import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const Svg = () => (
    <svg d="1667738501234" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="7398" width="20" height="20">
        <path
            d="M644.8 581.568l160.64 187.456a64 64 0 0 1-48.597 105.643H267.157a64 64 0 0 1-48.597-105.643l160.661-187.435a253.813 253.813 0 0 0 61.206 26.944l-173.27 202.134h489.686l-173.27-202.134a254.613 254.613 0 0 0 61.227-26.965zM512 149.333c117.824 0 213.333 95.51 213.333 213.334S629.824 576 512 576s-213.333-95.51-213.333-213.333S394.176 149.333 512 149.333z m0 64A149.333 149.333 0 1 0 512 512a149.333 149.333 0 0 0 0-298.667z"
            p-id="7399"></path>
    </svg>
);

/**
 * 用户图标
 * @param props
 * @constructor
 */
const UserIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={Svg} {...props} />
);

export default UserIcon
