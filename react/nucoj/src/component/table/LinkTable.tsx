import React from 'react';
import {List} from "antd";
import {LinkInfo} from "../../interface/link";

/**
 * 主页链接
 * @constructor
 */
function LinkTable(props:{data:LinkInfo[]}) {

    return (
        <List
            bordered={false}
            dataSource={props.data}
            renderItem={(item:LinkInfo) => (
                <List.Item>
                    <div style={{width:'100%', display:'inline-block', textAlign: 'center'}}>
                        <a href={item.link} target={'_blank'}>{item.title}</a>
                    </div>
                </List.Item>
            )}
        />
    );
}

export default LinkTable;