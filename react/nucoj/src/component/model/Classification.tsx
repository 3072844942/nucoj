import React, {useEffect, useState} from 'react';
import axios from "axios";
import RightArrowIcon from "../../assets/icon/RightArrowIcon";

/**
 * 题目分类
 * @param props
 * @constructor
 */
function Classification(props: { setType: Function }) {
    const [active, setActive] = useState<string>('grade')
    const [data, setData] = useState([])
    const [current, setCurrent] = useState(-1)

    useEffect(() => {
        document.title = "题库 | Online Judge"
    }, [])

    useEffect(() => {
        axios({
            url: active === 'grade' ? '/api/problem/grades' : '/api/problem/tags',
            method: 'get',
            timeout: 2000
        }).then(res => {
            setData(res.data.data)
        })
    }, [active])
    return (
        <div style={{
            boxShadow: '0 1px 1px 1px rgb(33, 33, 33, .1)',
            borderRadius: '20px',
            paddingTop: '10px',
            position: 'sticky'
        }}>
            <div style={{
                display: 'flex',
                justifyContent: 'space-around',
                borderBottom: '1px dashed'
            }}>
                <p
                    style={{
                        color: active === 'grade' ? 'black' : '#E3E3E3',
                        cursor: 'pointer'
                    }}
                    onClick={() => setActive('grade')}
                >
                    难度分类
                </p>
                /
                <p
                    style={{
                        color: active === 'tag' ? 'black' : '#E3E3E3',
                        cursor: 'pointer'
                    }}
                    onClick={() => setActive('tag')}
                >
                    题目分类
                </p>

            </div>
            <div>
                {
                    data.map((item: string, index) => <div
                        key={item}
                        onClick={() => props.setType(item)}
                        style={{
                            display: 'flex',
                            padding: '20px 24px',
                            cursor: 'pointer',
                            alignItems: 'center',
                            backgroundColor: index === current ? '#e3e3e3' : 'white'
                        }}
                        onMouseEnter={() => setCurrent(index)}
                        onMouseLeave={() => setCurrent(-1)}
                    >
                        <RightArrowIcon/>
                        <div style={{
                            height: '100%',
                            textAlign: 'center',
                            marginLeft: '10%',
                            fontSize: '18px'
                        }}>{item}</div>
                    </div>)
                }
            </div>
        </div>
    );
}

export default Classification;