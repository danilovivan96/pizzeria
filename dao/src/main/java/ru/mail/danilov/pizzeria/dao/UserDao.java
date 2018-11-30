package ru.mail.danilov.pizzeria.dao;

import ru.mail.danilov.pizzeria.dao.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    User getByLogin(String var1);

    List<User> getForPage(Long page);

    Long countAll();
}
