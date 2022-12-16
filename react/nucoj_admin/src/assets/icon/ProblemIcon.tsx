import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const Svg = () => (
    <svg d="1667738176688" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="6762" width="20" height="20">
        <path
            d="M490.666667 533.333333v256a64 64 0 0 1-64 64h-192a64 64 0 0 1-64-64v-192a64 64 0 0 1 64-64h256z m298.666666 0a64 64 0 0 1 64 64v192a64 64 0 0 1-64 64h-192a64 64 0 0 1-64-64V533.333333h256z m-362.666666 64h-192v192h192v-192z m362.666666 0h-192v192h192v-192zM426.666667 170.666667a64 64 0 0 1 64 64v256H234.666667a64 64 0 0 1-64-64v-192a64 64 0 0 1 64-64h192z m266.666666 0a160 160 0 1 1 0 320 160 160 0 0 1 0-320zM426.666667 234.666667h-192v192h192v-192z m266.666666 0a96 96 0 1 0 0 192 96 96 0 0 0 0-192z"
            p-id="6763"></path>
    </svg>
);

/**
 * 题库图标
 * @param props
 * @constructor
 */
const ProblemIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={Svg} {...props} />
);

export default ProblemIcon
