package ru.mail.danilov.pizzeria.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.mail.danilov.pizzeria.dao.CommentDao;
import ru.mail.danilov.pizzeria.dao.NewsDao;
import ru.mail.danilov.pizzeria.dao.UserDao;
import ru.mail.danilov.pizzeria.dao.model.Comment;
import ru.mail.danilov.pizzeria.dao.model.News;
import ru.mail.danilov.pizzeria.dao.model.User;
import ru.mail.danilov.pizzeria.service.CommentService;
import ru.mail.danilov.pizzeria.service.converter.Converter;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.dto.CommentDto;
import ru.mail.danilov.pizzeria.service.utils.AuthenticatedUserUtil;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    private final CommentDao commentDao;
    private final NewsDao newsDao;
    protected final UserDao userDao;
    private final Converter<CommentDto, Comment> commentConverter;
    private final ConverterDto<CommentDto, Comment> commentDtoConverter;
    private final AuthenticatedUserUtil userUtil;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao,
                              NewsDao newsDao,
                              @Qualifier("commentConverter") Converter<CommentDto, Comment> commentConverter,
                              @Qualifier("commentDtoConverter") ConverterDto<CommentDto, Comment> commentDtoConverter,
                              AuthenticatedUserUtil userUtil, UserDao userDao) {
        this.commentDao = commentDao;
        this.newsDao = newsDao;
        this.commentConverter = commentConverter;
        this.commentDtoConverter = commentDtoConverter;
        this.userUtil = userUtil;
        this.userDao = userDao;
    }

    @Override
    public CommentDto save(CommentDto commentDto, Long newsId) {
        commentDto.setCreated(LocalDateTime.now());
        Comment comment = commentConverter.toEntity(commentDto);
        User user = userDao.findOne(userUtil.getUserPrincipal().getId());
        News news = newsDao.findOne(newsId);
        comment.setUser(user);
        comment.setNews(news);
        commentDao.create(comment);
        news.getComments().add(comment);
        return commentDto;
    }

    @Override
    public void delete(Long id) {
        Comment comment = commentDao.findOne(id);
        logger.info("Comment '" + comment.getContent() + "' is deleted");
    }

}
