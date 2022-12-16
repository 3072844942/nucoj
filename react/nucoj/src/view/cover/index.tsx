import * as React from 'react';

import Button from "../../component/button/Button";
import {useEffect, useState} from "react";
import Typewriter from "../../component/container/Typewriter";
import {getBlogInfo} from "../../action/controller";
import {connect} from "react-redux";

/**
 * 主图封面
 * @constructor
 */
function Index(props: {
    title: string,
    getInfo: Function
}) {
    useEffect(() => {
        document.title = "中北大学YYDS"
        props.getInfo()
    }, [])

    return (
        <div style={{
            background: "url(" + require('../../assets/img/coverBackground.JPG') + ") center center / cover no-repeat",
            width: "100vw",
            height: "100vh",
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            flexWrap: 'wrap',
            position: 'absolute',
            top: 0,
            left: 0,
            zIndex: '1002'
        }}>
            <Typewriter
                value={props.title}
                style={{
                    font: 'bold 100px arial',
                    color: 'white',
                    animation: 'shake 0.8s linear infinite'
                }}
                cursor={false}
            />
            <div style={{
                flexBasis: '100%',
                display: 'flex',
                justifyContent: 'space-around',
                alignItems: 'center'
            }}>
                <Button color={"#D6E9CA"} fontColor={"#7B8D42"} context={"前往OJ"} size={10} link={"/index"}
                        enter={true}></Button>
                <Button color={"#8D9192"} fontColor={"#F0F6DA"} context={"Github"} size={10}
                        link={"https://github.com/3072844942/NUCOJ"} enter={true}></Button>
                {/*<Button color={"#FFFFE5"} fontColor={"#E3D7A3"} context={"历届经历"} size={10} link={"/past"}*/}
                {/*        enter={true}></Button>*/}
            </div>
        </div>
    );
}

const mapStateToProps = (state: any) => {
    return {
        title: state.Controller.title
    }
}

const mapDispatchToProps = {
    getInfo() {
        return getBlogInfo()
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Index);