package ru.mail.danilov.pizzeria.dao.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_orders_items")
public class OrderPosition implements Serializable {
    @EmbeddedId
    private OrderItemId id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("item_id")
    private Item item;
    @Column(name = "quantity")
    private Integer quantity;

    public OrderPosition(Order order, Item item) {
        this.order = order;
        this.item = item;
        this.id = new OrderItemId(order.getId(), item.getId());
    }

    public OrderPosition() {
    }

    public OrderItemId getId() {
        return this.id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
