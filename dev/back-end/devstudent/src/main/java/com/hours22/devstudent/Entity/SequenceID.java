package com.hours22.devstudent.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "Sequence")
public class SequenceID {
    @Id
    private String _id;
    private int seqNum;

    public SequenceID(String _id, int seqNum) {
        this._id = _id;
        this.seqNum = seqNum;
    }
}