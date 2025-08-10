package org.pizzashop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.pizzashop.enums.OrderStatus;

import java.time.Instant;
import java.util.Map;

@Data
public class OrderResponseDTO {

    @Min(value = 1)
    private Long id;

    @Min(value = 1)
    private Long customerId;

    @Min(value = 1)
    private Double total;

    @NotNull
    private Instant orderDate;

    @NotNull
    private Instant updatedAt;

    private OrderStatus status = OrderStatus.PENDING;

    @NotNull
    private Map<String, Integer> products;
}
