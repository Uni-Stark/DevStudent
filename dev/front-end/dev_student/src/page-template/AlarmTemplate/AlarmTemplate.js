import React from "react";
import { Container } from "reactstrap";
import { Alert, AlertTitle } from "@material-ui/lab";
// css
import "./AlarmTemplate.css";

// items
import AlarmItem from "item/AlarmItem/AlarmItem";

const AlarmTemplate = ({ alarms }) => {
    const alramlist = alarms.map(({ _id, question_id, user_id, respondent, content, date, read }) => (
        <AlarmItem
            key={_id}
            question_id={question_id}
            respondent={respondent}
            content={content}
            date={date}
            read={read}
        />
    ));
    const alarmCount = alarms.length;
    return (
        <Container>
            <div className="margin-top-3">
                <div className="alarm-wrapper">
                    <span className="content-header">알림</span>
                </div>
                <div>
                    <Alert style={{ marginBottom: "10px" }} severity="info">
                        {alarmCount}건의 알림이 있어요
                    </Alert>
                    <div className="alarm-list-wrapper">
                        {alramlist}
                        {/* <AlarmItem url={"1239"} content={"아 배고파"}/>
                    <AlarmItem url={"1239"} content={"아 배고파"}/>
                    <AlarmItem url={"1239"} content={"아 배고파"}/>
                    <AlarmItem url={"1239"} content={"아 배고파"}/>
                    <AlarmItem url={"1239"} content={"아 배고파"}/> */}
                    </div>
                </div>
            </div>
        </Container>
    );
};

export default AlarmTemplate;
