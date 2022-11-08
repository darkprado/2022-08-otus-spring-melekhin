package ru.otus.example.springmongo.service;

/**
 * @author s.melekhin
 * @since 19 окт. 2022 г.
 */
public interface InfoService {

    void getAllInfo();

    void getInfoById(String id);

    void save();

    void deleteById(String id);

    void update(String id);

}
