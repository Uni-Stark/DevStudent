import React from "react";
import "./HowToRuleItem.css";
const HowToRuleItem = ({ id, nowClicked, setClicked, icon, title, message }) => {
    const ItemBox = ({ children }) => {
        if (nowClicked === id) {
            return <div className="howto-rule-item-box clicked">{children}</div>;
        } else {
            return <div className="howto-rule-item-box">{children}</div>;
        }
    };
    return (
        <div className="HowToRuleItem" onClick={() => setClicked(id)}>
            <ItemBox>
                <div className="title">
                    {icon} &nbsp;
                    {title}
                </div>
                <div>{message}</div>
            </ItemBox>
        </div>
    );
};

export default HowToRuleItem;
