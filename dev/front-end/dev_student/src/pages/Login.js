import React, { useState, useRef, useLayoutEffect } from "react";
import { Redirect } from "react-router-dom";
import LoginPageTemplate from "page-template/LoginPageTemplate/LoginPageTemplate";
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from "reactstrap";
import { Link } from "react-router-dom";
import { hashPassword } from "auth";

import { POST, LOGIN_TO_SERVER } from "rest";

const Login = ({ logIn, location }) => {
    // const [loginToServer, { data }] = useMutation(LOGIN);
    const [data, setData] = useState(null);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [btnClick, setBtnClick] = useState(false);

    const btn_style = {
        backgroundColor: "#4BA0B5",
        color: "white",
        fontSize: "16px",
    };
    const modal_style = {
        padding: "10px",
        paddingLeft: "15px",
    };

    const modal_header_style = {
        paddingTop: "10px",
        paddingBottom: "10px",
        fontSize: "16px",
    };

    const modal_btn_style = {
        padding: "3px",
    };

    const auth = localStorage.getItem("auth");
    const [modal, setModal] = useState(false);
    const toggle = () => setModal(!modal);

    const AlertModal = () => {
        return (
            <Modal isOpen={modal}>
                <ModalHeader style={modal_header_style}>
                    <b>닉네임 설정</b>
                </ModalHeader>
                <ModalBody style={modal_style}>닉네임을 설정해주세요.</ModalBody>
                <ModalFooter style={modal_btn_style}>
                    <Link to="/nickname">
                        <Button color="info">확인</Button>
                    </Link>
                </ModalFooter>
            </Modal>
        );
    };
    const loginToServer = () => {
        POST("post", LOGIN_TO_SERVER, { email: email, password: hashPassword(password) })
            .then((response) => setData(response))
            .catch((error) => console.log(error));
    };
    const firstUpdate = useRef(true);
    useLayoutEffect(() => {
        if (firstUpdate.current) {
            firstUpdate.current = false;
            return;
        }
        if (data == null) return;
        if (data?.nickname) {
            logIn(data);
        } else if (data.accessToken) {
            if (data.email === "null") {
                alert("이메일 인증 후 사용해주세요.");
                setBtnClick(false);
            } else {
                window.sessionStorage.setItem("token", data.accessToken);
                window.sessionStorage.setItem("email", data.email);
                setPassword("");
                toggle();
            }
        } else {
            setPassword("");
            alert("로그인 시스템의 정보와 다릅니다!");
            setBtnClick(false);
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
                btnClick={btnClick}
                setBtnClick={setBtnClick}
            />
            <AlertModal />
        </React.Fragment>
    );
};

export default Login;
