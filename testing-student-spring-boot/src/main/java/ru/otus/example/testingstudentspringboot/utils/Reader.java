package ru.otus.example.testingstudentspringboot.utils;

import java.util.List;

import ru.otus.example.testingstudentspringboot.entity.Question;

/**
 * @author s.melekhin
 * @since 08 сент. 2022 г.
 */
public interface Reader {

    List<Question> readFile();

}
