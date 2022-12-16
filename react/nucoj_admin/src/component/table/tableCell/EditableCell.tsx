import {Form, Input, InputNumber} from 'antd';
import {LinkInfo} from "../../../interface/link";
import React from "react";

interface EditableCellProps extends React.HTMLAttributes<HTMLElement> {
    editing: boolean;
    dataIndex: string;
    title: any;
    inputType: 'number' | 'text';
    record: LinkInfo;
    index: number;
    children: React.ReactNode;
}

/**
 * 可编辑单元格
 * 写法与antd官网一致
 * @param editing
 * @param dataIndex
 * @param title
 * @param inputType
 * @param record
 * @param index
 * @param children
 * @param restProps
 * @constructor
 */
const EditableCell: React.FC<EditableCellProps> = ({
                                                       editing,
                                                       dataIndex,
                                                       title,
                                                       inputType,
                                                       record,
                                                       index,
                                                       children,
                                                       ...restProps
                                                   }) => {
    const inputNode = inputType === 'number' ? <InputNumber/> : <Input/>;

    return (
        <td {...restProps}>
            {editing ? (
                <Form.Item
                    name={dataIndex}
                    style={{margin: 0}}
                    rules={[
                        {
                            required: true,
                            message: `Please Input ${title}!`,
                        },
                    ]}
                >
                    {inputNode}
                </Form.Item>
            ) : (
                children
            )}
        </td>
    );
};

export default EditableCell;