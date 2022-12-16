
import React, {useEffect, useState} from 'react';
import {ProblemInfo} from "../../interface/problem";
import ProblemContainer from "../container/ProblemContainer";
import ScrollList from "./ScrollList";
import axios from "axios";

function ProblemList(props: { userId:string, value: string, tags: string[] }) {
    const [data, setDate] = useState<ProblemInfo[]>([])
    const [acceptProblem, setAcceptProblem] = useState<string[]>([]);
    const [tryProblem, setTryProblem] = useState<string[]>([])

    useEffect(() => {
        if (props.userId !== '') {
            axios({
                url: '/api/user/acceptProblem/' + props.userId,
                method: 'get'
            }).then(res => {
                setAcceptProblem(res.data.data)
            })
            axios({
                url: '/api/user/tryProblem/' + props.userId,
                method: 'get'
            }).then(res => {
                setTryProblem(res.data.data)
            })
        }
    }, [props.userId])

    return (
        <ScrollList url={'problem'} value={props.value} tags={props.tags} setData={(item:any) => {
            setDate(item)
        }} data={data}>
            <div>
                {
                    data.map(item =>
                        <ProblemContainer
                            problemInfo={item}
                            status={acceptProblem?.includes(item.id) ? 2 : (tryProblem?.includes(item.id) ? 1 : 0)}
                        />
                    )
                }
            </div>
        </ScrollList>
    );
}

export default ProblemList;