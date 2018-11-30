package ru.mail.danilov.pizzeria.service.converter.impl.dao;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.dao.model.Comment;
import ru.mail.danilov.pizzeria.service.converter.Converter;
import ru.mail.danilov.pizzeria.service.dto.CommentDto;

import java.util.ArrayList;
import java.util.List;

@Component("commentConverter")
public class CommentConverter implements Converter<CommentDto, Comment> {
    @Override
    public Comment toEntity(CommentDto dto) {
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setCreated(dto.getCreated());
        return comment;
    }

    @Override
    public List<Comment> toEntityList(List<CommentDto> commentsDto) {
        List<Comment> comments = new ArrayList<>();
        for (CommentDto dto : commentsDto) {
            Comment comment = new Comment();
            comment.setContent(dto.getContent());
            comment.setCreated(dto.getCreated());
            comments.add(comment);
        }
        return comments;
    }
}
