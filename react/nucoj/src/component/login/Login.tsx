import React, {useState} from 'react';
import {login} from "../../action/user";
import {connect} from "react-redux";

import IconButton from '@mui/material/IconButton';
import Input from '@mui/material/Input';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import FormControl from '@mui/material/FormControl';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import {AccountCircle} from "@mui/icons-material";
import Button from "../button/Button";
import toast from "../toast";
import ErrorIcon from "../../assets/icon/ErrorIcon";
import {hiddenLogin, showForget, showRegister} from "../../action/controller";
import Background from "../background";

interface State {
    amount: string;
    email: string;
    password: string;
    weight: string;
    weightRange: string;
    showPassword: boolean;
}

/**
 * 登录框
 * @param props
 * @constructor
 */
function Login(props:any) {
    const [values, setValues] = useState<State>({
        amount: '',
        email: '',
        password: '',
        weight: '',
        weightRange: '',
        showPassword: false,
    });

    const handleChange =
        (prop: keyof State) => (event: React.ChangeEvent<HTMLInputElement>) => {
            setValues({ ...values, [prop]: event.target.value });
        };

    const handleClickShowPassword = () => {
        setValues({
            ...values,
            showPassword: !values.showPassword,
        });
    };

    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };


    return (
        <div style={{
            width: '100vw',
            height: '100vh',
            display: 'flex',
            alignItems: 'center',
            justifyItems: 'center',
            position: 'fixed',
            left: '0',
            top: '0',
            zIndex: '5'
        }} >
            <Background color={'rgb(33, 33, 33)'} onClick={() => props.hiddenLogin()}></Background>
            <div style={{
                backgroundColor: '#ffffff',
                width: '30vw',
                height: '50vh',
                opacity: '1',
                margin: 'auto',
                padding: '6vh 4vw 0 3vw',
                borderRadius: '50px',
                animation: 'emergence 0.2s ease-in',
                zIndex: '1000',
            }} onClick={(evt) => evt.stopPropagation()}>
                {/*用户名*/}
                <FormControl fullWidth={true} sx={{ m: 1, width: '100%' }} variant="standard">
                    <InputLabel htmlFor="standard-adornment-email">Email</InputLabel>
                    <Input
                        id="standard-adornment-email"
                        type={'text'}
                        value={values.email}
                        onChange={handleChange('email')}
                        endAdornment={
                            <InputAdornment position="end">
                                <AccountCircle />
                            </InputAdornment>
                        }
                        onKeyDown={(event) => {
                            if (event.key === 'Enter')
                                props.loginUser(values.email, values.password)
                        }}
                    />
                </FormControl>
                {/*密码*/}
                <FormControl fullWidth={true} sx={{ m: 1, width: '100%' }} style={{marginTop: '5vh'}} variant="standard">
                    <InputLabel htmlFor="standard-adornment-password">Password</InputLabel>
                    <Input
                        id="standard-adornment-password"
                        type={values.showPassword ? 'text' : 'password'}
                        value={values.password}
                        onChange={handleChange('password')}
                        endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowPassword}
                                    onMouseDown={handleMouseDownPassword}
                                >
                                    {values.showPassword ? <Visibility /> : <VisibilityOff />}
                                </IconButton>
                            </InputAdornment>
                        }
                        onKeyDown={(event) => {
                            if (event.key === 'Enter')
                                props.loginUser(values.email, values.password)
                        }}
                    />
                </FormControl>
                {/*登录按钮*/}
                <div style={{
                    marginTop: '5vh',
                    padding: '0 0 0 35%'
                }}>
                    <Button
                        context={'登录'}
                        color={'#008dff'}
                        fontColor={'white'}
                        size={7}
                        enter={true}
                        onClick={() => {
                            props.loginUser(values.email, values.password)
                        }}
                    />
                </div>
                {/*注册 & 忘记密码*/}
                <div style={{
                    marginTop: '2vh',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'space-between'
                }}>
                    <p style={{color: 'black', cursor: 'pointer'}} onClick={() => {props.hiddenLogin(); props.showRegister()}}>立即注册</p>
                    <p style={{color: 'black', cursor: 'pointer'}} onClick={() => {props.hiddenLogin(); props.showForget()}}>忘记密码？</p>
                </div>
            </div>
        </div>
    );
}

const mapStateToProps = () => {
    return {
    }
}

const mapDispatchToProps = {
    hiddenLogin() {
        return hiddenLogin();
    },

    loginUser(email:string, password:string) {
        // 验证
        const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
        if (email !== "root" && !regEmail.test(email)) {
            toast('fail', "邮箱输入有误", <ErrorIcon/>)
            return null
        }
        const regPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{6,16}$/
        if (!regPassword.test(password)) {
            toast('fail', "密码输入有误，密码必须为大小写字母和数字的组合，长度在6-16之间", <ErrorIcon/>)
            return null
        }
        return login(email, password);
    },

    showRegister() {
        return showRegister()
    },

    showForget() {
        return showForget()
    }
}


export default connect(mapStateToProps, mapDispatchToProps)(Login);