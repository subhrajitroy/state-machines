package com.roy.states;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.state.State;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World");
        HashSet<CustomState> customStates = new HashSet<>();
        OpenCustomState openState = new OpenCustomState();
        customStates.add(openState);
        CloseCustomState closeState = new CloseCustomState();
        customStates.add(closeState);
        StateMachineBuilder.Builder<CustomState, Event> builder = StateMachineBuilder.builder();
        builder.configureStates().withStates().initial(openState).states(customStates);

        ButtonPushedEvent buttonPushedEvent = new ButtonPushedEvent();
        builder.configureTransitions().withExternal().source(openState).target(closeState)
                .event(buttonPushedEvent)
                .and()
                .withExternal().source(closeState).target(openState).event(buttonPushedEvent);

        StateMachine<CustomState, Event> stateMachine = builder.build();
        stateMachine.start();
        System.out.println(makeTransitions(buttonPushedEvent, stateMachine).state());
        System.out.println(makeTransitions(buttonPushedEvent, stateMachine).state());
        System.out.println(makeTransitions(buttonPushedEvent, stateMachine).state());
    }

    private static CustomState makeTransitions(ButtonPushedEvent buttonPushedEvent, StateMachine<CustomState, Event> stateMachine) {
        stateMachine.sendEvent(buttonPushedEvent);
        State<CustomState, Event> state = stateMachine.getState();
        return state.getId();
    }
}
