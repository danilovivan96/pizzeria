package ru.mail.danilov.pizzeria.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.mail.danilov.pizzeria.dao.ItemDao;
import ru.mail.danilov.pizzeria.dao.OrderDao;
import ru.mail.danilov.pizzeria.dao.UserDao;
import ru.mail.danilov.pizzeria.dao.model.Item;
import ru.mail.danilov.pizzeria.dao.model.Order;
import ru.mail.danilov.pizzeria.dao.model.OrderPosition;
import ru.mail.danilov.pizzeria.dao.model.User;
import ru.mail.danilov.pizzeria.dao.utils.OrderStatusEnum;
import ru.mail.danilov.pizzeria.dao.utils.RoleEnum;
import ru.mail.danilov.pizzeria.service.OrderService;
import ru.mail.danilov.pizzeria.service.converter.Converter;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.dto.OrderDto;
import ru.mail.danilov.pizzeria.service.dto.OrderListDto;
import ru.mail.danilov.pizzeria.service.dto.OrderPositionDto;
import ru.mail.danilov.pizzeria.service.dto.OrderWithDetails;
import ru.mail.danilov.pizzeria.service.utils.AuthenticatedUserUtil;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    public static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private final OrderDao orderDao;
    private final ItemDao itemDao;
    private final UserDao userDao;
    private final Converter<OrderDto, Order> orderConverter;
    private final ConverterDto<OrderDto, Order> orderDtoConverter;
    private final ConverterDto<OrderListDto, Order> orderListDtoConverter;
    private final ConverterDto<OrderWithDetails, Order> orderWithDetailsConverter;
    private final AuthenticatedUserUtil userUtil;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao,
                            ItemDao itemDao,
                            UserDao userDao,
                            @Qualifier("orderConverter") Converter<OrderDto, Order> orderConverter,
                            @Qualifier("orderDtoConverter") ConverterDto<OrderDto, Order> orderDtoConverter,
                            @Qualifier("orderListDtoConverter") ConverterDto<OrderListDto, Order> orderListDtoConverter,
                            @Qualifier("orderWithDetailsConverter") ConverterDto<OrderWithDetails, Order> orderWithDetailsConverter,
                            AuthenticatedUserUtil userUtil) {
        this.orderDao = orderDao;
        this.itemDao = itemDao;
        this.userDao = userDao;
        this.orderConverter = orderConverter;
        this.orderDtoConverter = orderDtoConverter;
        this.orderListDtoConverter = orderListDtoConverter;
        this.orderWithDetailsConverter = orderWithDetailsConverter;
        this.userUtil = userUtil;
    }


    public List<OrderListDto> getForPage(Long page) {
        logger.info("Start getting user orders");
        List<Order> orders;
        if (userUtil.getUserPrincipal().getRole().equalsIgnoreCase(RoleEnum.CUSTOMER.name())) {
            orders = orderDao.getForCustomerPage(userUtil.getUserPrincipal().getId(), page);
        } else {
            orders = orderDao.getForPage(page);
        }
        List<OrderListDto> list = orderListDtoConverter.toDtoList(orders);
        logger.info("orders gotten");
        return list;
    }

    @Override
    public void delete(String id) {
        Order order = orderDao.findOne(Long.parseLong(id));
        orderDao.delete(order);
    }

    @Override
    public String save(Long id) {
        User user = userDao.findOne(userUtil.getUserPrincipal().getId());
        if (user.getProfile() == null) {
            return "redirect:/profiles/add";
        } else {
            Order order = orderDao.findOne(id);
            order.setStatus(OrderStatusEnum.NEW.name());
            return "redirect:/orders";
        }
    }

    @Override
    public Long countPages(Long id) {
        Long count;
        if (userUtil.getUserPrincipal().getRole().equalsIgnoreCase("CUSTOMER")) {
            count = orderDao.countAll();
        } else {
            count = orderDao.countForCustomer(userUtil.getUserPrincipal().getId());
        }
        if (count % 5 != 0) {
            count = (count / 5) + 1;
        } else {
            count = count / 5;
        }
        return count;
    }

    @Override
    public OrderWithDetails showDetails(Long id) {
        OrderWithDetails orderDto;
        Order order = orderDao.findOne(id);
        orderDto = orderWithDetailsConverter.toDto(order);
        logger.info("Order with detail gotten");
        return orderDto;
    }

    @Override
    public void changeStatus(Long id, OrderStatusEnum status) {
        orderDao.changeStatus(status, id);
        logger.info("Order status changed");
    }

    @Override
    public Integer addToBucket(OrderPositionDto positionDto) {
        User user = userDao.findOne(userUtil.getUserPrincipal().getId());
        Order bucket = orderDao.getBucket(user.getId());
        if (bucket == null) {
            bucket = new Order();
            bucket.setStatus(OrderStatusEnum.BUCKET.name());
            bucket.setUser(user);
            bucket.setCreated(LocalDateTime.now());
            bucket.setCost(new BigDecimal(0));
            user.getOrders().add(bucket);
            orderDao.create(bucket);
        }
        Item item = itemDao.findOne(positionDto.getItemId());
        BigDecimal cost = bucket.getCost();
        cost = cost.add((item.getPrice().multiply(BigDecimal.valueOf(positionDto.getQuantity()))));
        bucket.setCost(cost);
        OrderPosition orderPosition = new OrderPosition(bucket, item);
        orderPosition.setQuantity(positionDto.getQuantity());
        bucket.getItems().add(orderPosition);
        item.getOrders().add(orderPosition);
        orderPosition.getOrder().getItems().add(orderPosition);
        orderPosition.getItem().getOrders().add(orderPosition);
        return positionDto.getQuantity();
    }

    @Override
    public Integer getBucketSize() {
        Order bucket = orderDao.getBucket(userUtil.getUserPrincipal().getId());
        Integer size = 0;
        if (bucket == null) {
            return size;
        } else {
            for (OrderPosition orderPosition : bucket.getItems()) {
                size += orderPosition.getQuantity();
            }
            return size;
        }
    }

    @Override
    public OrderWithDetails getBucket() {
        Order bucket = orderDao.getBucket(userUtil.getUserPrincipal().getId());
        return orderWithDetailsConverter.toDto(bucket);
    }

    @Override
    public void removePosition(OrderPositionDto position) {
        Order bucket = orderDao.getBucket(userUtil.getUserPrincipal().getId());
        for (OrderPosition orderPosition : bucket.getItems()) {
            if (orderPosition.getItem().getId().equals(position.getItemId())) {
                bucket.getItems().remove(orderPosition);
                break;
            }
        }
    }

    @Override
    public List<String> getStatusNames() {
        List<String> statuses = new ArrayList<>();
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            statuses.add(statusEnum.name());
        }
        return statuses;
    }
}
