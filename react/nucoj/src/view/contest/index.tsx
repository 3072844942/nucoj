import React, {useEffect, useState} from 'react';
import Search from "../../component/model/Search";
import ContestList from "../../component/list/ContestList";

/**
 * 比赛页面
 * @constructor
 */
function Index() {
    const [value, setValue] = useState("")

    useEffect(() => {
        document.title = '比赛'
    }, [])

    return (
        <div style={{
            margin: '3vh 4vw 3vh 6vw'
        }}>
            <div>
                <Search value={value} setValue={setValue}/>
            </div>
            <div>
                <ContestList value={value}/>
            </div>
        </div>
    );
}

export default Index;