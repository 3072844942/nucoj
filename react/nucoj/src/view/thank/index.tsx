import React from 'react';
import {markdownToHtml} from "../../util";

function Index() {

    return (
        <div style={{margin: '3vh 4vw 3vh 6vw'}}>
            <div
                className={"article-content markdown-body"}
                dangerouslySetInnerHTML={{__html: markdownToHtml('这个作者太懒了， 什么也没有留下。\n ' +
                        '### 参考目录\n ' +
                        '+ [antd](https://ant.design/components/overview-cn)\n ' +
                        '+ [antd设计思路](https://ant.design/docs/spec/introduce-cn)\n ' +
                        '+ [material-ui](https://mui.com/material-ui/react-autocomplete/)\n ' +
                        '+ [图形库](https://www.iconfont.cn/home/index)\n' +
                        '+ [echarts](https://echarts.apache.org/examples/zh/index.html#chart-type-line)\n' +
                        '+ [判题核心](https://github.com/yuzhanglong/YuJudge-Core)\n' +
                        '+ [判题后端参考](https://github.com/yuzhanglong/YuJudge-JudgeHost)\n' +
                        '2022-12-13\n'
                    )}}></div>
            {/*dangerouslySetInnerHTML={{__html: markdownToHtml("# 123")}}></div>*/}
        </div>
    );
}

export default Index