import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const WordSvg = () => (
    <svg d="1664941982567" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="1806" width="26" height="26">
        <path
            d="M512.6 719c-35.2 0-70.8-9.2-98.6-27L165 529.4c-46.4-30.2-74-81.2-74-136.6s27.8-106.4 74-136.6L414.6 94c55.4-36 142-36 197 0.4l248 162.6c46 30.2 73.6 81.2 73.6 136.2 0 55-27.8 106-73.6 136.2l-248 162.6c-27.8 18.2-63.4 27-99 27z m0-589.8c-23.6 0-47.2 5.4-64.2 17L199.2 308.4c-29 19-46 49.6-46 84.4s16.6 65.4 46 84.4L448.4 640c34.4 22.4 94.8 22.4 129.2 0l248-162.6c29-19 45.6-49.6 45.6-84.4s-16.6-65.4-45.6-84.4L577.6 146c-17.4-11-41-16.8-65-16.8z"
            p-id="1807"></path>
        <path
            d="M512.2 957c-18.2 0-36.8-2.4-51.8-7.4l-132-43.8c-62.6-20.6-111.8-89-111.4-154.8l0.4-194.2c0-17 14-31 31-31s31 14 31 31l-0.4 194.2c0 39 32.2 83.6 69.2 96l132 43.8c16.6 5.4 47.2 5.4 63.8 0l132-43.8c36.8-12.4 69.2-57.2 69.2-95.6v-192c0-17 14-31 31-31s31 14 31 31v192c0 65.8-48.8 133.6-111.4 154.8L564 950c-15 4.6-33.6 7-51.8 7zM901.4 667.2c-17 0-31-14-31-31V387.8c0-17 14-31 31-31s31 14 31 31v248.4c0 17-14.2 31-31 31z"
            p-id="1808"></path>
    </svg>
);

/**
 * 更新图标
 * @param props
 * @constructor
 */
const WordIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={WordSvg} {...props} />
);

export default WordIcon
