import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const Svg = () => (
    <svg d="1667738858463" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="7593" width="28" height="28">
        <path
            d="M853.333333 714.666667v64H170.666667v-64h682.666666zM320 405.333333v213.333334l-149.333333-106.666667 149.333333-106.666667z m533.333333 85.333334v64H405.333333v-64h448z m0-234.666667v64H170.666667v-64h682.666666z"
            p-id="7594"></path>
    </svg>
);

/**
 * 收起图标
 * @param props
 * @constructor
 */
const StowIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={Svg} {...props} />
);

export default StowIcon
