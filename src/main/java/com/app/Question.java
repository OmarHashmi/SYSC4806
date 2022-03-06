package com.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    private String question;
    private String answer;

    protected Question() {}

    public Question(Long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public Question(String question, String answer) {
        this.id = 0L;
        this.question = question;
        this.answer = answer;
    }

    public Question(Long id, String question) {
        this.id = id;
        this.question = question;
        this.answer = "";
    }

    public Question(String question) {
        this.id = 0L;
        this.question = question;
        this.answer = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "com.app.Question [question=" + question + ", answer=" + answer + "]";
    }
}
