package ru.otus.example.testingstudentspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ru.otus.example.testingstudentspringboot.service.impl.StudentTestingServiceImpl;

@SpringBootApplication
public class TestingStudentSpringBootApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TestingStudentSpringBootApplication.class, args);
        StudentTestingServiceImpl studentTestingServiceImpl = (StudentTestingServiceImpl)context.getBean("studentTestingServiceImpl");
        studentTestingServiceImpl.test();
    }

}
