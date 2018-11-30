package ru.mail.danilov.pizzeria.service.converter.impl.dto;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.dao.model.Order;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.dto.OrderDto;

@Component("orderDtoConverter")
public class OrderDtoConverter implements ConverterDto<OrderDto, Order> {
    @Override
    public OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUser(order.getUser().getId());
        orderDto.setCost(order.getCost());
        orderDto.setStatus(order.getStatus());
        return orderDto;
    }
}
