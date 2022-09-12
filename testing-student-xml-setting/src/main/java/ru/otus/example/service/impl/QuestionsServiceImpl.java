package ru.otus.example.service.impl;

import java.util.List;

import ru.otus.example.dto.QuestionDto;
import ru.otus.example.service.QuestionsService;
import ru.otus.example.utils.Reader;
import ru.otus.example.utils.impl.CsvReader;

/**
 * @author s.melekhin
 * @since 12 май 2022 г.
 */
public class QuestionsServiceImpl implements QuestionsService {

    private final Reader reader;

    public QuestionsServiceImpl(CsvReader reader) {
        this.reader = reader;
    }

    @Override
    public List<QuestionDto> getQuestions() {
        return reader.readFile();
    }

}
