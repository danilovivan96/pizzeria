package ru.mail.danilov.pizzeria.dao.impl;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.mail.danilov.pizzeria.dao.NewsDao;
import ru.mail.danilov.pizzeria.dao.model.News;

import java.util.List;

@Repository
public class NewsDaoImpl extends GenericDaoImpl<News> implements NewsDao {
    public NewsDaoImpl() {
        super(News.class);
    }

    @Override
    public List<News> getForPage(Long page) {
        String selectPage = "from News as n order by n.created desc";
        Query query = getCurrentSession().createQuery(selectPage);
        query.setFirstResult((int) ((page - 1) * 5));
        query.setMaxResults((int) (page * 5));
        return query.list();
    }

    @Override
    public Long countAll() {
        String countItem = "select count(*) from News";
        Query query = getCurrentSession().createQuery(countItem);
        return (Long) query.uniqueResult();
    }
}
