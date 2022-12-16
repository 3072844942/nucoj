import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const TitleSvg = () => (
    <svg d="1665802310510" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="5427" width="32" height="32">
        <path
            d="M79.084173 949.307857l362.873588 0L441.957761 586.442456 79.084173 586.442456 79.084173 949.307857zM79.084173 465.487663l362.873588 0L441.957761 102.622262 79.084173 102.622262 79.084173 465.487663zM562.904367 949.307857l362.873588 0L925.777955 586.442456 562.904367 586.442456 562.904367 949.307857zM1000.923422 284.054451 744.344742 27.468608 487.758899 284.054451l256.585843 256.585843L1000.923422 284.054451z"
            p-id="5428" fill="#d81e06"></path>
    </svg>
);

/**
 * 时间图标
 * @param props
 * @constructor
 */
const TitleIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={TitleSvg} {...props} />
);

export default TitleIcon
