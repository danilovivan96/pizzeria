package ru.mail.danilov.pizzeria.service.converter.impl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.dao.UserDao;
import ru.mail.danilov.pizzeria.dao.model.Order;
import ru.mail.danilov.pizzeria.dao.utils.OrderStatusEnum;
import ru.mail.danilov.pizzeria.service.converter.Converter;
import ru.mail.danilov.pizzeria.service.dto.OrderDto;

import java.util.ArrayList;

@Component("orderConverter")
public class OrderConverter implements Converter<OrderDto, Order> {
    private final UserDao userDao;

    @Autowired
    public OrderConverter(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Order toEntity(OrderDto dto) {
        Order order = new Order();
        order.setUser(userDao.findOne(dto.getUser()));
        order.setCost(order.getCost().add(dto.getCost()));
        order.setCreated(dto.getCreated());
        order.setStatus(OrderStatusEnum.valueOf(dto.getStatus()).name());
        order.setItems(new ArrayList<>());
        return order;
    }
}
