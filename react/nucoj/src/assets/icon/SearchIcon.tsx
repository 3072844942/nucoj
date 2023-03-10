import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const SearchSvg = () => (
    <svg d="1665127724324" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="2551" width="40" height="40">
        <path
            d="M756.565333 697.258667c2.133333 1.493333 4.224 3.157333 6.101334 5.12l241.664 241.621333c16.256 16.256 16.512 43.52-0.128 60.16a42.453333 42.453333 0 0 1-60.202667 0.170667l-241.664-241.664a41.429333 41.429333 0 0 1-5.034667-6.101334A424.917333 424.917333 0 0 1 426.666667 853.333333C191.018667 853.333333 0 662.314667 0 426.666667S191.018667 0 426.666667 0s426.666667 191.018667 426.666666 426.666667c0 102.698667-36.266667 196.949333-96.768 270.592zM426.666667 768a341.333333 341.333333 0 1 0 0-682.666667 341.333333 341.333333 0 0 0 0 682.666667z"
            fill="#1296db" p-id="2552"></path>
    </svg>
);

/**
 * 搜索图标
 * @param props
 * @constructor
 */
const SearchIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={SearchSvg} {...props} />
);

export default SearchIcon
