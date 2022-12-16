import React, {useState} from 'react';
import {ProblemInfo} from "../../interface/problem";
import ErrorIcon from "../../assets/icon/ErrorIcon";
import CorrectIcon from "../../assets/icon/CorrectIcon";

import Accordion from '@mui/material/Accordion';
import AccordionDetails from '@mui/material/AccordionDetails';
import AccordionSummary from '@mui/material/AccordionSummary';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Button from "../button/Button";
import {NavLink} from "react-router-dom";

/**
 * 题目信息容器
 * 一个可展开的信息区块
 * @param props
 * @constructor
 */
function ProblemContainer(props: { problemInfo: ProblemInfo, status?: number }) {
    const [enter, setEnter] = useState(false)

    const getIcon = (status: number | undefined) => {
        if (status === 1) return <ErrorIcon/>
        else if (status === 2) return <CorrectIcon/>
        else return <ExpandMoreIcon/>
    }

    return (
        <div
            style={{
                position: 'relative'
            }}
            onMouseEnter={() => setEnter(true)}
            onMouseLeave={() => setEnter(false)}
        >
            <Accordion expanded={enter}>
                {/*标题*/}
                <AccordionSummary
                    expandIcon={getIcon(props.status)}
                >
                    <Typography sx={{width: '33%', flexShrink: 0}}>
                        <NavLink to={'/problem/' + props.problemInfo.id}>
                            {props.problemInfo.title}
                        </NavLink>
                    </Typography>
                    <Typography sx={{color: 'text.secondary'}}>
                        <div style={{display: 'flex'}}>
                            <Button context={props.problemInfo.grade} color={'red'} fontColor={'white'} size={3}
                                    enter={false}/>
                            {
                                props.problemInfo.tags.map(item => <Button
                                    context={item}
                                    color={'green'}
                                    fontColor={'white'}
                                    size={3}
                                    enter={false}
                                    style={{
                                        marginLeft: '10px'
                                    }}
                                />)
                            }
                        </div>
                    </Typography>
                </AccordionSummary>
                {/*展开后的内容*/}
                <AccordionDetails>
                    <Typography>
                        {props.problemInfo.context}
                    </Typography>
                </AccordionDetails>
            </Accordion>
        </div>
    );
}

export default ProblemContainer;