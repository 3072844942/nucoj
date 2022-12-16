import React, {ReactNode} from "react";

interface MenuInfo {
    label: string,
    key: string,
    icon?: string | ReactNode,
    children?: MenuInfo[],
    type?: 'group',
}

export type {MenuInfo}