import React from 'react';
import MarkDownTest1 from '../../MarkDownTEst/MarkDownTest1.md';

const HowToReply = ({_id,author,date,contents}) => {
    const ReactMarkdown = require('react-markdown/with-html');
    return (
        <ReactMarkdown
  source={contents}
  escapeHtml={false}
/>
    );
}

export default HowToReply;