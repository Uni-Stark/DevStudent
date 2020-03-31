package com.hours22.devstudent.Command;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hours22.devstudent.Command.Find.FindAllQuestions;
import com.hours22.devstudent.Command.Find.FindQuestionBy_id;
import com.hours22.devstudent.Command.Find.FindQuestionsByOption;
import com.hours22.devstudent.Command.Find.FindQuestionsByTags;
import com.hours22.devstudent.Entity.Question;
import com.hours22.devstudent.Repository.QuestionRepository;
import com.hours22.devstudent.Repository.SequenceIDRepository;
import com.hours22.devstudent.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    @Autowired
    FindAllQuestions findAllQuestions;
    @Autowired
    FindQuestionsByTags findQuestionsByTags;
    @Autowired
    FindQuestionBy_id findQuestionBy_id;
    @Autowired
    FindQuestionsByOption findQuestionsByOption;

    public Query(QuestionRepository questionRepository, UserRepository userRepository){
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public List<Question> findAllQuestions(String param, int pageNum, int requiredCount) {
        return findAllQuestions.findAllQuestions(param,pageNum,requiredCount);
    }

    public List<Question> findQuestionsByOption(String param, String option, String searchContent, int pageNum, int requiredCount) {
        return findQuestionsByOption.findQuestionsByOption(param,option,searchContent,pageNum,requiredCount);
    }

        public Question findQuestionBy_id(String _id) {
        return findQuestionBy_id.findQuestionBy_id(_id);
        
    }

    public List<Question> findQuestionsByTags(String param, int pageNum, int requiredCount, List<String> tags, String logical) {
        return findQuestionsByTags.findQuestionsByTags(param, pageNum, requiredCount, tags, logical);
    }

    public Boolean checkDuplicate(String _id){
        return !userRepository.existsBy_id(_id);
    }
}