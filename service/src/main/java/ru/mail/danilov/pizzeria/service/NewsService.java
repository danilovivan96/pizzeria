package ru.mail.danilov.pizzeria.service;

import ru.mail.danilov.pizzeria.service.dto.CommentDto;
import ru.mail.danilov.pizzeria.service.dto.NewsDto;

import java.util.List;

public interface NewsService {

    NewsDto create(NewsDto news);

    void delete(Long news);

    void update(NewsDto news);

    List<NewsDto> getForPage(Long page);

    Long countPages(Long quantity);

    List<CommentDto> getComments(Long id);
}
