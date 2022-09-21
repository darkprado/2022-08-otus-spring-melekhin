package ru.otus.example.testingstudentnewsetting.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.otus.example.entity.Answer;
import ru.otus.example.entity.Student;
import ru.otus.example.entity.StudentTest;
import ru.otus.example.service.QuestionsService;
import ru.otus.example.service.impl.StudentTestingServiceImpl;
import ru.otus.example.utils.impl.IOServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author s.melekhin
 * @since 12 сент. 2022 г.
 */
@DisplayName("StudentTestingServiceImpl class test")
@ExtendWith(MockitoExtension.class)
public class StudentTestingServiceImplTest {

    private static final String SUCCESS_RESULT = "Student %s, testing success, gain %s point.%s";
    private static final String FAIL_RESULT = "Student %s, testing fail, gain %s point.%s";

    @Mock
    private QuestionsService questionsService;

    @Mock
    private IOServiceImpl ioService;
    private Student student;
    private StudentTest studentTest;
    private Map<String, Answer> answers;
    private StudentTestingServiceImpl studentTestingService;


    @BeforeEach
    public void init() {
        studentTestingService = new StudentTestingServiceImpl(questionsService, ioService, 1);
        student = new Student();
        student.setFirstname("Ivan");
        student.setLastname("Ivanov");
        answers = new HashMap<>();
        studentTest = new StudentTest(student, answers, 1);
    }

    @DisplayName("check success student test")
    @Test
    public void checkSuccessStudentTest() {
        String expectedResult = "Student Ivan Ivanov, testing success, gain 1 point.";
        answers.put("Кто?", new Answer("я", true));
        assertEquals(expectedResult, studentTestingService.getResult(SUCCESS_RESULT, studentTest));
    }

    @DisplayName("check fail student test")
    @Test
    public void checkFailStudentTest() {
        String expectedResult = "Student Ivan Ivanov, testing fail, gain 0 point. Incorrect answers:\n"
                + "who?\n"
                + "i\n"
                + "who other?\n"
                + "you\n";
        answers.put("who?", new Answer("i", false));
        answers.put("who other?", new Answer("you", false));
        assertEquals(expectedResult, studentTestingService.getResult(FAIL_RESULT, studentTest));
    }

}
