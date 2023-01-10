package ru.otus.springwebspa.springwebspa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.springwebspa.springwebspa.domain.Author;

/**
 * @author s.melekhin
 * @since 10 окт. 2022 г.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
