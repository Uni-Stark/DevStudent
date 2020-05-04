import React, { useEffect } from "react";
import { useMutation } from "@apollo/react-hooks";
import axios from "axios";

// muatations
import { CREATE_QUESTION } from "mutation/mutations";

// utils
import { timeForToday, getTimeStamp } from "util/time";

// page-template
import NewQuestionPageTemplate from "page-template/NewQuestionPageTemplate/NewQuestionPageTemplate";
const NewQuestion = () => {
    console.log(getTimeStamp());
    const url = "https://devstu.koreaelection.shop/uploadRealFile/" + getTimeStamp() + "";
    console.log(url);
    const [createQuestion] = useMutation(CREATE_QUESTION);
    useEffect(() => {
        return () => {
            console.log("cleaned up");
            sessionStorage.clear();
        };
    }, []);
    const handleSubmit = async (authorParam, titleParam, contentParam, tagsParam) => {
        console.log(contentParam);
        axios
            .post(url, JSON.parse(sessionStorage.getItem("devstu_imgs")))
            .then((response) => {
                createQuestion({
                    variables: {
                        author: authorParam,
                        title: titleParam,
                        content: contentParam,
                        tags: tagsParam,
                    },
                })
                    .then((response) => {
                        alert("질문을 저장했습니다.");
                        window.location.href = "https://devstudent.web.app/howto";
                    })
                    .catch((err) => {
                        alert(err.messeage);
                    });
            })
            .catch((error) => {});
    };

    return (
        <React.Fragment>
            <NewQuestionPageTemplate handleSubmit={handleSubmit}></NewQuestionPageTemplate>
        </React.Fragment>
    );
};

export default NewQuestion;
