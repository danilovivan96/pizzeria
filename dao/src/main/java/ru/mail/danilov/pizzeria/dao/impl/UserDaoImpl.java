package ru.mail.danilov.pizzeria.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.mail.danilov.pizzeria.dao.UserDao;
import ru.mail.danilov.pizzeria.dao.model.User;

import java.util.List;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
        super(User.class);
    }

    public User getByLogin(String login) {
        try {
            String getUser = " from User as U where U.login=:login";
            Query query = this.getCurrentSession().createQuery(getUser);
            query.setParameter("login", login);
            return (User) query.uniqueResult();
        } catch (Exception e) {
            logger.info("user with login: " + login + " not exist");
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<User> getForPage(Long page) {
        String selectPage = "from User as u  where u.isDelete=false order by u.name asc";
        Query query = getCurrentSession().createQuery(selectPage);
        query.setFirstResult((int) ((page - 1) * 5));
        query.setMaxResults((int) (page * 5));
        return query.list();
    }

    @Override
    public Long countAll() {
        String countUsers = "select count(*) from User as u  where u.isDelete=false";
        Query query = getCurrentSession().createQuery(countUsers);
        return (Long) query.uniqueResult();
    }
}
