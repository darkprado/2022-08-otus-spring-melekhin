package ru.otus.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import ru.otus.example.service.StudentTestingService;

/**
 * @author s.melekhin
 * @since 12 сент. 2022 г.
 */
@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class StudentTestingApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(StudentTestingApplication.class);
        StudentTestingService studentTestingService = context.getBean(StudentTestingService.class);
        studentTestingService.test();
    }

}
