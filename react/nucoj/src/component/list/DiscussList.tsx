import React, {useState} from 'react';
import ScrollList from "./ScrollList";
import DiscussTable from "../table/DiscussTable";
import {DiscussInfo} from "../../interface/discuss";

function DiscussList(props:{value:string}) {
    const [data, setDate] = useState<DiscussInfo[]>([])

    return (
        <ScrollList url={'discuss'} value={props.value} setData={(item:any) => {
            setDate(item)
        }} data={data}>
            <DiscussTable data={data}/>
        </ScrollList>
    );
}

export default DiscussList;