package com.hours22.devstudent.Command.Find;

import com.hours22.devstudent.Entity.Question;
import com.hours22.devstudent.Entity.QuestionScore;
import com.hours22.devstudent.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class FindQuestionsByOption extends Find {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Question> findQuestionsByOption(String param, String option, String searchContent, int pageNum, int requiredCount) {
        if(option.equals("author")) {
            option = "author.$id";
        }
        if(!option.equals("title and content")){
            Criteria criteria = new Criteria(option);
            criteria.regex(searchContent, "i");
            return getQuestions(param, pageNum, requiredCount, criteria);
        }

        TextIndexDefinition textIndexDefinition = new TextIndexDefinition.TextIndexDefinitionBuilder().onField("title", 2F).onField("content").build();
        mongoTemplate.indexOps(Question.class).ensureIndex(textIndexDefinition);

        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(searchContent);

        Query query = TextQuery.queryText(textCriteria).sortByScore();
        query.limit(requiredCount);
        query.skip((pageNum - 1) * requiredCount);
        List<Question> questions = this.mongoTemplate.find(query, Question.class);
        System.out.println("Option 테스트");
        for(Question question : questions){
            System.out.println(question.toString());
        }
        System.out.println("Option 테스트 End");
        return questions;
        /*Criteria criteria = new Criteria(option);
        criteria.regex(searchContent, "i");
        return getQuestions(param, pageNum, requiredCount, criteria);*/
    }
}
