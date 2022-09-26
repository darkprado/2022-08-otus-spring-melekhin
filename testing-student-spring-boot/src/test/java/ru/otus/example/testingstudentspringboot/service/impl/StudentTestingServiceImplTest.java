package ru.otus.example.testingstudentspringboot.service.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.example.testingstudentspringboot.entity.Answer;
import ru.otus.example.testingstudentspringboot.entity.Student;
import ru.otus.example.testingstudentspringboot.entity.StudentTest;
import ru.otus.example.testingstudentspringboot.utils.IOService;
import ru.otus.example.testingstudentspringboot.utils.Reader;

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
    @Autowired
    private MessageServiceImpl messageService;
    @MockBean
    private IOService ioService;
    @MockBean
    Reader reader;
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
        messageService.setLocale(new Locale("en"));
        String expectedResult = "Student Ivan Ivanov, testing success, gain 1 point.";
        answers.put("Кто?", new Answer("я", true));
        try {
            Method getResult = studentTestingService.getClass().getDeclaredMethod("getResult", String.class, StudentTest.class);
            getResult.setAccessible(true);
            String result = (String)getResult.invoke(studentTestingService, SUCCESS_KEY, studentTest);
            assertEquals(expectedResult, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("check fail student test")
    @Test
    public void checkFailStudentTest() {
        messageService.setLocale(new Locale("en"));
        String expectedResult = "Student Ivan Ivanov, testing fail, gain 0 point.\nIncorrect answers:\n"
                + "who?\n"
                + "i\n"
                + "who other?\n"
                + "you\n";
        answers.put("who?", new Answer("i", false));
        answers.put("who other?", new Answer("you", false));
        try {
            Method getResult = studentTestingService.getClass().getDeclaredMethod("getResult", String.class, StudentTest.class);
            getResult.setAccessible(true);
            String result = (String)getResult.invoke(studentTestingService, FAIL_KEY, studentTest);
            assertEquals(expectedResult, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DisplayName("check en locale")
    @Test()
    public void checkLocaleEnStudentTest() {
        messageService.setLocale(new Locale("en"));
        answers.put("who?", new Answer("i", false));
        answers.put("who other?", new Answer("you", false));
        try {
            Method getResult = studentTestingService.getClass().getDeclaredMethod("getResult", String.class, StudentTest.class);
            getResult.setAccessible(true);
            String result = (String)getResult.invoke(studentTestingService, FAIL_KEY, studentTest);
            assertTrue(result.startsWith("Student"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("check ru locale")
    @Test()
    public void checkLocaleRuStudentTest() {
        messageService.setLocale(new Locale("ru_RU"));
        answers.put("who?", new Answer("i", false));
        answers.put("who other?", new Answer("you", false));
        try {
            Method getResult = studentTestingService.getClass().getDeclaredMethod("getResult", String.class, StudentTest.class);
            getResult.setAccessible(true);
            String result = (String)getResult.invoke(studentTestingService, FAIL_KEY, studentTest);
            assertTrue(result.startsWith("Студент"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
