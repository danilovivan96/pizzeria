package ru.mail.danilov.pizzeria.service.converter.impl.dto;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.dao.model.News;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.dto.NewsDto;

import java.util.ArrayList;
import java.util.List;

@Component("newsDtoConverter")
public class NewsDtoConverter implements ConverterDto<NewsDto, News> {
    @Override
    public NewsDto toDto(News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setTittle(news.getTitle());
        newsDto.setContent(news.getContent());
        newsDto.setCreated(news.getCreated());
        newsDto.setAuthor(news.getUser().getName() + " " + news.getUser().getSurname());
        return newsDto;
    }

    @Override
    public List<NewsDto> toDtoList(List<News> list) {
        List<NewsDto> dtoList = new ArrayList<>();
        for (News news : list) {
            NewsDto newsDto = new NewsDto();
            newsDto.setId(news.getId());
            newsDto.setTittle(news.getTitle());
            newsDto.setContent(news.getContent());
            newsDto.setCreated(news.getCreated());
            newsDto.setAuthor(news.getUser().getName() + " " + news.getUser().getSurname());
            dtoList.add(newsDto);
        }
        return dtoList;
    }
}
