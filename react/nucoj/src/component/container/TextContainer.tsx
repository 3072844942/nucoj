import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import Clipboard from "clipboard";
import toast from "../toast";
import CorrectIcon from "../../assets/icon/CorrectIcon";
import axios from "axios";
import {SUCCESS} from "../../constant/controller";
import BG from "../../assets/img/Background.png";
import JPG from "../../assets/img/TopBackground.jpg";
import FontIcon from "../../assets/icon/FontIcon";
import TimeIcon from "../../assets/icon/TimeIcon";
import UpdateIcon from "../../assets/icon/UpdateIcon";
import {getDate, markdownToHtml} from "../../util";
import Seat from "../bar/Seat";
import {marked} from "marked";
import Tocify from "../../util/Tocify";

/**
 * 文本容器
 * 公告/题解/分享
 * @param props
 * @constructor
 */
function TextContainer(props:{
    url: string,
    title?: string
}) {
    const [data, setData] = useState({
        id: '',
        title: '',
        time: 0,
        author: '',
        context: '',
    })
    const history = useNavigate();
    const tocify = new Tocify()


    useEffect(() => {
        // 添加代码复制功能
        let clipboard = new Clipboard('.copy-btn');
        clipboard.on("success", () => {
            toast('success', 'copyed success', <CorrectIcon/>);
        });
    }, [])

    useEffect(() => {
        axios.get(props.url)
            .then(res => {
                if (res.data.code === SUCCESS) setData(res.data.data)
                else history('/404')
            })
    }, [])

    useEffect(() => {
        document.title = data.title + ' | ' + props.title
    }, [data])

    return (
        <div style={{
            background: 'url(' + BG + ') center center / cover no-repeat',
            height: '100%',
        }}>
            {/*顶部图片*/}
            <div style={{
                background: 'url(' + JPG + ') center center / cover no-repeat',
                width: "100%",
                height: "35vh",
                overflow: "hidden",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                boxShadow: "0 4px 4px 4px rgba(7, 17, 27, .15)"
            }}>
                <div>
                    <h1 style={{
                        font: "bold 40px arial",
                        color: "white",
                        textAlign: 'center'
                    }}>
                        {data.title}
                    </h1>
                    <p style={{color: 'white', position: 'relative', top: '0', left: '0', textAlign: 'center'}}>
                        <FontIcon/>&nbsp;字数统计：{(data.context.length / 1000).toFixed(2)} k |&nbsp;
                        <TimeIcon/>&nbsp;阅读时长：{(data.context.length / 60).toFixed(1)} min |&nbsp;
                        <UpdateIcon/>&nbsp;发布时间：{getDate(data.time)}
                    </p>
                </div>
            </div>

            <div style={{
                width: '66vw',
                margin: "50px auto",
                display: 'flex',
                justifyContent: 'space-between'
            }}>
                {/*正文*/}
                <div style={{
                    width: "66vw",
                    borderRadius: "15px",
                    backgroundColor: "white",
                    boxShadow: "0 4px 6px 6px rgba(7, 17, 27, .1)",
                    padding: "50px 50px 0 50px"
                }}>
                    <div
                        className={"article-content markdown-body"}
                        dangerouslySetInnerHTML={{__html: markdownToHtml(data.context)}}></div>
                        {/*dangerouslySetInnerHTML={{__html: markdownToHtml("# 123")}}></div>*/}
                    <div style={{minHeight: '40px'}}></div>
                    {/*占位*/}
                </div>
                {/*侧边栏*/}
                {/*<div>*/}
                {/*    <div className="toc">{tocify && tocify.render()}</div>*/}
                {/*</div>*/}
            </div>
            <Seat height={10}/> {/*占位*/}
        </div>
    );
}

export default TextContainer;