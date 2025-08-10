package org.pizzashop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;


    @Pattern(
            regexp = "^\\+7[0-9]{10}$",
            message = "Номер телефона должен начинаться с +7 и содержать ровно 10 цифр после"
    )
    @NotBlank
    @Schema(description = "Номер телефона в формате +7(10цифр)", example = "+70000000000")
    private String phoneNumber;
}
