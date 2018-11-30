package ru.mail.danilov.pizzeria.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.mail.danilov.pizzeria.dao.OrderDao;
import ru.mail.danilov.pizzeria.dao.model.Order;
import ru.mail.danilov.pizzeria.dao.utils.OrderStatusEnum;

import java.util.List;


@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    public OrderDaoImpl() {
        super(Order.class);
    }

    private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);

    @Override
    public Long countForCustomer(Long user) {
        String countForCustomer = "select count(*) from Order as o where o.user.id =:user";
        Query query = getCurrentSession().createQuery(countForCustomer);
        query.setParameter("user", user);
        return (Long) query.uniqueResult();
    }

    @Override
    public Long countAll() {
        String countAll = "select count(*) from Order";
        Query query = getCurrentSession().createQuery(countAll);
        return (Long) query.uniqueResult();
    }

    @Override
    public Order getBucket(Long user) {
        String getBucket = "from Order as o where o.user.id=:user and o.status = 'BUCKET'";
        Query query = getCurrentSession().createQuery(getBucket);
        query.setParameter("user", user);
        try {
            return (Order) query.uniqueResult();
        } catch (Exception e) {
            logger.info("bucket not exist");
            return null;
        }
    }

    @Override
    public List<Order> getForCustomerPage(Long id, Long page) {
        String getOrders = "from Order as o where o.user.id =:user and o.status is not 'DELIVERED' order by o.created desc";
        Query query = getCurrentSession().createQuery(getOrders);
        query.setParameter("user", id);
        query.setFirstResult((int) ((page - 1) * 5));
        query.setMaxResults((int) (page * 5));
        return query.list();
    }

    @Override
    public List<Order> getForPage(Long page) {
        String getOrders = "from Order as o where o.status NOT IN ('BUCKET', 'DELIVERED') order by  o.created desc";
        Query query = getCurrentSession().createQuery(getOrders);
        query.setFirstResult((int) ((page - 1) * 5));
        query.setMaxResults((int) (page * 5));
        return query.list();
    }

    @Override
    public Integer changeStatus(OrderStatusEnum status, Long id) {
        String changeStatus = "update Order as o set o.status=:status where o.id=:id";
        Query query = getCurrentSession().createQuery(changeStatus);
        query.setParameter("status", status.name());
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
