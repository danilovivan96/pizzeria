package ru.mail.danilov.pizzeria.service.converter.impl.dto;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.dto.CommentDto;
import ru.mail.danilov.pizzeria.dao.model.Comment;

import java.util.ArrayList;
import java.util.List;
@Component("commentDtoConverter")
public class CommentDtoConverter implements ConverterDto<CommentDto, Comment> {
    @Override
    public CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setCreated(comment.getCreated());
        commentDto.setUser(comment.getUser().getLogin());
        return commentDto;
    }

    @Override
    public List<CommentDto> toDtoList(List<Comment> comments) {
        List<CommentDto> commentsDto = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setContent(comment.getContent());
            commentDto.setCreated(comment.getCreated());
            commentDto.setUser(comment.getUser().getLogin());
            commentsDto.add(commentDto);
        }
        return commentsDto;
    }
}
