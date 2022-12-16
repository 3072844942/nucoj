import React, {useEffect, useState} from 'react';
import Search from "../../component/model/Search";
import TrainList from "../../component/list/TrainList";

/**
 * 题单页面
 * @constructor
 */
function Index() {
    const [value, setValue] = useState("")

    useEffect(() => {
        document.title = '题单'
    }, [])

    return (
        <div style={{margin: '3vh 4vw 3vh 6vw'}}>
            <div style={{
                width: '83vw'
            }}>
                <Search value={value} setValue={setValue}/>
            </div>
            <div>
                <TrainList value={value}/>
            </div>
        </div>
    );
}

export default Index;