import React, {useState} from 'react';
import {LinkInfo} from "../../interface/link";
import {Form, Popconfirm, Table, Typography} from "antd";
import axios from "axios";
import EditableCell from "./tableCell/EditableCell";
import {SUCCESS} from "../../constant/controller";
import toast from "../toast";

/**
 * 友链表格
 * 这里希望是可编辑的表格， 就不需要再写页面
 * @param props
 * @constructor
 */
function LinkTable(props: { data: LinkInfo[], setData: Function }) {
    const [editingKey, setEditingKey] = useState('');
    const [form] = Form.useForm();

    const isEditing = (record: LinkInfo) => record.link === editingKey;

    const edit = (record: Partial<LinkInfo>) => {
        form.setFieldsValue({title: '', link: '', ...record});
        setEditingKey(record.link ? record.link : '');
    };

    const save = async (key: React.Key) => {
        try {
            const row = (await form.validateFields()) as LinkInfo;

            const newData = [...props.data];
            const index = newData.findIndex(item => key === item.link);
            if (index > -1) {
                const item = newData[index];
                newData.splice(index, 1, {
                    ...item,
                    ...row,
                });
                props.setData(newData);
                setEditingKey('');
            } else {
                newData.push(row);
                props.setData(newData);
                setEditingKey('');
            }
        } catch (errInfo) {
            console.log('Validate Failed:', errInfo);
        }
    };

    const del = async (recode: LinkInfo) => {
        axios({
            url: '/api/link/delete',
            method: 'post',
            data: {
                id: recode.link
            }
        }).then(res => {
            if (res.data.code === SUCCESS) toast('success', '删除成功')
        })
    }

    const columns = [
        {
            title: '标题',
            dataIndex: 'title',
            editable: true,
            align: 'center'
        },
        {
            title: '地址',
            dataIndex: 'link',
            editable: true,
            align: 'center',
        },
        {
            title: 'operation',
            dataIndex: 'operation',
            width: '20%',
            align: 'center',
            render: (_: any, record: LinkInfo) => {
                const editable = isEditing(record);
                return editable ? (
                    <span>
                    <Typography.Link onClick={() => save(record.link)} style={{marginRight: 12}}>
                      保存
                    </Typography.Link>
                    <Typography.Link style={{marginRight: 12}}>
                        <Popconfirm title="取消编辑?" onConfirm={cancel}>
                          取消
                        </Popconfirm>
                    </Typography.Link>
                  </span>
                ) : (
                    <span>
                        <Typography.Link disabled={editingKey !== ''} onClick={() => edit(record)}
                                         style={{marginRight: 12}}>
                            编辑
                        </Typography.Link>
                        <Typography.Link disabled={editingKey !== ''}>
                            <Popconfirm title="确定删除?" onConfirm={() => del(record)}>
                              删除
                            </Popconfirm>
                        </Typography.Link>
                    </span>

                );
            },
        },
    ];

    const mergedColumns = columns.map(col => {
        if (!col.editable) {
            return col;
        }
        return {
            ...col,
            key: col.title,
            onCell: (record: LinkInfo) => ({
                record,
                inputType: 'text',
                dataIndex: col.dataIndex,
                title: col.title,
                editing: isEditing(record),
            }),
        };
    });

    const cancel = () => {
        setEditingKey('');
    };


    return (
        <Form form={form} component={false}>
            <Table
                sticky={true}
                bordered={true}
                components={{
                    body: {
                        cell: EditableCell,
                    },
                }}
                dataSource={props.data}
                // 这里在加上align： center之后报错类型不匹配  但是好像可以用。。
                // @ts-ignore
                columns={mergedColumns}
                pagination={{
                    onChange: cancel,
                }}
            />
        </Form>
    );
}

export default LinkTable;