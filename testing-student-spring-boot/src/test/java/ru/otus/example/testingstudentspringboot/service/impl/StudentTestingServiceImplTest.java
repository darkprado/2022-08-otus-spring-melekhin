package ru.otus.example.testingstudentspringboot.service.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.otus.example.testingstudentspringboot.entity.Answer;
import ru.otus.example.testingstudentspringboot.entity.Student;
import ru.otus.example.testingstudentspringboot.entity.StudentTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author s.melekhin
 * @since 23 сент. 2022 г.
 */
@DisplayName("StudentTestingServiceImpl class test")
@SpringBootTest
public class StudentTestingServiceImplTest {

    private static final String SUCCESS_KEY = "success";
    private static final String FAIL_KEY = "fail";
    private StudentTest studentTest;
    private Map<String, Answer> answers;
    @Autowired
    private StudentTestingServiceImpl studentTestingService;

    @BeforeEach
    public void init() {
        Student student = new Student();
        student.setFirstname("Ivan");
        student.setLastname("Ivanov");
        answers = new HashMap<>();
        studentTest = new StudentTest(student, answers, 1);
    }

    @DisplayName("check success student test")
    @Test
    public void checkSuccessStudentTest() {
        studentTestingService.setLocale(new Locale("en"));
        String expectedResult = "Student Ivan Ivanov, testing success, gain 1 point.";
        answers.put("Кто?", new Answer("я", true));
        assertEquals(expectedResult, studentTestingService.getResult(SUCCESS_KEY, studentTest));
    }

    @DisplayName("check fail student test")
    @Test
    public void checkFailStudentTest() {
        studentTestingService.setLocale(new Locale("en"));
        String expectedResult = "Student Ivan Ivanov, testing fail, gain 0 point.\nIncorrect answers:\n"
                + "who?\n"
                + "i\n"
                + "who other?\n"
                + "you\n";
        answers.put("who?", new Answer("i", false));
        answers.put("who other?", new Answer("you", false));
        assertEquals(expectedResult, studentTestingService.getResult(FAIL_KEY, studentTest));
    }


    @DisplayName("check en locale")
    @Test()
    public void checkLocaleEnStudentTest() {
        studentTestingService.setLocale(new Locale("en"));
        answers.put("who?", new Answer("i", false));
        answers.put("who other?", new Answer("you", false));
        assertTrue(studentTestingService.getResult(FAIL_KEY, studentTest).startsWith("Student"));
    }

    @DisplayName("check ru locale")
    @Test()
    public void checkLocaleRuStudentTest() {
        studentTestingService.setLocale(new Locale("ru_RU"));
        answers.put("who?", new Answer("i", false));
        answers.put("who other?", new Answer("you", false));
        assertTrue(studentTestingService.getResult(FAIL_KEY, studentTest).startsWith("Студент"));
    }

}
