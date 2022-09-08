package ru.otus.example.utils;

import java.util.List;

import ru.otus.example.dto.QuestionDto;

/**
 * @author s.melekhin
 * @since 08 сент. 2022 г.
 */
public interface Reader {

    List<QuestionDto> readFile();

}
