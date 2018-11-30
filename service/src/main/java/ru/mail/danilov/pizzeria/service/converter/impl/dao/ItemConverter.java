package ru.mail.danilov.pizzeria.service.converter.impl.dao;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.dao.model.Item;
import ru.mail.danilov.pizzeria.service.converter.Converter;
import ru.mail.danilov.pizzeria.service.dto.ItemDto;

import java.util.List;

@Component("itemConverter")
public class ItemConverter implements Converter<ItemDto, Item> {
    @Override
    public Item toEntity(ItemDto dto) {
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setUniqueNumber(dto.getUniqueNum());
        item.setPrice(dto.getPrice());
        item.setDelete(false);
        return item;
    }
}
