package org.pizzashop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pizzashop.dto.OrderDTO;
import org.pizzashop.dto.ProductResponseDTO;
import org.pizzashop.dto.UserDTO;
import org.pizzashop.dto.UserResponseDTO;
import org.pizzashop.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    @Operation(summary = "Создание нового пользователя")
    public Long createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.save(userDTO).getId();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя по его id")
    public UserResponseDTO getUser(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/getMenu")
    @Operation(summary = "Получение меню")
    public List<ProductResponseDTO> getMenu(@RequestParam int page, @RequestParam int size) {
        return userService.getMenu(page, size);
    }


    @PostMapping("/createOrder")
    @Operation(summary = "Создание заказа", description = "В products передаётся k:v = productId:quantity")
    public Long createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return userService.createOrder(orderDTO);
    }
}
