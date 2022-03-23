package com.app;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Survey {
    @Id
    @GeneratedValue
    private Long id;
    
    private String creator;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Question> questions;

    /*
     * A default constructor
     */
    public Survey(){
        this.creator = "Default";
        this.questions = new ArrayList<>();
    }

    /*
     * A constructor
     * @param creator {String}
     */
    public Survey(String creator){
        this.creator = creator;
        this.questions = new ArrayList<>();
    }

    public Survey(String creator, List<Question> questions){
        this.creator = creator;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
     * Retrieve the creator
     * @returns {String}
     */
    public String getCreator() {
        return this.creator;
    }

    /*
     * Sets the creator
     * @param creator {String}
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /*
     * Retrieve the list of questions
     * @returns {Collection<Question>}
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /*
     * Sets the list of questions
     * @param questions {Collection<Question>}
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

	public String toString(){
		String str = "";

		for(Question q: this.questions){
			str += q.toString();
		}

		return str;
	}
}
