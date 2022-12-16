import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const Svg = () => (
    <svg d="1667737686596" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="6933" width="20" height="20">
        <path
            d="M874.666667 170.666667v64h-42.666667v426.666666c0 35.349333-30.72 64-68.565333 64h-149.354667l113.749333 128h-85.632l-113.770666-128h-11.562667l-113.749333 128h-85.610667l113.728-128h-170.666667C222.72 725.333333 192 696.682667 192 661.333333V234.666667H149.333333V170.666667h725.333334z m-106.666667 64H256v426.666666h512V234.666667zM405.333333 469.333333v64h-64v-64h64z m277.333334 0v64H448v-64h234.666667z m0-106.666666v64H448v-64h234.666667z m-277.333334 0v64h-64v-64h64z"
            p-id="6934"></path>
    </svg>
);

/**
 * 题单图标
 * @param props
 * @constructor
 */
const TrainIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={Svg} {...props} />
);

export default TrainIcon
