package ru.mail.danilov.pizzeria.service.converter.impl.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.dao.model.Order;
import ru.mail.danilov.pizzeria.dao.model.OrderPosition;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.dto.OrderDetailDto;
import ru.mail.danilov.pizzeria.service.dto.OrderWithDetails;

import java.math.BigDecimal;
import java.util.List;

@Component("orderWithDetailsConverter")
public class OrderWithDetailsConverter implements ConverterDto<OrderWithDetails, Order> {
    private final ConverterDto<OrderDetailDto, OrderPosition> orderDetailDtoConverter;

    @Autowired
    public OrderWithDetailsConverter(@Qualifier("orderDetailDtoConverter") ConverterDto<OrderDetailDto, OrderPosition> orderDetailDtoConverter) {
        this.orderDetailDtoConverter = orderDetailDtoConverter;
    }


    @Override
    public OrderWithDetails toDto(Order order) {
        OrderWithDetails orderDto = new OrderWithDetails();
        orderDto.setId(order.getId());
        orderDto.setCreated(order.getCreated());
        orderDto.setStatus(order.getStatus());
        orderDto.setPositions(orderDetailDtoConverter.toDtoList(order.getItems()));
        BigDecimal cost = new BigDecimal(0);
        for (OrderDetailDto orderDetailDto : orderDto.getPositions()) {
            cost = cost.add(orderDetailDto.getPrice());
        }
        orderDto.setCost(cost);
        order.setCost(cost);
        return orderDto;
    }
}
