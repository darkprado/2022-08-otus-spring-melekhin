package ru.otus.example.testingstudentspringboot.utils.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import ru.otus.example.testingstudentspringboot.config.AppProps;
import ru.otus.example.testingstudentspringboot.entity.Answer;
import ru.otus.example.testingstudentspringboot.entity.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author s.melekhin
 * @since 21 сент. 2022 г.
 */
@DisplayName("CSV reader class test")
@ExtendWith(MockitoExtension.class)
public class CsvReaderTest {

    @Mock
    private AppProps props;

    @Mock
    private MessageSource messageSource;

    private Question expectedQuestion;

    private CsvReader csvReader;

    @BeforeEach
    public void init() {
        Mockito.when(messageSource.getMessage(anyString(), any(), any())).thenReturn("questions-test.csv");
        csvReader = new CsvReader(props, messageSource);
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("function", false));
        answers.add(new Answer("section", false));
        answers.add(new Answer("body", false));
        answers.add(new Answer("block", true));
        expectedQuestion = new Question("What is the name of the code between the curly braces?", answers);
    }

    @DisplayName("check the number of questions")
    @Test
    public void checkNumberQuestions() {
        assertEquals(2, csvReader.readFile().size());
    }

    @DisplayName("check question")
    @Test
    public void checkQuestion() {
        Mockito.when(messageSource.getMessage(anyString(), any(), any())).thenReturn("questions-test.csv");
        CsvReader reader = new CsvReader(props, messageSource);
        Question question = reader.readFile().get(0);
        assertEquals(expectedQuestion.getQuestion(), question.getQuestion());
        assertEquals(expectedQuestion.getAnswerChoice(), question.getAnswerChoice());
    }

}
