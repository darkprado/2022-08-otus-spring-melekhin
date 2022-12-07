package ru.otus.example.springmvcview.springmvcview.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.example.springmvcview.springmvcview.domain.Author;

/**
 * @author s.melekhin
 * @since 12 ноя. 2022 г.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
