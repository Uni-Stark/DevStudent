package com.hours22.devstudent.Command.Delete;

import com.hours22.devstudent.Entity.Question;
import com.hours22.devstudent.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DeleteQuestion {
    @Autowired
    private QuestionRepository questionRepository;

    public Question deleteQuestion(String _id) {
        if (questionRepository.countBy_id(_id) == 0)
            return new Question("null", "Exception", null, new ArrayList<String>(), "Not Exist Question", "Not Exist Question");
        Question question = questionRepository.findQuestionBy_id(_id);
        questionRepository.delete(question);
        return question;
    }
}
