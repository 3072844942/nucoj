import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const Svg = () => (
    <svg d="1667738992440" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="7951" width="20" height="20">
        <path
            d="M640 170.666667a64 64 0 0 1 64 64v490.666666h-64V234.666667H213.333333v554.666666h597.333334V362.666667h-64v-64h64a64 64 0 0 1 64 64v426.666666a64 64 0 0 1-64 64H213.333333a64 64 0 0 1-64-64V234.666667a64 64 0 0 1 64-64h426.666667z m-192 320v64h-170.666667v-64h170.666667z m128-128v64H277.333333v-64h298.666667z"
            p-id="7952"></path>
    </svg>
);

/**
 * 题解图标
 * @param props
 * @constructor
 */
const Solution = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={Svg} {...props} />
);

export default Solution
