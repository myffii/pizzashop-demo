package org.pizzashop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class OrderDTO {

    @Min(value = 1)
    private Long customerId;

    @NotNull
    @Schema(description = "Список продуктов и их количество (ключ: ID продукта, значение: количество)",
            example = "{\"1\": 2, \"2\": 1}", required = true)
    private Map<Long, Integer> products;
}
