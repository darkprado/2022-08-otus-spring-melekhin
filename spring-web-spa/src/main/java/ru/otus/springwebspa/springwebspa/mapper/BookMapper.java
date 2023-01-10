package ru.otus.springwebspa.springwebspa.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import ru.otus.springwebspa.springwebspa.domain.Book;
import ru.otus.springwebspa.springwebspa.dto.BookDto;

/**
 * @author s.melekhin
 * @since 05 дек. 2022 г.
 */
@Mapper(componentModel = "spring", uses = {
        AuthorMapper.class, GenreMapper.class

})
public interface BookMapper {

    BookDto toDto(Book book);

    Book toDomain(BookDto bookDto);

    List<BookDto> toDto(List<Book> book);

}
