package ru.otus.example.testingstudentspringbootshell.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author s.melekhin
 * @since 07 окт. 2022 г.
 */
@Service
public class StartTestEventPublisher implements EventsPublisher {

    private final ApplicationEventPublisher publisher;

    public StartTestEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish() {
        publisher.publishEvent(new StartTestEvent(this));
    }

}
