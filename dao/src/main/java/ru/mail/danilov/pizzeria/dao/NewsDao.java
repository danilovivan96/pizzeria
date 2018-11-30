package ru.mail.danilov.pizzeria.dao;

import ru.mail.danilov.pizzeria.dao.model.News;

import java.util.List;

public interface NewsDao extends GenericDao<News> {

    List<News> getForPage(Long page);

    Long countAll();
}
