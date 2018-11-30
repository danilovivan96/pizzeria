package ru.mail.danilov.pizzeria.service;

import ru.mail.danilov.pizzeria.dao.utils.OrderStatusEnum;
import ru.mail.danilov.pizzeria.service.dto.OrderListDto;
import ru.mail.danilov.pizzeria.service.dto.OrderPositionDto;
import ru.mail.danilov.pizzeria.service.dto.OrderWithDetails;

import java.util.List;

public interface OrderService {

    List<OrderListDto> getForPage(Long page);

    void delete(String id);

    String save(Long id);

    Long countPages(Long id);

    OrderWithDetails showDetails(Long id);

    void changeStatus(Long id, OrderStatusEnum status);

    Integer addToBucket(OrderPositionDto position);

    Integer getBucketSize();

    OrderWithDetails getBucket();

    void removePosition(OrderPositionDto position);

    List<String> getStatusNames();
}
