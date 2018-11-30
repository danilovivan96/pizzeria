package ru.mail.danilov.pizzeria.dao;

import ru.mail.danilov.pizzeria.dao.model.Order;
import ru.mail.danilov.pizzeria.dao.utils.OrderStatusEnum;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    Long countForCustomer(Long user);

    List<Order> getForPage(Long page);

    List<Order> getForCustomerPage(Long id, Long page);

    Integer changeStatus(OrderStatusEnum status, Long id);

    Long countAll();

    Order getBucket(Long user);
}