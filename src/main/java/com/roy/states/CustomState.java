package com.roy.states;

public abstract class CustomState {

    private String state;

    public CustomState(String state){
        this.state = state;
    }

    public String state(){
        return state;
    }
}
