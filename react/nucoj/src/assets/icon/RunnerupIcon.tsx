import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const RunnerupSvg = () => (
    <svg d="1664935741459" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="3265" width="26" height="26">
        <path
            d="M899.6 276.5L705 396.4 518.4 147.5c-3.2-4.3-9.6-4.3-12.9 0L319 396.4 124.3 276.5c-5.7-3.5-13.1 1.2-12.2 7.9L188.5 865c1.1 7.9 7.9 14 16 14h615.1c8 0 14.9-6 15.9-14l76.4-580.6c0.8-6.7-6.5-11.4-12.3-7.9z m-126 534.1H250.3l-53.8-409.4 139.8 86.1L512 252.9l175.7 234.4 139.8-86.1-53.9 409.4zM512 509c-62.1 0-112.6 50.5-112.6 112.6S449.9 734.2 512 734.2s112.6-50.5 112.6-112.6S574.1 509 512 509z m0 160.9c-26.6 0-48.2-21.6-48.2-48.3 0-26.6 21.6-48.3 48.2-48.3s48.2 21.6 48.2 48.3c0 26.6-21.6 48.3-48.2 48.3z"
            p-id="3266" fill="#c0c0c0"></path>
    </svg>
);

/**
 * 亚军图标
 * @param props
 * @constructor
 */
const RunnerupIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={RunnerupSvg} {...props} />
);

export default RunnerupIcon
