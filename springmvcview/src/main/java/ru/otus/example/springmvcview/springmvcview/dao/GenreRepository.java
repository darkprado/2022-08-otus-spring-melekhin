package ru.otus.example.springmvcview.springmvcview.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.example.springmvcview.springmvcview.domain.Genre;

/**
 * @author s.melekhin
 * @since 12 ноя. 2022 г.
 */
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
