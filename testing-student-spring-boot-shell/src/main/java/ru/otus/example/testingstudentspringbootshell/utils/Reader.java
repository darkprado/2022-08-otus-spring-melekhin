package ru.otus.example.testingstudentspringbootshell.utils;

import java.util.List;

import ru.otus.example.testingstudentspringbootshell.entity.Question;

/**
 * @author s.melekhin
 * @since 08 сент. 2022 г.
 */
public interface Reader {

    List<Question> readFile();

}
