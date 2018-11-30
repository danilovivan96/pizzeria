package ru.mail.danilov.pizzeria.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import ru.mail.danilov.pizzeria.dao.ItemDao;
import ru.mail.danilov.pizzeria.dao.model.Item;
import ru.mail.danilov.pizzeria.service.ItemService;
import ru.mail.danilov.pizzeria.service.converter.Converter;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.converter.impl.dao.ItemFromXmlConverter;
import ru.mail.danilov.pizzeria.service.dto.ItemDto;
import ru.mail.danilov.pizzeria.service.exceptions.ItemNotFoundException;
import ru.mail.danilov.pizzeria.service.utils.AuthenticatedUserUtil;
import ru.mail.danilov.pizzeria.service.xml.DomParser;
import ru.mail.danilov.pizzeria.service.xml.ItemXMLDto;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    public static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);
    private final ItemDao itemDao;
    private final Converter<ItemDto, Item> itemConverter;
    private final ConverterDto<ItemDto, Item> itemDtoConverter;
    private final DomParser domParser;
    private final ItemFromXmlConverter itemFromXmlConverter;
    private final AuthenticatedUserUtil user;

    @Autowired
    public ItemServiceImpl(ItemDao itemDao,
                           @Qualifier("itemConverter") Converter<ItemDto, Item> itemConverter,
                           @Qualifier("itemDtoConverter") ConverterDto<ItemDto, Item> itemDtoConverter,
                           DomParser domParser,
                           ItemFromXmlConverter itemFromXmlConverter, AuthenticatedUserUtil user) {
        this.itemDao = itemDao;
        this.itemConverter = itemConverter;
        this.itemDtoConverter = itemDtoConverter;
        this.domParser = domParser;
        this.itemFromXmlConverter = itemFromXmlConverter;
        this.user = user;
    }

    @Override
    public Long countPages(Long quantity) {
        logger.info("start count item controllers");
        Long count = itemDao.countAll();
        if (count % quantity != 0) {
            count = count / quantity + 1;
        } else {
            count = count / quantity;
        }
        return count;
    }

    @Override
    public List<ItemDto> getForPage(Long page) {
        List<Item> items = itemDao.getForPage(page);
        List<ItemDto> itemDtoList = itemDtoConverter.toDtoList(items);
        logger.info("List items successfully gotten.");
        return itemDtoList;
    }

    @Override
    public List<ItemDto> browseItemsToDatabase(MultipartFile multipartFile) {
        File file = new File("browse.xml");
        List<ItemDto> itemDtos = new ArrayList<>();
        try {
            multipartFile.transferTo(file);
            List<ItemXMLDto> xmlDtoList = domParser.parse(file);
            for (ItemXMLDto itemXMLDto : xmlDtoList) {
                Item item = checkCoincidence(itemXMLDto.getId(), itemXMLDto.getName());
                if (item == null) {
                    item = itemFromXmlConverter.toEntity(itemXMLDto);
                    item.setDelete(false);
                    itemDao.create(item);
                    logger.info("item saved");
                } else {
                    logger.info("coincidence with database");
                    ItemDto itemDto = itemDtoConverter.toDto(item);
                    itemDtos.add(itemDto);
                }
            }
        } catch (IOException | SAXException e) {
            logger.error("xml file parsing failed");
            e.printStackTrace();
        }
        return itemDtos;
    }

    @Override
    public ItemDto findById(Long id) {
        Item item = itemDao.findOne(id);
        ItemDto itemDto = itemDtoConverter.toDto(item);
        return itemDto;
    }

    @Override
    public Long delete(Long id) {
        Item item = itemDao.findOne(id);
        if (item.getOrders().isEmpty()) {
            itemDao.delete(item);
            logger.info("item was delete");
            return id;
        } else {
            logger.info("some orders contain item, delete failed");
            return 0L;
        }
    }

    @Override
    public ItemDto update(ItemDto itemDto) {
        Item item = itemConverter.toEntity(itemDto);
        itemDao.update(item);
        return itemDto;
    }

    @Override
    public ItemDto create(ItemDto itemDto) {
        Item item = itemConverter.toEntity(itemDto);
        itemDao.create(item);
        logger.info("New item saved");
        return itemDto;
    }

    @Override
    public Item checkCoincidence(String number, String name) {
        Item item;
        item = itemDao.findByUniqueNumber(number);
        if (item != null) {
            logger.info("coincidence by number");
            return item;
        }
        item = itemDao.findByName(name);
        if (item != null) {
            logger.info("coincidence by name");
            return item;
        } else {
            logger.info("coincidence not found");
            return null;
        }
    }

    @Override
    public void softDelete(Long id) {
        logger.info("start delete item with id:" + id);
        Item item = itemDao.findOne(id);
        if (item == null) {
            logger.info("item with id: " + id + " not found");
            throw new ItemNotFoundException();
        } else item.setDelete(true);
        logger.info("item was delete");
    }
}
