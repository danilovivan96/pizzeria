package ru.mail.danilov.pizzeria.service.converter.impl.dao;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.service.converter.Converter;
import ru.mail.danilov.pizzeria.dao.model.Item;
import ru.mail.danilov.pizzeria.service.xml.ItemXMLDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class ItemFromXmlConverter implements Converter<ItemXMLDto, Item> {
    @Override
    public Item toEntity(ItemXMLDto dto) {
        Item item = new Item();
        item.setUniqueNumber(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(BigDecimal.valueOf(dto.getPrice()));
        return item;
    }

    @Override
    public List<Item> toEntityList(List<ItemXMLDto> list) {
        List<Item> items = new ArrayList<>();
        for (ItemXMLDto dto : list) {
            Item item = new Item();
            item.setUniqueNumber(dto.getId());
            item.setName(dto.getName());
            item.setDescription(dto.getDescription());
            item.setPrice(BigDecimal.valueOf(dto.getPrice()));
            items.add(item);
        }
        return items;
    }
}
