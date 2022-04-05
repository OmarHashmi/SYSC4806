package com.app;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class OptionsQuestion extends Question {
    protected ArrayList<String> answers;
    protected boolean multipleSelect;

    protected OptionsQuestion() {
        super();
    }

    public OptionsQuestion(String question, ArrayList<String> answers) {
        super(question);
        this.answers = answers;
        this.multipleSelect = false;
    }

    public OptionsQuestion(boolean multipleSelect, String question, ArrayList<String> answers) {
        super(question);
        this.answers = answers;
        this.multipleSelect = multipleSelect;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public void addAnswer(String answer) {
        this.answers.add(answer);
    }

    public boolean isMultipleSelect() {
        return multipleSelect;
    }

    public void setMultipleSelect(boolean multipleSelect) {
        this.multipleSelect = multipleSelect;
    }

    @Override
    public String toString() {
        return "com.app.OptionQuestion [question=" + this.question + ", answers=" + this.answers.toString() + "]";
    }
}
