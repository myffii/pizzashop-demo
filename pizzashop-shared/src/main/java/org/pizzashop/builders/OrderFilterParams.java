package org.pizzashop.builders;

import lombok.Builder;
import lombok.Data;
import org.pizzashop.enums.OrderStatus;

import java.time.LocalDate;

@Builder
@Data
public class OrderFilterParams {
    private Long customerId;
    private OrderStatus status;
    private LocalDate orderDate;
}
