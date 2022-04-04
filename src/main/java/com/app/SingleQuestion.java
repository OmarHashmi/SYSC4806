package com.app;

import javax.persistence.*;

@Entity
public class SingleQuestion extends Question {

    protected String type;

    protected SingleQuestion() {
        super();
        type = "text";
    }

	public SingleQuestion(String question) {
        super(question);
        type = "text";
	}

	public SingleQuestion(String type, String question) {
        super(question);
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

    @Override
    public String toString() {
        return "com.app.SingleQuestion [question=" + question + ", type=" + type + "]";
    }
}
