package ru.mail.danilov.pizzeria.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mail.danilov.pizzeria.dao.GenericDao;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {
    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public GenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T findOne(long id) {
        return (T) this.getCurrentSession().get(this.clazz, id);
    }

    @Override
    public List<T> findAll() {
        return this.getCurrentSession().createQuery("from " + this.clazz.getSimpleName()).list();
    }

    @Override
    public void create(Serializable entity) {
        this.getCurrentSession().persist(entity);
    }

    @Override
    public void update(T entity) {
        this.getCurrentSession().merge(entity);
    }

    @Override
    public void delete(T entity) {
        this.getCurrentSession().delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        T entity = this.findOne(entityId);
        this.delete(entity);
    }

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
