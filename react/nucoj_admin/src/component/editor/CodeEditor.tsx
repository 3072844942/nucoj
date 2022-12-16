import React, {useState} from 'react';

import AceEditor from "react-ace";
import 'ace-builds/src-noconflict/ext-language_tools'; // 代码联想
// 语言模块
import "ace-builds/src-noconflict/mode-java";
import "ace-builds/src-noconflict/mode-c_cpp";
import "ace-builds/src-noconflict/mode-python";
// 样式
import "ace-builds/src-noconflict/theme-textmate"; // 亮
import 'ace-builds/src-noconflict/theme-monokai';// 暗
import {DownOutlined, UpOutlined} from '@ant-design/icons';
import {Button, Dropdown, Menu, Space} from 'antd';
import axios from "axios";
import toast from "../toast";
import CorrectIcon from "../../assets/icon/CorrectIcon";
import {SUCCESS} from "../../constant/controller";
import ErrorIcon from "../../assets/icon/ErrorIcon";

/**
 * 代码编辑器
 * @param props
 * @constructor
 */
function CodeEditor(props: {
    code: string | undefined,
    setCode: Function,
    language: string,
    setLanguage: Function,
    isCompile: boolean,
    setIsCompile: Function
}) {
    const [language, setLanguage] = useState({
        key: "C_PLUS_PLUS",
        label: "C++",
        model: "c_cpp"
    });

    const languages = (
        <Menu
            selectable
            defaultSelectedKeys={[props.language]}
            items={[
                {
                    key: 'C_PLUS_PLUS',
                    label: 'C++',
                    onClick: () => {
                        setLanguage({
                            key: "C_PLUS_PLUS",
                            label: "C++",
                            model: "c_cpp"
                        })
                        props.setLanguage("C_PLUS_PLUS")
                    }
                },
                {
                    key: 'JAVA',
                    label: 'Java',
                    onClick: () => {
                        setLanguage({
                            key: "JAVA",
                            label: "Java",
                            model: "java"
                        })
                        props.setLanguage("JAVA")
                    }
                },
                {
                    key: 'PYTHON',
                    label: 'Python',
                    onClick: () => {
                        setLanguage({
                            key: "PYTHON",
                            label: "Python",
                            model: "python"
                        })
                        props.setLanguage("PYTHON")
                    }
                },
            ]}
        />
    );

    return (
        <div style={{overflowX: 'hidden'}}>
            <div style={{
                width: '100%',
                height: '8vh',
                backgroundColor: '#F6F7F9',
                display: 'inline-block'
            }}>
                <div style={{position: 'relative', top: '9px', left: '20px'}}>
                    <Dropdown overlay={languages}>
                        <Button style={{width: '150px'}}>
                            <Space>
                                {language.label}
                                <DownOutlined/>
                            </Space>
                        </Button>
                    </Dropdown>
                </div>
                <div style={{float: 'right', position: 'relative', top: '-20px', right: '20px'}}>
                    <Button type={'default'} onClick={() => {
                        axios({
                            url: '/api/problem/debug',
                            method: 'post',
                            data: {
                                language: language.key,
                                code: props.code,
                                test: "123"
                            }
                        }).then(res => {
                            if (res.data.code !== SUCCESS) toast('fail', '编辑错误', <ErrorIcon/>)
                            else  {
                                toast('success', '测试成功', <CorrectIcon/>)
                                props.setIsCompile(true)
                            }
                        })
                    }}>编辑运行</Button>
                </div>
            </div>
            <AceEditor
                mode={language.model}
                width={'100%'}
                height={'85vh'}
                theme="textmate"
                name="UNIQUE_ID_OF_DIV"
                value={props.code}
                onChange={(code) => {
                    props.setCode(code)
                }}
                editorProps={{$blockScrolling: true}}
                setOptions={{
                    enableBasicAutocompletion: true,
                    enableLiveAutocompletion: true,
                    enableSnippets: true
                }}
            />
        </div>

    );
}
export default CodeEditor;