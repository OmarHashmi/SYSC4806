package com.app;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue
    protected Long id;

    protected String question;

    @OneToMany
    protected List<Result> results;

    protected Question() {
        results = new ArrayList<>();
    }

    public Question(String question) {
        this.question = question;
        results = new ArrayList<>();
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

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void addResult(Result result) {
        results.add(result);
    }

    @Override
    public String toString() {
        return "com.app.Question [question=" + question + "]";
    }
}
