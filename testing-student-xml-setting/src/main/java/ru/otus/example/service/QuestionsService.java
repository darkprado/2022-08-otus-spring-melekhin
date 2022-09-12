package ru.otus.example.service;

import java.util.List;

import ru.otus.example.dto.QuestionDto;

/**
 * @author s.melekhin
 * @since 12 май 2022 г.
 */
public interface QuestionsService {

    List<QuestionDto> getQuestions();

}
