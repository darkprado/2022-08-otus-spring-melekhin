package ru.otus.example.testingstudentspringboot.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.example.testingstudentspringboot.entity.Question;
import ru.otus.example.testingstudentspringboot.service.QuestionsService;
import ru.otus.example.testingstudentspringboot.utils.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * @author s.melekhin
 * @since 23 сент. 2022 г.
 */
@DisplayName("QuestionsServiceImpl class test")
@SpringBootTest
public class QuestionsServiceImplTest {

    @MockBean
    private Reader reader;

    @Autowired
    private QuestionsService questionsService;

    private List<Question> list;

    @BeforeEach
    public void initList() {
        list = new ArrayList<>();
        list.add(new Question("", Collections.emptyList()));
    }

    @DisplayName("Check number questions in list")
    @Test
    public void checkNumberQuestionsInList() {
        given(reader.readFile())
                .willReturn(list);
        assertEquals(1, questionsService.getQuestions().size());
    }

}
