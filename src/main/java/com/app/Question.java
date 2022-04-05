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
    protected boolean required;
    protected String type;
	protected String html;

	public String getHTML() {
		return html;
	}

    @OneToMany(cascade = CascadeType.ALL)
    protected List<Result> results;

    protected Question() {
        this.question = "Default";
        this.required = false;
        this.results = new ArrayList<>();
    }

	public Question(String question) {
		this.question = question;
		this.required = false;
		this.results = new ArrayList<>();
	}

	public Question(String type, String question, boolean required) {
		this.type = type;
		this.question = question;
		this.required = required;
		this.results = new ArrayList<>();

		String str="";
		str += "<label for=\"" +this.id+ "\">";
		str += "<input class=\"questionAnswer\" type=\"" +this.id+ "\" id=\"" +this.id+ "\" name=\"" +this.id+'"';
		if(this.required){
			str += " required/>";
		}else{
			str += "/>";
		}
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

    public boolean isRequired() {
        return required;
    }

	public boolean isMandatory() {
		return this.isRequired();
	}

    public void setRequired(boolean required) {
        this.required = required;
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
