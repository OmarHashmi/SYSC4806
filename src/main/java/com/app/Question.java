package com.app;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Entity
public abstract class Question {
    @Id
    @GeneratedValue
    protected Long id;
    protected String question;
    protected boolean mandatory;
    protected String type;

    @OneToMany(cascade = CascadeType.ALL)
    protected List<Result> results;

    protected Question() {
        this.question = "Default";
        this.mandatory = false;
        this.results = new ArrayList<>();
    }

	public Question(String question) {
		this.question = question;
		this.mandatory = false;
		this.results = new ArrayList<>();
	}

	public Question(String type, String question, boolean mandatory) {
		this.type = type;
		this.question = question;
		this.mandatory = mandatory;
		this.results = new ArrayList<>();
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

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void addResult(Result result) {
        this.results.add(result);
    }

    public ArrayList<CountPair> analyseResults(){
        ArrayList<CountPair> sortedCounts = new ArrayList<>();
        for (Result result:results) {
            boolean check = true;
            for(CountPair pair: sortedCounts){
                if( pair.getResult() == result.getAnswer()) {
                    check = false;
                }
            }
            if(check){
                CountPair counted = new CountPair(result.getAnswer());
                for (Result innerResult: results) {
                    if((result.getAnswer() == innerResult.getAnswer()) && (result.getId() != innerResult.getId())){
                        counted.increment();
                    }
                }
                sortedCounts.add(counted);
            }
        }
        sortedCounts.sort(Comparator.comparingInt(CountPair::getCount));
        Collections.reverse(sortedCounts);
        return sortedCounts;
    }

    @Override
    public String toString() {
        return "com.app.Question [question=" + question + "]";
    }
}
