import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const Svg = () => (
    <svg d="1667736988299" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="6762" width="20" height="20">
        <path
            d="M535.253333 180.693333l288.490667 183.914667A64 64 0 0 1 853.333333 418.581333v392.746667a64 64 0 0 1-64 64H213.333333a64 64 0 0 1-64-64v-398.293333a64 64 0 0 1 30.272-54.4l287.530667-178.346667a64 64 0 0 1 68.138667 0.426667zM500.885333 234.666667L213.333333 413.013333v398.336h576V418.56L500.864 234.666667zM682.666667 640v64H320v-64h362.666667z m0-106.666667v64H320v-64h362.666667z"
            p-id="6763"></path>
    </svg>
);

/**
 * 首页图标
 * @param props
 * @constructor
 */
const HomeIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={Svg} {...props} />
);

export default HomeIcon
