package ru.otus.example.testingstudentspringbootshell.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import ru.otus.example.testingstudentspringbootshell.events.EventsPublisher;

/**
 * @author s.melekhin
 * @since 07 окт. 2022 г.
 */
@ShellComponent
public class ApplicationEventsCommands {

    private final EventsPublisher eventsPublisher;

    public ApplicationEventsCommands(EventsPublisher eventsPublisher) {
        this.eventsPublisher = eventsPublisher;
    }

    @ShellMethod(value = "start testing", key = {"start"})
    public void start() {
        eventsPublisher.publish();
    }

    @ShellMethod(value = "stop program", key = {"stop"})
    public void stop() {
        System.out.println("Program is stopping");
        System.exit(777);
    }

}
