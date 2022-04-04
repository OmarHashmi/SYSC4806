package com.app;

import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Entity
public class Question {
    @Id
    @GeneratedValue
    protected Long id;
    protected String question;
	protected String type;
    protected boolean mandatory;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

	public Question(String type, String question) {
		this.type = type;
		this.question = question;
        this.mandatory = false;
		this.results = new ArrayList<>();
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getType(){
		return this.type;
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
                        System.out.println("Got Inside Increment if");
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
