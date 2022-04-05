package com.app;

public class CountPair {
    String result;
    int count;
    public CountPair(String result){
        this.result = result;
        count = 1;
    }
    public void increment(){
        count= count + 1;
    }
    public String getResult() {
        return result;
    }

    public int getCount() {
        return count;
    }

}
