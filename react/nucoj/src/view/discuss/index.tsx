import React, {useEffect, useState} from 'react';
import Search from "../../component/model/Search";
import DiscussList from "../../component/list/DiscussList";

/**
 * 讨论页面
 * @constructor
 */
function Index() {
    const [value, setValue] = useState("")

    useEffect(() => {
        document.title = '讨论！'
    }, [])

    return (
        <div style={{
            margin: '3vh 4vw 3vh 6vw'
        }}>
            <div>
                <Search value={value} setValue={setValue}/>
            </div>
            <div>
                <DiscussList value={value}/>
            </div>
        </div>
    );
}

export default Index;