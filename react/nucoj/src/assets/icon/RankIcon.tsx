import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const RankSvg = () => (
    <svg d="1664934743939" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="2889" width="30" height="30">
        <path
            d="M272 400v160c0 35.3-28.7 64-64 64H56c-4.4 0-8-3.6-8-8v-48c0-4.4 3.6-8 8-8h136c8.8 0 16-7.2 16-16v-16c0-8.8-7.2-16-16-16H56c-4.4 0-8-3.6-8-8v-48c0-4.4 3.6-8 8-8h136c8.8 0 16-7.2 16-16v-16c0-8.8-7.2-16-16-16H56c-4.4 0-8-3.6-8-8v-48c0-4.4 3.6-8 8-8h152c35.3 0 64 28.7 64 64zM544 64v216c0 4.4-3.6 8-8 8h-48c-4.4 0-8-3.6-8-8V80c0-8.8-7.2-16-16-16h-40c-4.4 0-8-3.6-8-8V8c0-4.4 3.6-8 8-8h56c35.3 0 64 28.7 64 64zM816 368v16c0 8.8 7.2 16 16 16h136c4.4 0 8 3.6 8 8v48c0 4.4-3.6 8-8 8H816c-35.3 0-64-28.7-64-64v-48c0-35.3 28.7-64 64-64h80c8.8 0 16-7.2 16-16v-16c0-8.8-7.2-16-16-16H760c-4.4 0-8-3.6-8-8v-48c0-4.4 3.6-8 8-8h152c35.3 0 64 28.7 64 64v48c0 35.3-28.7 64-64 64h-80c-8.8 0-16 7.2-16 16z"
            p-id="2890"></path>
        <path
            d="M640 400v112c0 35.3 28.7 64 64 64h240c8.8 0 16 7.2 16 16v360c0 4.4-3.6 8-8 8H72c-4.4 0-8-3.6-8-8V752c0-8.8 7.2-16 16-16h240c35.3 0 64-28.7 64-64V416c0-8.8 7.2-16 16-16h240m-256-64c-35.3 0-64 28.7-64 64v256c0 8.8-7.2 16-16 16H64c-35.3 0-64 28.7-64 64v256c0 17.7 14.3 32 32 32h960c17.7 0 32-14.3 32-32V576c0-35.3-28.7-64-64-64H720c-8.8 0-16-7.2-16-16v-96c0-35.3-28.7-64-64-64H384z"
            p-id="2891"></path>
    </svg>
);

/**
 * 排名图标
 * @param props
 * @constructor
 */
const RankIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={RankSvg} {...props} />
);

export default RankIcon
