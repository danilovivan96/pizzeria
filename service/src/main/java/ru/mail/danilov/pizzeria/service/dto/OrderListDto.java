package ru.mail.danilov.pizzeria.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderListDto {
    private Long id;
    private BigDecimal cost;
    private LocalDateTime created;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
