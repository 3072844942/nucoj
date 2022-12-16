import React, {useState} from 'react';

import {sendEmail, register,} from "../../action/user";
import {connect} from "react-redux";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Input from "@mui/material/Input";
import InputAdornment from "@mui/material/InputAdornment";
import {AccountCircle} from "@mui/icons-material";
import IconButton from "@mui/material/IconButton";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import MyButton from "../button/Button";
import Button from '@mui/material/Button';
import toast from "../toast";
import ErrorIcon from "../../assets/icon/ErrorIcon";
import {hiddenRegister, showLogin} from "../../action/controller";
import Background from "../background";
import {useInterval} from "../../util/useInterval";

interface State {
    amount: string;
    email: string;
    code: string;
    password: string;
    weight: string;
    weightRange: string;
    showPassword: boolean;
}

/**
 * 注册框
 * @param props
 * @constructor
 */
function Register(props:any) {
    const [values, setValues] = useState<State>({
        amount: '',
        email: '',
        code: '',
        password: '',
        weight: '',
        weightRange: '',
        showPassword: false,
    });
    const [time, setTime] = useState(0);

    useInterval(() => {
        if (time > 0)
            setTime(time - 1)
    }, 1000);

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
        }}>
            <Background color={'rgb(33, 33, 33)'} onClick={() => props.hiddenRegister()}></Background>
            <div style={{
                backgroundColor: '#fff',
                width: '30%',
                height: '60%',
                margin: 'auto',
                padding: '6vh 4vw 0 3vw',
                borderRadius: '50px',
                zIndex: '1000',
                animation: 'emergence 0.2s ease-in',
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
                                props.register(values.email, values.password, values.code)
                        }}
                    />
                </FormControl>
                {/*注册码*/}
                <FormControl fullWidth={true} sx={{ m: 1, width: '100%' }} style={{marginTop: '3vh'}}  variant="standard">
                    <InputLabel htmlFor="standard-adornment-code">Code</InputLabel>
                    <Input
                        id="standard-adornment-code"
                        type={'text'}
                        value={values.code}
                        onChange={handleChange('code')}
                        endAdornment={
                            <InputAdornment position="end">
                                <Button disabled={time != 0} onClick={() => {
                                    props.sendEmail(values.email)
                                    setTime(60)
                                }}>{time == 0 ? '发送' : time + '秒'}</Button>
                            </InputAdornment>
                        }
                        onKeyDown={(event) => {
                            if (event.key === 'Enter')
                                props.register(values.email, values.password, values.code)
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
                                props.register(values.email, values.password, values.code)
                        }}
                    />
                </FormControl>
                {/*注册按钮*/}
                <div style={{
                    marginTop: '5vh',
                    padding: '0 0 0 35%'
                }}>
                    <MyButton
                        context={'注册'}
                        color={'red'}
                        fontColor={'white'}
                        size={7}
                        enter={true}
                        onClick={() => {
                            props.register(values.email, values.password, values.code)
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
                    <p style={{color: 'black', cursor: 'pointer'}} onClick={() => {props.showLogin(); props.hiddenRegister()}}>已有账号？登录</p>
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
    hiddenRegister() {
        return hiddenRegister();
    },

    register(email:string, password:string, code: string) {
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
        // const regCode = /^(?=.*\d)[^]{6,6}$/
        // if (!regCode.test(code)) {
        //     toast('fail', "验证码输入有误", <ErrorIcon/>)
        //     return null
        // }
        return register(email, password, code);
    },

    showLogin() {
        return showLogin()
    },

    sendEmail(email:string) {
        return sendEmail(email)
    }
}


export default connect(mapStateToProps, mapDispatchToProps)(Register);