package ru.otus.example.testingstudentnewsetting.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.otus.example.entity.Question;
import ru.otus.example.service.QuestionsService;
import ru.otus.example.service.impl.QuestionsServiceImpl;
import ru.otus.example.utils.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * @author s.melekhin
 * @since 12 сент. 2022 г.
 */
@DisplayName("QuestionsServiceImpl class test")
@ExtendWith(MockitoExtension.class)
public class QuestionsServiceImplTest {

    @Mock
    private Reader reader;

    private QuestionsService questionsService;

    private List<Question> list;

    @BeforeEach
    public void initList() {
        questionsService = new QuestionsServiceImpl(reader);
        list = new ArrayList<>();
        list.add(new Question("", Collections.emptyList(), ""));
    }

    @DisplayName("Check number questions in list")
    @Test
    public void checkNumberQuestionsInList() {
        given(reader.readFile())
                .willReturn(list);
        assertEquals(1, questionsService.getQuestions().size());
    }

}
