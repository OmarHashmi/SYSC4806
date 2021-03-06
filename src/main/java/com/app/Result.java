package com.app;

import javax.persistence.*;

@Entity
public class Result {
    @Id
    @GeneratedValue
    private Long id;
    private String answer;

    public Result() {
        this.answer = "Default";
    }

    public Result(String answer){
        this.answer = answer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
