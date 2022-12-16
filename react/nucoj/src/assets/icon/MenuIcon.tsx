import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const MenuSvg = () => (
    <svg d="1665545359210" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="2573" width="32" height="32">
        <path
            d="M170.666667 213.333333h682.666666v85.333334H170.666667V213.333333z m0 512h682.666666v85.333334H170.666667v-85.333334z m0-256h682.666666v85.333334H170.666667v-85.333334z"
            fill="#444444" p-id="2574"></path>
    </svg>
);

/**
 * 菜单图标
 * @param props
 * @constructor
 */
const MenuIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={MenuSvg} {...props} />
);

export default MenuIcon
