package ru.otus.springwebspa.springwebspa.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import ru.otus.springwebspa.springwebspa.domain.Genre;
import ru.otus.springwebspa.springwebspa.dto.GenreDto;

/**
 * @author s.melekhin
 * @since 05 дек. 2022 г.
 */
@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toDto(Genre genre);

    Genre toDomain(GenreDto genreDto);

    List<GenreDto> toDto(List<Genre> genres);

}
