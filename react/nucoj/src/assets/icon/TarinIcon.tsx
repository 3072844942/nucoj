import Icon from '@ant-design/icons';
import type { CustomIconComponentProps } from '@ant-design/icons/lib/components/Icon';
import React from 'react';

const TrainSvg = () => (
    <svg d="1664536920306" className="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
         p-id="15251" width="32" height="32">
        <path
            d="M752.197531 807.822222H189.62963a25.283951 25.283951 0 0 1-25.283951-25.28395V147.026173a25.283951 25.283951 0 0 1 25.283951-25.283951h562.567901a25.283951 25.283951 0 0 1 25.28395 25.283951V782.538272a25.283951 25.283951 0 0 1-25.28395 25.28395z m-537.283951-50.567901h512V172.310123H214.91358z"
            p-id="15252"></path>
        <path
            d="M849.92 905.544691H287.731358a25.283951 25.283951 0 0 1 0-50.567901h536.904691V244.748642a25.283951 25.283951 0 0 1 50.567902 0v635.512099a25.283951 25.283951 0 0 1-25.283951 25.28395zM629.94963 318.957037H312.25679a25.283951 25.283951 0 0 1 0-50.567901h317.69284a25.283951 25.283951 0 0 1 0 50.567901z"
            p-id="15253"></path>
        <path
            d="M629.94963 490.002963H312.25679a25.283951 25.283951 0 1 1 0-50.567901h317.69284a25.283951 25.283951 0 0 1 0 50.567901zM458.903704 661.175309H312.25679a25.283951 25.283951 0 0 1 0-50.567902h146.646914a25.283951 25.283951 0 0 1 0 50.567902z"
            p-id="15254"></path>
    </svg>
);

/**
 * 题单图标
 * @param props
 * @constructor
 */
const TrainIcon = (props: Partial<CustomIconComponentProps>) => (
    <Icon component={TrainSvg} {...props} />
);

export default TrainIcon
