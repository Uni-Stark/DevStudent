import React, { useEffect, useState } from "react";
import { Container } from "reactstrap";
import axios from "axios";
//pageTemplate
import AboutUsPageTemplate from "page-template/AboutUsPageTemplate/AboutUsPageTemplate";
//atoms
import MarkdownParser from "atom/MarkdownParser/MarkdownParser";
// items
import { text } from "item/DevNoteJson/DevNoteJsonItem.txt";
const DevNote = () => {
    const [readme, setReadme] = useState();
    useEffect(() => {
        axios.get("https://raw.githubusercontent.com/22hours/DevStudent/master/Update.md").then((response) => {
            setReadme(response.data);
            console.log(response.data);
        });
    }, [1]);
    return (
        <React.Fragment>
            {/* <Container style={{ marginTop: "50px" }}>
                <MarkdownParser content={text} />
            </Container> */}
            {/* <AboutUsPageTemplate /> */}
            <Container>
                <MarkdownParser content={readme} />
            </Container>
        </React.Fragment>
    );
};

export default DevNote;
