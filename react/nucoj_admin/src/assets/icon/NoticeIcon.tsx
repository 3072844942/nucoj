import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const Svg = () => (
    <svg d="1667738310002" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="6762" width="20" height="20">
        <path
            d="M545.536 152.96a64 64 0 0 1 20.970667 20.949333L643.264 298.666667H789.333333c35.349333 0 64 31.146667 64 69.546666v394.24c0 38.4-28.650667 69.546667-64 69.546667H234.666667c-35.349333 0-64-31.146667-64-69.546667V368.213333C170.666667 329.813333 199.317333 298.666667 234.666667 298.666667h146.048l76.778666-124.757334a64 64 0 0 1 88.042667-20.970666zM789.333333 362.666667H234.666667v405.333333h554.666666V362.666667z m-149.333333 234.666666v64H277.333333v-64h362.666667z m106.666667-149.333333v64H277.333333v-64h469.333334zM512 207.445333L455.872 298.666667h112.256L512 207.445333z"
            p-id="6763"></path>
    </svg>
);

/**
 * 公告图标
 * @param props
 * @constructor
 */
const NoticeIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={Svg} {...props} />
);

export default NoticeIcon
