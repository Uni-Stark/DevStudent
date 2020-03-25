package com.hours22.devstudent.Entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@ToString
@Getter
public class Answer {
    @Id
    private String _id; // 고유 number
    private String author;
    private String content;
    private String date;
    public Answer(String _id, String author, String content, String date){
        this._id = _id;
        this.author = author;
        this.content = content;
        this.date = date;
    }
}