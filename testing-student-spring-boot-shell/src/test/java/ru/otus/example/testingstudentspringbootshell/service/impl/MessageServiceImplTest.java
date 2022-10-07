package ru.otus.example.testingstudentspringbootshell.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.example.testingstudentspringbootshell.service.QuestionsService;
import ru.otus.example.testingstudentspringbootshell.service.StudentTestingService;
import ru.otus.example.testingstudentspringbootshell.utils.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author s.melekhin
 * @since 03 окт. 2022 г.
 */
@DisplayName("MessageServiceImpl class test")
@SpringBootTest
public class MessageServiceImplTest {

    private static final String SUCCESS_KEY = "success";
    private static final String FAIL_KEY = "fail";

    @MockBean
    private Reader reader;

    @MockBean
    private StudentTestingService studentTestingService;

    @MockBean
    private QuestionsService questionsService;

    @Autowired
    private MessageServiceImpl messageService;

    @DisplayName("Check success string")
    @Test
    public void checkSuccessString() {
        String expectedResult = "Student {0}, testing success, gain {1} point.{2}";
        assertEquals(expectedResult, messageService.getMessage(SUCCESS_KEY));
    }

    @DisplayName("Check fail string")
    @Test
    public void checkFailString() {
        String expectedResult = "Student {0}, testing fail, gain {1} point.{2}";
        assertEquals(expectedResult, messageService.getMessage(FAIL_KEY));
    }

}
