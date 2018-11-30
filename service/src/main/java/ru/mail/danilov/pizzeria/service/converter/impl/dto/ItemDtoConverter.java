package ru.mail.danilov.pizzeria.service.converter.impl.dto;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.dao.model.Item;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.dto.ItemDto;

import java.util.ArrayList;
import java.util.List;

@Component("itemDtoConverter")
public class ItemDtoConverter implements ConverterDto<ItemDto, Item> {
    @Override
    public ItemDto toDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setUniqueNum(item.getUniqueNumber());
        itemDto.setDescription(item.getDescription());
        itemDto.setPrice(item.getPrice());
        return itemDto;
    }

    @Override
    public List<ItemDto> toDtoList(List<Item> list) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (Item item : list) {
            ItemDto itemDto = new ItemDto();
            itemDto.setId(item.getId());
            itemDto.setName(item.getName());
            itemDto.setUniqueNum(item.getUniqueNumber());
            itemDto.setDescription(item.getDescription());
            itemDto.setPrice(item.getPrice());
            itemDtoList.add(itemDto);
        }
        return itemDtoList;
    }
}
