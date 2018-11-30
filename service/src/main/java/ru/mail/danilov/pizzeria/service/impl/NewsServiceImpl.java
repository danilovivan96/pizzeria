package ru.mail.danilov.pizzeria.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.mail.danilov.pizzeria.dao.NewsDao;
import ru.mail.danilov.pizzeria.dao.UserDao;
import ru.mail.danilov.pizzeria.dao.model.Comment;
import ru.mail.danilov.pizzeria.dao.model.News;
import ru.mail.danilov.pizzeria.dao.model.User;
import ru.mail.danilov.pizzeria.service.NewsService;
import ru.mail.danilov.pizzeria.service.converter.Converter;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.dto.CommentDto;
import ru.mail.danilov.pizzeria.service.dto.NewsDto;
import ru.mail.danilov.pizzeria.service.utils.AuthenticatedUserUtil;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    private final NewsDao newsDao;
    private final UserDao userDao;
    private final Converter<NewsDto, News> newsConverter;
    private final ConverterDto<CommentDto, Comment> commentDtoConverter;
    private final ConverterDto<NewsDto, News> newsDtoConverter;
    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);
    private final AuthenticatedUserUtil userUtil;

    @Autowired
    public NewsServiceImpl(NewsDao newsDao,
                           UserDao userDao,
                           @Qualifier("newsConverter") Converter<NewsDto, News> newsConverter,
                           @Qualifier("newsDtoConverter") ConverterDto<NewsDto, News> newsDtoConverter,
                           @Qualifier(value = "commentDtoConverter") ConverterDto<CommentDto, Comment> commentDtoConverter,
                           AuthenticatedUserUtil userUtil) {
        this.newsDao = newsDao;
        this.userDao = userDao;
        this.newsConverter = newsConverter;
        this.newsDtoConverter = newsDtoConverter;
        this.userUtil = userUtil;
        this.commentDtoConverter = commentDtoConverter;
    }

    @Override
    public NewsDto create(NewsDto newsDto) {
        newsDto.setCreated(LocalDateTime.now());
        News news = newsConverter.toEntity(newsDto);
        User user = userDao.findOne(userUtil.getUserPrincipal().getId());
        news.setUser(user);
        user.getNews().add(news);
        newsDao.create(news);
        newsDto = newsDtoConverter.toDto(news);
        logger.info("news saved");
        return newsDto;
    }

    @Override
    public void delete(Long newsId) {
        News news = newsDao.findOne(newsId);
        newsDao.delete(news);
        logger.info("A News " + news.getTitle() + " is deleted");
    }


    @Override
    public void update(NewsDto newsDto) {
        News news = newsConverter.toEntity(newsDto);
        newsDao.update(news);
        newsDto = newsDtoConverter.toDto(news);
        logger.info("News '" + newsDto.getTittle() + "' updated successfully");
    }

    @Override
    public List<NewsDto> getForPage(Long page) {
        List<News> news = newsDao.getForPage(page);
        List<NewsDto> list = newsDtoConverter.toDtoList(news);
        logger.info("List news successfully gotten.");
        return list;

    }

    @Override
    public Long countPages(Long quantity) {
        logger.info("start count item controllers");
        Long count = newsDao.countAll();
        if (count % quantity != 0) {
            count = count / quantity + 1;
        } else {
            count = count / quantity;
        }
        return count;
    }

    @Override
    public List<CommentDto> getComments(Long id) {
        News news = newsDao.findOne(id);
        List<CommentDto> comments = commentDtoConverter.toDtoList(news.getComments());
        return comments;
    }
}
