package ru.mail.danilov.pizzeria.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Serializable> {
    T findOne(long var1);

    List<T> findAll();

    void create(T var1);

    void update(T var1);

    void delete(T var1);

    void deleteById(long var1);

    Session getCurrentSession();
}
