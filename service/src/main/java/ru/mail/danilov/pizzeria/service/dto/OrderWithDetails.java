package ru.mail.danilov.pizzeria.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderWithDetails {
    private Long id;
    private LocalDateTime created;
    private BigDecimal cost;
    private String status;
    private List<OrderDetailDto> positions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetailDto> getPositions() {
        return positions;
    }

    public void setPositions(List<OrderDetailDto> positions) {
        this.positions = positions;
    }

}
