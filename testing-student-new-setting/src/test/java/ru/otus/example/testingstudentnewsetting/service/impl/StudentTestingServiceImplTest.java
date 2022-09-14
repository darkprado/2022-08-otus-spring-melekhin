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

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author s.melekhin
 * @since 12 сент. 2022 г.
 */
@DisplayName("StudentTestingServiceImpl class test")
@ExtendWith(MockitoExtension.class)
public class StudentTestingServiceImplTest {

    @Mock
    private QuestionsService questionsService;
    private Student student;
    private List<StudentTest> studentTestList;
    private Map<String, Answer> answers;
    private StudentTestingServiceImpl studentTestingService;


    @BeforeEach
    public void init() {
        studentTestingService = new StudentTestingServiceImpl(questionsService, 1);
        student = new Student();
        student.setFirstname("Ivan");
        student.setLastname("Ivanov");
        studentTestList = new ArrayList<>();
        answers = new HashMap<>();
    }

    @DisplayName("check success student test")
    @Test
    public void checkSuccessStudentTest() {
        String expectedResult = "Student Ivan Ivanov, testing success, gain 1 point.";
        answers.put("Кто?", new Answer("я", true));
        studentTestList.add(new StudentTest(student, answers, 1));
        assertEquals(expectedResult, studentTestingService.getResultTesting(studentTestList).get(0));
    }

    @DisplayName("check fail student test")
    @Test
    public void checkFailStudentTest() {
        String expectedResult = "Student Ivan Ivanov, testing fail, gain 1 point. Incorrect answers:\n"
                + "who other?\n"
                + "you\n";
        answers.put("who?", new Answer("i", true));
        answers.put("who other?", new Answer("you", false));
        studentTestList.add(new StudentTest(student, answers, 2));
        assertEquals(expectedResult, studentTestingService.getResultTesting(studentTestList).get(0));
    }

}
