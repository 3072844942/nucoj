import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const RightArrowSvg = () => (
    <svg d="1665143770114" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="2542" width="26" height="26">
        <path
            d="M918.6 489.3L646.1 216.8c-12.3-12.3-32.5-12.3-44.8 0l-0.7 0.7c-12.3 12.3-12.3 32.4 0 44.7l218.7 218.7H257.6c-17.3 0-31.5 14.2-31.5 31.5s14.2 31.5 31.5 31.5h560.1L601.2 760.5c-12.5 12.5-12.5 32.9 0 45.4s32.9 12.5 45.4 0l271.1-271.1s0.1-0.1 0.2-0.1l0.7-0.7c12.3-12.3 12.3-32.4 0-44.7z"
            fill="#515151" p-id="2543"></path>
        <path d="M97.6 512.6a31.8 31.7 0 1 0 63.6 0 31.8 31.7 0 1 0-63.6 0Z" fill="#515151" p-id="2544"></path>
    </svg>
);

/**
 * 向右图标
 * @param props
 * @constructor
 */
const RightArrowIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={RightArrowSvg} {...props} />
);

export default RightArrowIcon
