package ru.otus.example.springorm.service;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
public interface InnerInfoService {

    void getAllInfoByExternalEntity(long bookId);

    void getInfoById(long id);

    void save(long bookId);

    void deleteById(long id);

    void update(long id);

}
