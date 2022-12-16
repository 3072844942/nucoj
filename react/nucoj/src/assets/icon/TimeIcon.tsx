import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const TimeSvg = () => (
    <svg d="1664875979354" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="5424" width="14" height="14">
        <path
            d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64z m0 820c-205.4 0-372-166.6-372-372s166.6-372 372-372 372 166.6 372 372-166.6 372-372 372z"
            p-id="5425" fill="#ffffff"></path>
        <path
            d="M686.7 638.6L544.1 535.5V288c0-4.4-3.6-8-8-8H488c-4.4 0-8 3.6-8 8v275.4c0 2.6 1.2 5 3.3 6.5l165.4 120.6c3.6 2.6 8.6 1.8 11.2-1.7l28.6-39c2.6-3.7 1.8-8.7-1.8-11.2z"
            p-id="5426" fill="#ffffff"></path>
    </svg>
);

/**
 * 时间图标
 * @param props
 * @constructor
 */
const TimeIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={TimeSvg} {...props} />
);

export default TimeIcon
