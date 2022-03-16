package com.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue
    protected Long id;

    protected String question;

    @OneToMany
    protected List<Result> results;

    protected Question() {}

    public Question(Long id, String question) {
        this.id = id;
        this.question = question;
    }

    public Question(String question) {
        this.id = 0L;
        this.question = question;
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

    public List<Result> getResutls() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "com.app.Question [question=" + question + "]";
    }
}
