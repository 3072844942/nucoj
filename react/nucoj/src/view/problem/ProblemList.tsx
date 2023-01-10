import React, {useEffect, useState} from 'react';
import Search from "../../component/model/Search";
import ProblemList from "../../component/list/ProblemList";
import Classification from "../../component/model/Classification";
import {connect} from "react-redux";

/**
 * 题库页面
 * @constructor
 */
function Problem(props: { userId: string }) {
    const [value, setValue] = useState<string>("") // 输入框搜索值
    const [tags, setTags] = useState<string[]>([]) // 难度等级/题目标签等

    useEffect(() => {
        document.title = '题库'
    }, [])

    const click = (value: string) => {
        let data = [...tags]
        let t = -1
        tags.forEach((item: string, index: number) => {
            if (item === value)
                t = index
        })
        if (t !== -1) data.splice(t, 1)
        else data.push(value)
        setTags(data)
    }

    return (
        <div style={{margin: '3vh 0 3vh 3vw'}}>
            <div style={{
                width: '70vw'
            }}>
                <Search tags={tags} value={value} setValue={setValue} setType={click}></Search>
            </div>
            <div style={{
                display: 'flex'
            }}>
                <div style={{
                    width: '75%',
                    marginRight: '2vw',
                    height: '100px'
                }}>
                    <ProblemList userId={props.userId} value={value} tags={tags}></ProblemList>
                </div>
                <div style={{
                    width: '25%',
                    marginRight: '2vw'
                }}>
                    <Classification setType={click}></Classification>
                </div>
            </div>
        </div>
    );
}

const mapStateToProps = (state: any) => {
    return {
        userId: state.UserInfo.id
    }
}

export default connect(mapStateToProps)(Problem);