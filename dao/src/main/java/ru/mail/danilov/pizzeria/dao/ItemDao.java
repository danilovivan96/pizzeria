package ru.mail.danilov.pizzeria.dao;

import ru.mail.danilov.pizzeria.dao.model.Item;

import java.util.List;

public interface ItemDao extends GenericDao<Item> {

    Item findByUniqueNumber(String uniqueNum);

    Item findByName(String name);

    Long countAll();

    List<Item> getForPage(Long page);

}
