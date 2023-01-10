package ru.otus.springwebspa.springwebspa.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import ru.otus.springwebspa.springwebspa.domain.Comment;
import ru.otus.springwebspa.springwebspa.dto.CommentDto;

/**
 * @author s.melekhin
 * @since 05 дек. 2022 г.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto toDto(Comment comment);

    Comment toDomain(CommentDto commentDto);

    List<CommentDto> toDto(List<Comment> comments);

}
