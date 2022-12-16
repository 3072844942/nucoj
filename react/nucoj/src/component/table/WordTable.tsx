import React, {useEffect, useState} from 'react';

function WordTable() {
    const [data, setData] = useState({
        commit_from: '',
        created_at: "",
        creator: "",
        creator_uid: 0,
        from: "",
        from_who: 0,
        hitokoto: "",
        id: 0,
        length: 0,
        reviewer: 0,
        type: "0",
        uuid: "0"
    })

    useEffect(() => {
        fetch("https://v1.hitokoto.cn/")
            .then(res => {
                return res.json();
            })
            .then((hitokoto: any) => {
                setData(hitokoto)
            });
    }, [])

    return (
        <div style={{
            width: '100%',
            minHeight: '60px',
            display: 'inline-block',
            padding: '10px'
        }}>
            <p style={{textAlign: 'center', margin:'auto', fontSize: '16px'}}>{data.hitokoto}</p>
            <div style={{float: 'right'}}>----{data.creator}</div>
        </div>
    );
}

export default WordTable;