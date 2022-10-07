package ru.otus.example.testingstudentspringboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.otus.example.testingstudentspringboot.entity.Question;
import ru.otus.example.testingstudentspringboot.service.QuestionsService;
import ru.otus.example.testingstudentspringboot.utils.Reader;

/**
 * @author s.melekhin
 * @since 12 май 2022 г.
 */
@Service
public class QuestionsServiceImpl implements QuestionsService {

    private final Reader reader;

    public QuestionsServiceImpl(Reader reader) {
        this.reader = reader;
    }

    @Override
    public List<Question> getQuestions() {
        return reader.readFile();
    }

}
