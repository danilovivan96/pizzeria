package ru.mail.danilov.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.mail.danilov.pizzeria.controllers.properties.PageProperties;
import ru.mail.danilov.pizzeria.dao.utils.OrderStatusEnum;
import ru.mail.danilov.pizzeria.service.ItemService;
import ru.mail.danilov.pizzeria.service.OrderService;
import ru.mail.danilov.pizzeria.service.dto.OrderListDto;
import ru.mail.danilov.pizzeria.service.dto.OrderPositionDto;
import ru.mail.danilov.pizzeria.service.dto.OrderWithDetails;

import java.util.List;

@Controller
@RequestMapping(value = "/orders")
public class OrdersController {
    private final PageProperties properties;
    private final OrderService orderService;
    private final ItemService itemService;

    @Autowired
    public OrdersController(OrderService orderService,
                            PageProperties properties,
                            ItemService itemService) {
        this.orderService = orderService;
        this.properties = properties;
        this.itemService = itemService;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('SHOW_ORDERS')")
    public String getForPage(ModelMap modelMap,
                             @RequestParam(value = "page", defaultValue = "1") Long page) {
        List<OrderListDto> orders = orderService.getForPage(page);
        modelMap.addAttribute("orders", orders);
        Long quantity = properties.getOrdersForPageQuantity();
        List<String> statuses = orderService.getStatusNames();
        modelMap.addAttribute("statuses", statuses);
        Long pages = orderService.countPages(quantity);
        modelMap.addAttribute("pages", pages);
        return properties.getOrdersPagePath();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SHOW_ORDERS')")
    public String showDetails(@PathVariable("id") Long id,
                              ModelMap modelMap) {
        OrderWithDetails order = orderService.showDetails(id);
        modelMap.addAttribute("order", order);
        return properties.getOrdersDetailsPagePath();
    }

    @PostMapping("/creating")
    @PreAuthorize("hasAuthority('CREATE_ORDERS')")
    public String creating(@RequestParam("id") Long item,
                                @RequestParam(value = "quantity", defaultValue = "1") Integer quantity,
                                @RequestParam(value = "page", defaultValue = "1") Long page) {
        OrderPositionDto position = new OrderPositionDto();
        position.setItemId(item);
        position.setQuantity(quantity);
        orderService.addToBucket(position);
        return "redirect:/items";
    }

    @GetMapping("/bucket")
    @PreAuthorize("hasAuthority('ADD_ITEMS_TO_BUCKET')")
    public String showBucket(ModelMap modelMap) {
        OrderWithDetails bucket = orderService.getBucket();
        modelMap.addAttribute("bucket", bucket);
        return properties.getOrdersBucketPagePath();
    }

    @GetMapping("/{id}/save")
    @PreAuthorize("hasAuthority('CREATE_ORDERS')")
    public String save(@PathVariable("id") Long id) {
        String redirect = orderService.save(id);
        return redirect;
    }

    @PostMapping("/{bucket}/bucket/remove")
    @PreAuthorize("hasAuthority('CREATE_ORDERS')")
    public String removePosition(@PathVariable("bucket") Long bucket,
                                 @RequestParam("id") Long item) {
        OrderPositionDto position = new OrderPositionDto();
        position.setOrderId(bucket);
        position.setItemId(item);
        orderService.removePosition(position);
        return "redirect:/orders/bucket";
    }

    @PostMapping("/status")
    @PreAuthorize("hasAuthority('CHANGE_ORDERS_STATUS')")
    public String changeStatus(@RequestParam("id") Long order,
                               @RequestParam("new_status") String status) {
        orderService.changeStatus(order, OrderStatusEnum.valueOf(status));
        return "redirect:/orders";
    }


}
