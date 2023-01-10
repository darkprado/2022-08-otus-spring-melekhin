package ru.otus.springwebspa.springwebspa.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import ru.otus.springwebspa.springwebspa.domain.Author;
import ru.otus.springwebspa.springwebspa.dto.AuthorDto;

/**
 * @author s.melekhin
 * @since 05 дек. 2022 г.
 */
@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDto(Author author);

    Author toDomain(AuthorDto authorDto);

    List<AuthorDto> toDto(List<Author> authors);

}
