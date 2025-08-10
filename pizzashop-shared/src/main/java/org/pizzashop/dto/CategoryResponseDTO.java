package org.pizzashop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryResponseDTO {
    @Min(value = 1)
    private Long id;

    @NotBlank
    private String name;
}
