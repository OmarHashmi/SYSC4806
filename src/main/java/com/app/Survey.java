package com.app;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Survey {
    public static final long publicID = -1;

    @Id
    @GeneratedValue
    private Long id;
    private Long userID;
    private String surveyName;
    private boolean closed;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Question> questions;

    /*
     * A default constructor
     */
    public Survey(){
        this.userID = publicID; // Default -1L = Public
        this.surveyName = "Default";
        this.questions = new ArrayList<>();
        this.closed = true;
    }

    /*
     * A constructor
     * @param creator {String}
     */
    public Survey(String surveyName){
        this.userID = publicID;
        this.surveyName = surveyName;
        this.questions = new ArrayList<>();
        this.closed = true;
    }

    /*
     * A constructor
     * @param creator {String}
     */
    public Survey(Long userID, String surveyName){
        this.userID = userID;
        this.surveyName = surveyName;
        this.questions = new ArrayList<>();
        this.closed = true;
    }

    public Survey(String surveyName, List<Question> questions){
        this.surveyName = surveyName;
        this.questions = questions;
        this.closed = true;
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
    public String getSurveyName() {
        return this.surveyName;
    }

    /*
     * Sets the creator
     * @param creator {String}
     */
    public void setSurveyName(String creator) {
        this.surveyName = creator;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String toString(){
		String str = "";

		for(Question q: this.questions){
			str += q.toString();
		}

		return str;
	}
}
