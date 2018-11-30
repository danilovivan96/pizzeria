package ru.mail.danilov.pizzeria.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mail.danilov.pizzeria.controllers.properties.PageProperties;
import ru.mail.danilov.pizzeria.dao.utils.RoleEnum;
import ru.mail.danilov.pizzeria.service.ItemService;
import ru.mail.danilov.pizzeria.service.OrderService;
import ru.mail.danilov.pizzeria.service.dto.ItemDto;
import ru.mail.danilov.pizzeria.service.utils.AuthenticatedUserUtil;

import java.util.List;

@Controller
@RequestMapping(value = "/items")
public class ItemsController {
    private final PageProperties properties;
    private final ItemService itemService;
    private final Validator itemValidator;
    private final OrderService orderService;
    private final AuthenticatedUserUtil userUtil;

    @Autowired
    public ItemsController(PageProperties properties,
                           ItemService itemService,
                           @Qualifier(value = "itemValidator") Validator itemValidator,
                           OrderService orderService,
                           AuthenticatedUserUtil userUtil) {
        this.properties = properties;
        this.itemService = itemService;
        this.itemValidator = itemValidator;
        this.orderService = orderService;
        this.userUtil = userUtil;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('SHOW_ITEMS')")
    public String getForPage(ModelMap modelMap,
                             @RequestParam(value = "page", defaultValue = "1") Long page) {
        List<ItemDto> items = itemService.getForPage(page);
        modelMap.addAttribute("items", items);
        Long quantity = properties.getItemsForPageQuantity();
        Long pages = itemService.countPages(quantity);
        modelMap.addAttribute("pages", pages);
        Integer size = orderService.getBucketSize();
        modelMap.addAttribute("size", size);
        if (userUtil.getUserPrincipal().getRole().equalsIgnoreCase(RoleEnum.SALE_USER.name())) {
            return properties.getItemsAdminPagePath();
        } else {
            return properties.getItemsPagePath();
        }
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('REMOVE_ITEMS')")
    public String delete(ModelMap modelMap,
                         @RequestParam("ids") Long[] ids,
                         @RequestParam(value = "page", defaultValue = "1") Long page) {
        for (Long id : ids) {
            itemService.softDelete(id);
        }
        List<ItemDto> items = itemService.getForPage(page);
        Long pages = itemService.countPages(properties.getItemsForPageQuantity());
        modelMap.addAttribute("items", items);
        modelMap.addAttribute("pages", pages);
        return properties.getItemsAdminPagePath();
    }

    @GetMapping(value = "/add")
    @PreAuthorize("hasAuthority('CREATE_ITEMS')")
    public String createItem(ModelMap modelMap) {
        ItemDto item = new ItemDto();
        modelMap.addAttribute("item", item);
        return properties.getItemsCreatePagePath();
    }


    @GetMapping(value = "/{id}/updating")
    @PreAuthorize("hasAuthority('COPY_ITEMS')")
    public String getItem(ModelMap modelMap,
                          @PathVariable("id") Long id) {
        ItemDto item = itemService.findById(id);
        modelMap.addAttribute("item", item);
        return properties.getItemsUpdatePagePath();
    }

    @PostMapping(value = "/{id}/update")
    @PreAuthorize("hasAuthority('COPY_ITEMS')")
    public String updateItem(@PathVariable("id") Long id,
                             @ModelAttribute ItemDto item,
                             BindingResult result,
                             ModelMap modelMap) {
        item.setId(id);
        itemValidator.validate(item, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("item", item);
            return properties.getItemsUpdatePagePath();
        } else {
            itemService.update(item);
            modelMap.addAttribute("success", "Item updated!");
            modelMap.addAttribute("item", item);
            return properties.getItemsUpdatePagePath();
        }
    }

    @GetMapping(value = "/browse")
    @PreAuthorize("hasAuthority('UPLOAD_ITEMS')")
    public String browsePage() {
        return properties.getItemsBrowsePagePath();
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('UPLOAD_ITEMS')")
    public String upload(@RequestParam("file") MultipartFile file,
                         ModelMap modelMap) {
        if (!file.isEmpty()) {
            List<ItemDto> list = itemService.browseItemsToDatabase(file);
            if (list.isEmpty()) {
                return "redirect:/items";
            } else
                modelMap.addAttribute("message", "This items was not browsed");
            modelMap.addAttribute("items", list);
            return properties.getItemsAdminPagePath();
        }
        modelMap.addAttribute("message", "Empty file");
        return properties.getItemsBrowsePagePath();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('COPY_ITEMS')")
    public String createItem(
            @ModelAttribute ItemDto item,
            BindingResult result,
            ModelMap modelMap) {
        itemValidator.validate(item, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("item", item);
            return properties.getItemsCreatePagePath();
        } else {
            itemService.create(item);
            return "redirect:/items";
        }
    }
}
