package ru.otus.example.springjdbc.service;

/**
 * @author s.melekhin
 * @since 19 окт. 2022 г.
 */
public interface InfoService {

    void getAllInfo();

    void getInfoById(long id);

    void save();

    void deleteById(long id);

    void update(long id);

}
