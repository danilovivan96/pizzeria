package ru.mail.danilov.pizzeria.service.converter.impl.dao;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.dao.model.News;
import ru.mail.danilov.pizzeria.service.converter.Converter;
import ru.mail.danilov.pizzeria.service.dto.NewsDto;

import java.util.List;

@Component("newsConverter")
public class NewsConverter implements Converter<NewsDto, News> {

    @Override
    public News toEntity(NewsDto dto) {
        News news = new News();
        news.setTitle(dto.getTittle());
        news.setContent(dto.getContent());
        news.setCreated(dto.getCreated());
        return news;
    }

}
