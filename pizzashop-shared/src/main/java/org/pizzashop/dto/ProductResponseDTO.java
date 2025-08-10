package org.pizzashop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductResponseDTO {
    @Min(value = 1)
    private Long id;

    @NotBlank
    private String name;

    @Min(value = 1)
    private Double price;

    @NotNull
    private Long categoryId;
}
