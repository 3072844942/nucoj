import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter} from "react-router-dom";
import {Provider} from "react-redux";
import {persistor, Store} from "./store";
import {PersistGate} from "redux-persist/integration/react";
import Loading from "./view/loading/Loading";
import App from "./App";

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);
root.render(
    // <React.StrictMode>
        <Provider store={Store}>
            <PersistGate loading={<Loading />} persistor={persistor}>
                <BrowserRouter>
                    <App/>
                </BrowserRouter>
            </PersistGate>
        </Provider>
    // </React.StrictMode>
);
