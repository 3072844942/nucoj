import React, {useEffect, useState} from 'react';
import Search from "../../component/model/Search";
import SolutionList from "../../component/list/SolutionList";

/**
 * 题解页面
 * @constructor
 */
function Solution() {
    const [value, setValue] = useState("")

    useEffect(() => {
        document.title = '题解'
    }, [])

    return (
            <div style={{margin: '3vh 4vw 3vh 3vw'}}>
                <div style={{
                    width: '83vw'
                }}>
                    <Search value={value}  setValue={setValue}/>
                </div>
                <div>
                    <SolutionList value={value}/>
                </div>
            </div>
    );
}

export default Solution;