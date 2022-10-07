package ru.otus.example.testingstudentspringbootshell.utils.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.otus.example.testingstudentspringbootshell.config.AppProps;
import ru.otus.example.testingstudentspringbootshell.config.FilenameProps;
import ru.otus.example.testingstudentspringbootshell.entity.Answer;
import ru.otus.example.testingstudentspringbootshell.entity.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author s.melekhin
 * @since 21 сент. 2022 г.
 */
@DisplayName("CSV reader class test")
@ExtendWith(MockitoExtension.class)
public class CsvReaderTest {

    @Mock
    private FilenameProps props;

    private Question expectedQuestion;

    private CsvReader csvReader;

    @BeforeEach
    public void init() {
        Mockito.when(props.getFilename()).thenReturn("questions_en.csv");
        csvReader = new CsvReader(props);
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
        assertEquals(5, csvReader.readFile().size());
    }

    @DisplayName("check question")
    @Test
    public void checkQuestion() {
        Question question = csvReader.readFile().get(0);
        assertEquals(expectedQuestion.getQuestion(), question.getQuestion());
        assertEquals(expectedQuestion.getAnswerChoice(), question.getAnswerChoice());
    }

}
