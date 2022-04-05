package com.app;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class OptionsQuestion extends Question {
    protected ArrayList<String> options;
    protected boolean multipleSelect;

    protected OptionsQuestion() {
        super();
    }

	public OptionsQuestion(String type,String question, ArrayList<String> options) {
		super(question);
		this.options = options;

		if(type.equals("radio")){
			this.multipleSelect = false;
		}
		else if(type.equals("checkbox")){
			this.multipleSelect = true;
		}
	}

	public OptionsQuestion(boolean multipleSelect, String question, ArrayList<String> answers) {
		super(question);
		this.options = answers;
		this.multipleSelect = multipleSelect;
	}

	public OptionsQuestion(String type, String question, ArrayList<String> options, boolean required) {
		super(type,question,required);
		this.options = options;
	}

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void addAnswer(String answer) {
        this.options.add(answer);
    }

    public boolean isMultipleSelect() {
        return multipleSelect;
    }

    public void setMultipleSelect(boolean multipleSelect) {
        this.multipleSelect = multipleSelect;
    }

    @Override
    public String toString() {
        return "com.app.OptionQuestion [question=" + this.question + ", answers=" + this.options.toString() + "]";
    }
}
