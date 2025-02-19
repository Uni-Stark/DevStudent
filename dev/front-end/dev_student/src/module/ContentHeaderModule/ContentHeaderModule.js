import React from "react";
// css
import "./ContentHeaderModule.css";

// atom
import Avatar from "atom/Avatar/Avatar";

// img
import GradeAvatar from "atom/GradeAvatar/GradeAvatar";

const ContentHeaderModule = (props) => {
    const { _id, title, authorNickname, authorGrade, date, dateToText, views, mine, adoptedAnswerId } = props;
    const HeaderBadge = () => {
        if (adoptedAnswerId) {
            return <span className="solved-bagde">SOLVED</span>;
        } else {
            return <span className="not-solved-bagde">HELP WANTED</span>;
        }
    };
    const MineBadge = () => {
        if (mine) {
            return <span className="mine-bagde">내글</span>;
        } else {
            return <p></p>;
        }
    };
    return (
        <React.Fragment>
            <div className="content-header-header">
                <span className="question-header">{title}</span>
                <span className="quetsion-id">&nbsp;#{_id}</span>
                <HeaderBadge />
                <MineBadge />
            </div>
            <div className="content-intro-wrapper-div">
                <div className="avatar-box">
                    <Avatar img={GradeAvatar(authorGrade)} />
                </div>
                <div className="author-date-wrapper">
                    <div className="author-box">{authorNickname}</div>
                    <div className="date-box">
                        {date} {dateToText}
                    </div>
                </div>
            </div>
        </React.Fragment>
    );
};

export default ContentHeaderModule;
