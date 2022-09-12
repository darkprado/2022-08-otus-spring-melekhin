package ru.otus.example.testingstudentnewsetting.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.otus.example.entity.Student;
import ru.otus.example.service.QuestionsService;
import ru.otus.example.service.impl.StudentTestingServiceImpl;
import ru.otus.example.utils.IOService;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author s.melekhin
 * @since 12 сент. 2022 г.
 */
@DisplayName("StudentTestingServiceImpl class test")
@ExtendWith(MockitoExtension.class)
public class StudentTestingServiceImplTest {

    @Mock
    private IOService ioService;

    @Mock
    QuestionsService questionsService;

    private Student student;

    private StudentTestingServiceImpl studentTestingService;


    @BeforeEach
    public void init() {
        studentTestingService = new StudentTestingServiceImpl(questionsService, ioService, 1);
        student = new Student();
        student.setFirstname("Ivan");
        student.setLastname("Ivanov");
    }

    @DisplayName("check success student test")
    @Test
    public void checkSuccessStudentTest() {
        student.incrementPoint();
        String expectedResult = "Congratulation Ivan Ivanov, your testing success";
        assertEquals(expectedResult, studentTestingService.getResultTesting(student));
    }

    @DisplayName("check fail student test")
    @Test
    public void checkFailStudentTest() {
        String expectedResult = "Ivan Ivanov, try again later";
        assertEquals(expectedResult, studentTestingService.getResultTesting(student));
    }

}
