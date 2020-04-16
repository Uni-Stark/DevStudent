import React, { useState, useRef, useLayoutEffect } from "react";
import { Redirect } from "react-router-dom";
import { useMutation } from "@apollo/react-hooks";
import { LOGIN } from "mutation/mutations";
import { setAuthInfo, getAuthInfo } from "auth";
import LoginPageTemplate from "page-template/LoginPageTemplate/LoginPageTemplate";
const Login = ({ logIn, location }) => {
    const [loginToServer, { data }] = useMutation(LOGIN);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const btn_style = {
        backgroundColor: "#4BA0B5",
        color: "white",
    };

    const { auth } = getAuthInfo();

    const firstUpdate = useRef(true);
    useLayoutEffect(() => {
        if (firstUpdate.current) {
            firstUpdate.current = false;
            return;
        }
        if (data == null) return;
        if (data?.loginToServer.token) {
            logIn(data.loginToServer.nickname, email, data.loginToServer.token);
            return;
        } else {
            setPassword("");
            alert("로그인 시스템의 정보와 다릅니다!");
            return;
        }
    }, [data]);

    const { from } = location.state || { from: { pathname: "/" } };
    if (auth) return <Redirect to={from} />;

    return (
        <React.Fragment>
            <LoginPageTemplate
                logIn={logIn}
                authenticated={auth}
                setEmail={setEmail}
                password={password}
                setPassword={setPassword}
                email={email}
                loginToServer={loginToServer}
                btn_style={btn_style}
            />
        </React.Fragment>
    );
};

export default Login;
