package ru.otus.example.springdata.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.example.springdata.domain.Author;

/**
 * @author s.melekhin
 * @since 10 окт. 2022 г.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
