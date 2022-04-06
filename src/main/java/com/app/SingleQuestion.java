package com.app;

import javax.persistence.*;

@Entity
public class SingleQuestion extends Question {
    protected SingleQuestion() {
        super();
        type = "text";
    }

	public SingleQuestion(String question) {
        super(question);
        type = "text";
	}

	public SingleQuestion(String type, String question, boolean required) {
        super(type, question, required);
	}

	public String getType(){
		return this.type;
	}

    @Override
    public String toString() {
        return "com.app.SingleQuestion [question=" + question + ", type=" + type + "]";
    }
}
