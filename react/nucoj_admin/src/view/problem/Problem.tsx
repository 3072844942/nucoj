import React, {useEffect, useState} from 'react';
import Container from "../../component/container";
import {useNavigate, useParams} from "react-router-dom";
import {ProblemInfo} from "../../interface/problem";
import axios from "axios";
import {getDate} from "../../util";
import {Button, Input, InputNumber, Popconfirm, Select, Switch} from "antd";
import Editor from "../../component/editor/Editor";
import Title from "../../component/title/Title";
import {Upload} from 'antd';
import {InboxOutlined} from "@ant-design/icons";
import toast from "../../component/toast";
import CorrectIcon from "../../assets/icon/CorrectIcon";
import ErrorIcon from "../../assets/icon/ErrorIcon";
import CodeEditor from "../../component/editor/CodeEditor";
import {SUCCESS} from "../../constant/controller";

const {Dragger} = Upload;
const {TextArea} = Input;

/**
 * 修改/创建 题目页面
 * @constructor
 */
function Problem() {
    const history = useNavigate()
    const params = useParams();
    const [data, setData] = useState<ProblemInfo>({
        id: '',
        title: '',
        time: 0,
        grade: '1级',
        tags: [],
        context: '',
        inputContext: '',
        outputContext: '',
        timeRequire: 1000,
        memoryRequire: 256 * 1024,
        examples: [{test: '', answer: ''}],
        recordId: '',
        isSpecial: false,
        specialJudge: '',
        specialLanguage: 'C_PLUS_PLUS'
    });
    const [tags, setTags] = useState([])
    const [isCompile, setIsCompile] = useState(false)
    // 谜之操作， 错误请查看Editor.tsx
    const [context1, setContext1] = useState('')
    const [context2, setContext2] = useState('')
    const [context3, setContext3] = useState('')

    // 希望是13个等级， 对应比赛中的13道题目
    const grades = () => {
        let values = []
        for (let i = 1; i <= 13; i++) {
            values.push({
                value: i + '级',
                label: i + '级'
            })
        }
        return values
    }

    useEffect(() => {
        axios({
            url: '/api/problem/tags',
            method: 'get',
            timeout: 2000
        }).then(res => {
            setTags(res.data.data)
        })

        if (params.problemId !== undefined) {
            axios({
                url: params.contestId ? '/api/admin/contest/problem' : '/api/admin/problem',
                method: 'post',
                data: {
                    id: params.problemId,
                    contestId: params.contestId
                }
            }).then(res => {
                setData(res.data.data)
                setContext1(res.data.data.context)
                setContext2(res.data.data.inputContext)
                setContext3(res.data.data.outputContext)
            })
        }
    }, [])

    useEffect(() => {
        setData({
            ...data,
            context: context1,
            inputContext: context2,
            outputContext: context3
        })
    }, [context1, context2, context3])

    return (
        <Container style={{margin: '10px 0 0 20px'}} title={'添加题目'} width={'96%'}>
            <Title>标题</Title>
            <div style={{display: 'flex'}}>
                <Input
                    value={data.title}
                    onChange={(e) => {
                        setData({
                            ...data,
                            title: e.target.value
                        })
                    }}
                    status={data.title.length ? undefined : 'error'}
                    placeholder={getDate(new Date().getTime())}
                    allowClear
                />
                <Select
                    defaultValue={data.grade}
                    style={{width: '100px', marginLeft: '20px'}}
                    onChange={(e) => setData({...data, grade: e})}
                    options={grades()}
                />
                <InputNumber style={{width: '200px', marginLeft: '20px'}} addonAfter={'ms'} value={data.timeRequire}
                             onChange={(e) => setData({...data, timeRequire: e})}></InputNumber>
                <InputNumber style={{width: '200px', marginLeft: '20px'}} addonAfter={'kb'} value={data.memoryRequire}
                             onChange={(e) => setData({...data, memoryRequire: e})}></InputNumber>
            </div>
            <Title>标签</Title>
            <Select
                mode="tags"
                style={{width: '100%'}}
                value={data.tags}
                onChange={(value) => {
                    setData({
                        ...data,
                        tags: value
                    })
                }}
                tokenSeparators={[',', ' ']}
                options={tags.map((item) => {
                    return {
                        value: item,
                        label: item
                    }
                })}
            />
            <Title>题目描述</Title>
            <Editor editorId={'context'} text={context1} setText={setContext1}/>
            <Title>输入描述</Title>
            <Editor editorId={'input'}  text={context2} setText={setContext2}/>
            <Title>输出描述</Title>
            <Editor editorId={'output'}  text={context3} setText={setContext3}/>
            <Title>样例</Title>
            {
                data.examples.map((item, index) => <div key={index} style={{
                    display: 'flex',
                    width: '100%',
                    height: '26vh',
                    justifyContent: 'space-between'
                }}>
                    <div style={{width: '50%'}}>
                        <Title>输入样例#{index + 1}</Title>
                        <TextArea rows={5} placeholder="test" value={item.test}
                                  style={{width: '80%'}}
                                  onChange={(e) => {
                                      let newData = {...data}
                                      newData.examples[index].test = e.target.value
                                      setData(newData)
                                  }}/>
                    </div>
                    <div style={{width: '50%'}}>
                        <Title>输出样例#{index + 1}</Title>
                        <TextArea rows={5} placeholder="answer" value={item.answer}
                                  style={{width: '80%'}}
                                  onChange={(e) => {
                                      let newData = {...data}
                                      newData.examples[index].answer = e.target.value
                                      setData(newData)
                                  }}/>
                    </div>
                    <Popconfirm title={'确定删除？'} onConfirm={() => {
                        let newData = {...data}
                        newData.examples.splice(index, 1)
                        setData(newData)
                    }}>
                        <Button danger>删除</Button>
                    </Popconfirm>
                </div>)
            }
            <div
                style={{display: 'flex', alignItems: 'center', justifyContent: 'center', width: '100%', height: '7vh'}}>
                <Button type="dashed" style={{width: '20%', height: '100%'}} onClick={() => {
                    let newData = {...data}
                    newData.examples.push({test: '', answer: ''})
                    setData(newData)
                }}>+ Add</Button>
            </div>
            <Title>测试数据</Title>
            <Dragger
                name={'file'}
                multiple={true}
                action={'/api/record/upload'}
                beforeUpload={(file) => {
                    // zip && rar
                    const isPNG = (file.type === 'application/x-zip-compressed' || file.type === 'application/octet-stream');
                    if (!isPNG) {
                        toast('fail', '请上传压缩包', <ErrorIcon/>)
                    }
                    return isPNG || Upload.LIST_IGNORE;
                }}
                onChange={(info) => {
                    // 重复上传会发多个请求， 所以只能上传压缩包， 且以最后一次的上传为准
                    const {status} = info.file;
                    if (status === 'done') { // 上传成功
                        toast('success', '文件上传成功', <CorrectIcon/>)
                        setData({
                            ...data,
                            recordId: info.file.response.data
                        })
                    } else if (status === 'error') {
                        toast('fail', info.file.response.message, <ErrorIcon/>)
                    }
                }
                }
                onDrop={(e) => { // 删除上传内容， 没有变化
                    console.log('Dropped files', e.dataTransfer.files);
                }}
            >
                <p className="ant-upload-drag-icon">
                    <InboxOutlined/>
                </p>
                <p className="ant-upload-text">点击或拖拽文件夹上传</p>
            </Dragger>
            <Title>
                <Switch checked={data.isSpecial} onChange={() => {
                    setData({
                        ...data,
                        isSpecial: !data.isSpecial,
                        specialLanguage: 'C_PLUS_PLUS',
                        specialJudge: '#include <stdio.h>\n' +
                            '\n' +
                            '#define AC 0\n' +
                            '#define WA 1\n' +
                            '#define ERROR -1\n' +
                            '\n' +
                            'int spj(FILE *input, FILE *user_output);\n' +
                            '\n' +
                            'void close_file(FILE *f){\n' +
                            '    if(f != NULL){\n' +
                            '        fclose(f);\n' +
                            '    }\n' +
                            '}\n' +
                            '\n' +
                            'int main(int argc, char *args[]){\n' +
                            '    FILE *input = NULL, *user_output = NULL;\n' +
                            '    int result;\n' +
                            '    if(argc != 3){\n' +
                            '        printf("Usage: spj x.in x.out\\n");\n' +
                            '        return ERROR;\n' +
                            '    }\n' +
                            '    input = fopen(args[1], "r");\n' +
                            '    user_output = fopen(args[2], "r");\n' +
                            '    if(input == NULL || user_output == NULL){\n' +
                            '        printf("Failed to open output file\\n");\n' +
                            '        close_file(input);\n' +
                            '        close_file(user_output);\n' +
                            '        return ERROR;\n' +
                            '    }\n' +
                            '\n' +
                            '    result = spj(input, user_output);\n' +
                            '    printf("result: %d\\n", result);\n' +
                            '\n' +
                            '    close_file(input);\n' +
                            '    close_file(user_output);\n' +
                            '    return result;\n' +
                            '}\n' +
                            '\n' +
                            'int spj(FILE *input, FILE *user_output){\n' +
                            '    int a, b, c;\n' +
                            '    fscanf(input, "%d%d", &a, &b);\n' +
                            '    fscanf(user_output, "%d", &c);\n' +
                            '    if (a + b != c) {\n' +
                            '        return WA;\n' +
                            '    }\n' +
                            '    return AC;\n' +
                            '}\n'
                    })
                }}/>
                启用特别判断
            </Title>
            {
                data.isSpecial ? <CodeEditor
                    code={data.specialJudge}
                    setCode={(value: string) => {
                        setData({...data, specialJudge: value})
                    }}
                    language={data.specialLanguage}
                    setLanguage={(value: string) => {
                        setData({...data, specialLanguage: value})
                    }}
                    isCompile={isCompile}
                    setIsCompile={setIsCompile}
                /> : null
            }
            <Button
                style={{
                    margin: '2vh 0'
                }}
                type={'primary'}
                onClick={() => {
                    if (data.title === '') toast('fail', '题目标题未输入', <ErrorIcon/>)
                    else if (data.tags.length === 0) toast('fail', '题目标签未输入', <ErrorIcon/>)
                    else if (data.grade === '') toast('fail', '题目等级未选择', <ErrorIcon/>)
                    else if (data.context === '') toast('fail', '题目描述未输入', <ErrorIcon/>)
                    else if (data.inputContext === '') toast('fail', '输入描述未输入', <ErrorIcon/>)
                    else if (data.outputContext === '') toast('fail', '输出描述未输入', <ErrorIcon/>)
                    else if (data.outputContext === '') toast('fail', '输出描述未输入', <ErrorIcon/>)
                    else if (data.examples.length === 0
                        || data.examples[data.examples.length - 1].test === ''
                        || data.examples[data.examples.length - 1].answer === '')
                        toast('fail', '题目样例未输入', <ErrorIcon/>)
                    else if (data.recordId === '') toast('fail', '题目数据为上传', <ErrorIcon/>)
                    // 特别判断的代码必须通过测试， 但是是否能满足自己的题目数据， 需要自行判断
                    else if (data.isSpecial && !isCompile) toast('fail', '特别判断未测试', <ErrorIcon/>)
                    else {
                        axios({
                            url: params.contestId ? '/api/contest/problem/update' : '/api/problem/update',
                            method: 'post',
                            data: {
                                ...data,
                                contestId: params.contestId
                            }
                        }).then(res => {
                            if (res.data.code === SUCCESS) {
                                toast('success', '创建成功', <CorrectIcon/>)
                                if (params.contestId) history('/contest/' + params.contestId)
                            }
                            else toast('fail', res.data.message, <CorrectIcon/>)
                        })
                    }
                }}
            >创建完成</Button>
        </Container>
    );
}

export default Problem;