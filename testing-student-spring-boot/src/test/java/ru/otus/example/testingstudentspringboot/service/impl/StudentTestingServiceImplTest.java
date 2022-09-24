package ru.otus.example.testingstudentspringboot.service.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.otus.example.testingstudentspringboot.config.AppProps;
import ru.otus.example.testingstudentspringboot.entity.Answer;
import ru.otus.example.testingstudentspringboot.entity.Student;
import ru.otus.example.testingstudentspringboot.entity.StudentTest;
import ru.otus.example.testingstudentspringboot.service.QuestionsService;
import ru.otus.example.testingstudentspringboot.utils.impl.IOServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author s.melekhin
 * @since 23 сент. 2022 г.
 */
@DisplayName("StudentTestingServiceImpl class test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StudentTestingServiceImplTest {

    private static final String SUCCESS_KEY = "success";
    private static final String FAIL_KEY = "fail";
    @MockBean
    private QuestionsService questionsService;
    @MockBean
    private IOServiceImpl ioService;
    @Autowired
    private AppProps props;
    @Autowired
    private MessageSource messageSource;
    private StudentTest studentTest;
    private Map<String, Answer> answers;
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
        props.setLocale(new Locale("en"));
        studentTestingService = new StudentTestingServiceImpl(questionsService, ioService, props, messageSource);
        String expectedResult = "Student Ivan Ivanov, testing success, gain 1 point.";
        answers.put("Кто?", new Answer("я", true));
        assertEquals(expectedResult, studentTestingService.getResult(SUCCESS_KEY, studentTest));
    }

    @DisplayName("check fail student test")
    @Test
    public void checkFailStudentTest() {
        props.setLocale(new Locale("en"));
        studentTestingService = new StudentTestingServiceImpl(questionsService, ioService, props, messageSource);
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
        props.setLocale(new Locale("en"));
        studentTestingService = new StudentTestingServiceImpl(questionsService, ioService, props, messageSource);
        answers.put("who?", new Answer("i", false));
        answers.put("who other?", new Answer("you", false));
        assertTrue(studentTestingService.getResult(FAIL_KEY, studentTest).startsWith("Student"));
    }

    @DisplayName("check ru locale")
    @Test()
    public void checkLocaleRuStudentTest() {
        props.setLocale(new Locale("ru_RU"));
        studentTestingService = new StudentTestingServiceImpl(questionsService, ioService, props, messageSource);
        answers.put("who?", new Answer("i", false));
        answers.put("who other?", new Answer("you", false));
        assertTrue(studentTestingService.getResult(FAIL_KEY, studentTest).startsWith("Студент"));
    }

}
