import React, {useState} from 'react';
import ScrollList from "./ScrollList";
import {SolutionInfo} from "../../interface/solution";
import SolutionTable from "../table/SolutionTable";

function SolutionList(props:{value:string}) {
    const [data, setDate] = useState<SolutionInfo[]>([])

    return (
        <ScrollList url={'solution'} value={props.value} setData={(item:any) => {
            setDate(item)
        }} data={data}>
            <SolutionTable data={data}/>
        </ScrollList>
    );
}

export default SolutionList;