package ru.mail.danilov.pizzeria.dao.impl;

import org.springframework.stereotype.Repository;
import ru.mail.danilov.pizzeria.dao.CommentDao;
import ru.mail.danilov.pizzeria.dao.model.Comment;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {
    public CommentDaoImpl() {
        super(Comment.class);
    }
}