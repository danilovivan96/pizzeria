package ru.mail.danilov.pizzeria.service.converter.impl.dto;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.dao.model.OrderPosition;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.dto.OrderDetailDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component("orderDetailDtoConverter")
public class OrderDetailDtoConverter implements ConverterDto<OrderDetailDto, OrderPosition> {
    @Override
    public OrderDetailDto toDto(OrderPosition entity) {
        OrderDetailDto detailDto = new OrderDetailDto();
        detailDto.setId(entity.getItem().getId());
        detailDto.setName(entity.getItem().getName());
        detailDto.setQuantity(entity.getQuantity());
        detailDto.setPrice(entity.getItem().getPrice().multiply(BigDecimal.valueOf(entity.getQuantity())));
        return detailDto;
    }

    @Override
    public List<OrderDetailDto> toDtoList(List<OrderPosition> list) {
        List<OrderDetailDto> listDto = new ArrayList<>();
        for (OrderPosition entity : list) {
            OrderDetailDto detailDto = new OrderDetailDto();
            detailDto.setId(entity.getItem().getId());
            detailDto.setName(entity.getItem().getName());
            detailDto.setQuantity(entity.getQuantity());
            detailDto.setPrice(entity.getItem().getPrice().multiply(BigDecimal.valueOf(entity.getQuantity())));
            listDto.add(detailDto);
        }
        return listDto;
    }
}
