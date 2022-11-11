package ru.otus.example.springmongo.service;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
public interface InnerInfoService {

    void getAllInfoByExternalEntity(String bookId);

    void getInfoById(String id);

    void save(String bookId);

    void deleteById(String id);

    void update(String id);

}
