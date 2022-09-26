package ru.otus.example.testingstudentspringboot.service;

/**
 * @author s.melekhin
 * @since 26 сент. 2022 г.
 */
public interface MessageService {

    String getMessage(String key);

    String getMessage(String key, String[] args);

}
