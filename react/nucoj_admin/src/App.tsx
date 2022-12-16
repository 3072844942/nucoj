import React, {useState} from 'react';
import RouterView from "./router/RouterView";
import SideBar from "./component/bar/SideBar";

import 'antd/dist/antd.min.css'
import 'highlight.js/styles/atom-one-light.css'
import './assets/css/markdown.scss'
import './App.scss'
import './assets/js/hidden'
import 'md-editor-rt/lib/style.css';
import TopBar from "./component/bar/TopBar";

function App() {
    const [collapsed, setCollapsed] = useState(false);

    // 如果从客户端传过来直接登录
    /*const params = useParams();

    useEffect(() => {
        const email = params.email
        const password = params.password
        console.log(email, password)
        if (email != null && password != null)
            login(email, password)
    }, [])*/

    return (
        <div
            style={{
                display: 'flex'
            }}
        >
            <SideBar collapsed={collapsed} setCollapsed={setCollapsed}/>
            <div
                style={{
                    width: collapsed ? '97vw' : '88vw',
                    left: collapsed ? '3vw' : '12vw',
                    transition: 'margin .3s ease-out',
                    animation: 'emergence .2s'
                }}
            >
                <TopBar/>
                <RouterView/>
            </div>
        </div>
    );
}

export default App;