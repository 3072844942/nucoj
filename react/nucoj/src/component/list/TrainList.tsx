import React, {useState} from 'react';
import ScrollList from "./ScrollList";
import TrainTable from "../table/TrainTable";
import {TrainInfo} from "../../interface/train";

function TrainList(props:{value:string}) {
    const [data, setDate] = useState<TrainInfo[]>([])

    return (
        <ScrollList url={'train'} value={props.value} setData={(item:any) => {
            setDate(item)
        }} data={data}>
            <TrainTable data={data} />
        </ScrollList>
    );
}

export default TrainList;