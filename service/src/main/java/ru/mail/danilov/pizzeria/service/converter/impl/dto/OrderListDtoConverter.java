package ru.mail.danilov.pizzeria.service.converter.impl.dto;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.dto.OrderListDto;
import ru.mail.danilov.pizzeria.dao.model.Order;

import java.util.ArrayList;
import java.util.List;
@Component("orderListDtoConverter")
public class OrderListDtoConverter implements ConverterDto<OrderListDto, Order> {
    @Override
    public OrderListDto toDto(Order order) {
        OrderListDto dto = new OrderListDto();
        dto.setId(order.getId());
        dto.setCost(order.getCost());
        dto.setCreated(order.getCreated());
        dto.setStatus(order.getStatus());
        return null;
    }

    @Override
    public List<OrderListDto> toDtoList(List<Order> list) {
        List<OrderListDto> listDtos = new ArrayList<>();
        for (Order order : list) {
            OrderListDto dto = new OrderListDto();
            dto.setId(order.getId());
            dto.setCost(order.getCost());
            dto.setCreated(order.getCreated());
            dto.setStatus(order.getStatus());
            listDtos.add(dto);
        }
        return listDtos;
    }
}
