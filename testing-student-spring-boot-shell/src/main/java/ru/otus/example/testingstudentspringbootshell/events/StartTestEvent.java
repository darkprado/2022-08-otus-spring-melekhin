package ru.otus.example.testingstudentspringbootshell.events;

import org.springframework.context.ApplicationEvent;

/**
 * @author s.melekhin
 * @since 07 окт. 2022 г.
 */
public class StartTestEvent extends ApplicationEvent {

    private final String payload;

    public StartTestEvent(Object source) {
        super(source);
        payload = "Start testing";
    }

    public String getPayload() {
        return payload;
    }
}
