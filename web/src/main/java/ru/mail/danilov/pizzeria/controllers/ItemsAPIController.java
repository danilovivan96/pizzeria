package ru.mail.danilov.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mail.danilov.pizzeria.service.ItemService;
import ru.mail.danilov.pizzeria.service.dto.ItemDto;

import javax.persistence.PersistenceUnit;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemsAPIController {

    private final ItemService itemService;

    @Autowired
    public ItemsAPIController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemDto> getItems() {
        List<ItemDto> items = itemService.getForPage(1L);
        return items;
    }

    @DeleteMapping(value = "/soft/{id}")
    public String softDelete(@PathVariable(name = "id") Long id) {
        itemService.softDelete(id);
        return String.valueOf(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        Long result = itemService.delete(id);
        return String.valueOf(result);
    }

    @PostMapping("/create")
    public ItemDto save(@RequestBody ItemDto item) {
        itemService.create(item);
        return item;
    }

    @PostMapping("/{id}/update")
    ItemDto update(@RequestBody ItemDto item) {
        itemService.update(item);
        return item;
    }
}

