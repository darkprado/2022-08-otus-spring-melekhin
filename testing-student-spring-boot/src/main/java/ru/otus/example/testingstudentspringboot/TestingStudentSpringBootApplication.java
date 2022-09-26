package ru.otus.example.testingstudentspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ru.otus.example.testingstudentspringboot.service.StudentTestingService;

@SpringBootApplication
public class TestingStudentSpringBootApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TestingStudentSpringBootApplication.class, args);
        StudentTestingService studentTestingService = (StudentTestingService)context.getBean("studentTestingServiceImpl");
        studentTestingService.test();
    }

}
