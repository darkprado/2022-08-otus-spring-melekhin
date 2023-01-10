package ru.otus.springwebspa.springwebspa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.springwebspa.springwebspa.domain.Genre;

/**
 * @author s.melekhin
 * @since 10 окт. 2022 г.
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
