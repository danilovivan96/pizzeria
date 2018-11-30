package ru.mail.danilov.pizzeria.service;


import org.springframework.web.multipart.MultipartFile;
import ru.mail.danilov.pizzeria.dao.model.Item;
import ru.mail.danilov.pizzeria.service.dto.ItemDto;

import java.util.List;

public interface ItemService {

    Long countPages(Long quantity);

    List<ItemDto> getForPage(Long page);

    List<ItemDto> browseItemsToDatabase(MultipartFile file);

    ItemDto findById(Long id);

    Long delete(Long id);

    ItemDto update(ItemDto item);

    ItemDto create(ItemDto item);

    Item checkCoincidence(String number, String name);

    void softDelete(Long id);

}
