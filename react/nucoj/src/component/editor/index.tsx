import React, {useEffect, useState} from 'react';

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
import { Input } from 'antd';
import {ProblemInfo} from "../../interface/problem";
import {debug, judge} from "../../action/problem";
const { TextArea } = Input;

function Index(props: {
    userId: string,
    problemId: string | undefined,
    problem: ProblemInfo,
    contestId: string | undefined
}) {
    const [show, setShow] = useState('')
    const [test, setTest] = useState('') // 自测输入
    const [answer, setAnswer] = useState('') // 测试结果
    const [language, setLanguage] = useState({
        key: "C_PLUS_PLUS",
        label: "C++",
        model: "c_cpp"
    });
    const [code, setCode] = useState("");

    const languages = (
        <Menu
            selectable
            defaultSelectedKeys={['c++']}
            items={[
                {
                    key: 'C_PLUS_PLUS',
                    label: 'C++',
                    onClick: () => setLanguage({
                        key: "C_PLUS_PLUS",
                        label: "C++",
                        model: "c_cpp"
                    })
                },
                {
                    key: 'JAVA',
                    label: 'Java',
                    onClick: () => setLanguage({
                        key: "JAVA",
                        label: "Java",
                        model: "java"
                    })
                },
                {
                    key: 'PYTHON',
                    label: 'Python',
                    onClick: () => setLanguage({
                        key: "PYTHON",
                        label: "Python",
                        model: "python"
                    })
                },
            ]}
        />
    );

    useEffect(() => {
        if (props.problem.examples.length > 0) {
            setTest(props.problem.examples[0].test)
        }
    }, [props.problem])

    return (
        <div style={{overflowX: 'hidden'}}>
            <div style={{
                width: '100%',
                height: '8vh',
                backgroundColor: '#F6F7F9'
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
            </div>
            <AceEditor
                mode={language.model}
                width={'100%'}
                height={show == '' ? '85vh' : '60vh'}
                theme="textmate"
                name="UNIQUE_ID_OF_DIV"
                value={code}
                onChange={(code) => {
                    setCode(code)
                }}
                editorProps={{$blockScrolling: true}}
                setOptions={{
                    enableBasicAutocompletion: true,
                    enableLiveAutocompletion: true,
                    enableSnippets: true
                }}
            />
            <div style={{position: 'relative'}}>
                <div style={{
                    width: 30,
                    height: 30,
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    position: 'absolute',
                    top: -10,
                    left: '50%',
                    borderRadius: '100%',
                    backgroundColor: '#E3E3E3',
                    userSelect: "none",
                    cursor: 'pointer'
                }} onClick={() => {
                    if (show == '') setShow('test')
                    else setShow('')
                }}>
                    {show == '' ? <UpOutlined /> : <DownOutlined />}
                </div>
                <div style={{
                    width: '100%',
                    height: '6vh',
                    display: 'inline-block',
                    padding: '1vh 1vw',
                    backgroundColor: '#F6F7F9'
                }}>
                    <div style={{float: 'left', display: 'flex'}}>
                        <Button type={show == 'test' ? 'primary' : 'text'} onClick={() => setShow('test')}>自测输入</Button>
                        <Button type={show == 'answer' ? 'primary' : 'text'} onClick={() => setShow('answer')} style={{marginLeft: '2vw'}}>运行结果</Button>
                        <Button
                            style={{marginLeft: '2vw'}}
                            loading={answer == 'waiting'}
                            onClick={() => {
                                setShow('answer')
                                setAnswer('waiting')
                                debug(props.problem?.timeRequire, language.key, code, test, setAnswer)
                            }}
                        >自测运行</Button>
                    </div>
                    <div style={{float: 'right'}}>
                        <Button shape="round" type="primary"  loading={answer === 'judging'} onClick={() => {
                            setShow('')
                            judge(code, language.key, props.userId, props.problemId ? props.problemId : '', props.contestId, setAnswer, setShow);
                        }}>提交答案</Button>
                    </div>
                </div>
                <div style={{
                    padding: '1vh 2vw'
                }}>
                    <TextArea rows={6} value={show == 'test' ? test : answer} onChange={(e) => {
                        if (show == 'test') setTest(e.target.value)
                        else setAnswer(e.target.value)
                    }}/>
                </div>
            </div>
        </div>

    );
}
export default Index;