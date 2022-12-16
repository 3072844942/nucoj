import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const ContextSvg = () => (
    <svg d="1665802904190" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="9694" width="32" height="32">
        <path
            d="M784.2 62.9H239.8c-57.4 0-103.8 46.4-103.8 103.8v690.6c0 57.4 46.4 103.8 103.8 103.8h544.4c57.4 0 103.8-46.4 103.8-103.8V166.7c0-57.4-46.4-103.8-103.8-103.8zM270.7 339h484.8c17.8 0 32.4 13.2 32.4 29.5S773.3 398 755.5 398H270.7c-18 0-32.4-13.2-32.4-29.5 0.1-16.3 14.5-29.5 32.4-29.5z m330.9 338.4h-0.2l-330.7-0.4c-17.8 1.9-34-9.4-36.1-25.7-2.3-16.1 10.2-30.9 28-32.8 2.7-0.4 5.4-0.4 8.1 0h330.7c18-0.2 32.6 13.2 32.6 29.5s-14.6 29.4-32.4 29.4z m157.5-139.7H270.7c-18 0.8-33.2-11.5-34.3-27.8-0.8-16.3 12.7-30.1 30.7-31.1h488.4c17.8-0.8 33.2 11.5 34 27.8 1.2 16.3-12.4 30.3-30.4 31.1z"
            p-id="9695" fill="#1296db"></path>
    </svg>
);

/**
 * 时间图标
 * @param props
 * @constructor
 */
const ContextIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={ContextSvg} {...props} />
);

export default ContextIcon
