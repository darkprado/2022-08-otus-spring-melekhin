package ru.otus.example.testingstudentspringbootshell.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import ru.otus.example.testingstudentspringbootshell.service.StudentTestingService;

/**
 * @author s.melekhin
 * @since 07 окт. 2022 г.
 */
@Component
public class TestingRespondent {

    private final StudentTestingService studentTestingService;

    public TestingRespondent(StudentTestingService studentTestingService) {
        this.studentTestingService = studentTestingService;
    }

    @EventListener
    public void onApplicationEvent(StartTestEvent startTestEvent) {
        System.out.printf("- %s%n", startTestEvent.getPayload());
        studentTestingService.test();
    }

}
