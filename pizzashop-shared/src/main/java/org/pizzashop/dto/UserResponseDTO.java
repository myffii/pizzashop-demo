package org.pizzashop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserResponseDTO {
    @Min(value = 1)
    private Long id;

    @NotBlank
    private String username;

    @Pattern(
            regexp = "^\\+7[0-9]{10}$",
            message = "Номер телефона должен начинаться с +7 и содержать ровно 10 цифр после"
    )
    @NotBlank
    private String phoneNumber;
}
