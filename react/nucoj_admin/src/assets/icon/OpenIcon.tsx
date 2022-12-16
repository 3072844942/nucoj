import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const Svg = () => (
    <svg d="1667738887956" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="7772" width="28" height="28">
        <path
            d="M853.333333 725.034667v64H170.666667v-64h682.666666z m-149.333333-309.333334l149.333333 106.666667-149.333333 106.666667v-213.333334z m-85.333333 74.666667v64H170.666667v-64h448zM853.333333 256v64H170.666667v-64h682.666666z"
            p-id="7773"></path>
    </svg>
);

/**
 * 展开图标
 * @param props
 * @constructor
 */
const OpenIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={Svg} {...props} />
);

export default OpenIcon
