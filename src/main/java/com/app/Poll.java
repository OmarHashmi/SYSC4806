package com.app;
import javax.persistence.*;
import java.util.Collection;

@Entity

public class Poll {
    @Id
    @GeneratedValue
    private Long id;

    private String question;
    private String creator;
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Question> questions;

    /*
     * A default constructor
     */
    public Poll(){
        this(null);
    }

    /*
     * A constructor
     * @param creator {String}
     */
    public Poll(String creator){
        this(creator, null);
    }

    public Poll(String creator, Collection<Question> questions){
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
    public Collection<Question> getQuestions() {
        return questions;
    }

    /*
     * Sets the list of questions
     * @param questions {Collection<Question>}
     */
    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }


}
