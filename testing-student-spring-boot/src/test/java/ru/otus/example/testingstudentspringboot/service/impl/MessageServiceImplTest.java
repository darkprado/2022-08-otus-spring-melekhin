package ru.otus.example.testingstudentspringboot.service.impl;

import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Autowired
    private MessageServiceImpl messageService;

    @DisplayName("Check success string en locale")
    @Test
    public void CheckSuccessStringEnLocale() {
        messageService.setLocale(new Locale("en"));
        String expectedResult = "Student {0}, testing success, gain {1} point.{2}";
        assertEquals(expectedResult, messageService.getMessage(SUCCESS_KEY));
    }

    @DisplayName("Check success string ru locale")
    @Test
    public void CheckSuccessStringRuLocale() {
        messageService.setLocale(new Locale("ru_RU"));
        String expectedResult = "Студент {0}, прошел тестирование, набрал {1} баллов.{2}";
        assertEquals(expectedResult, messageService.getMessage(SUCCESS_KEY));
    }

    @DisplayName("Check fail string en locale")
    @Test
    public void CheckFailStringEnLocale() {
        messageService.setLocale(new Locale("en"));
        String expectedResult = "Student {0}, testing fail, gain {1} point.{2}";
        assertEquals(expectedResult, messageService.getMessage(FAIL_KEY));
    }

    @DisplayName("Check fail string ru locale")
    @Test
    public void CheckFailStringRuLocale() {
        messageService.setLocale(new Locale("ru_RU"));
        String expectedResult = "Студент {0}, не прошел тестирование, набрал {1} баллов.{2}";
        assertEquals(expectedResult, messageService.getMessage(FAIL_KEY));
    }

}
