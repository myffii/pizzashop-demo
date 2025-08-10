package org.pizzashop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pizzashop.dto.OrderDTO;
import org.pizzashop.dto.OrderResponseDTO;
import org.pizzashop.enums.OrderStatus;
import org.pizzashop.services.AdminService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/getOrders")
    @Operation(summary = "Получение заказов с фильтрами", description = "Дата передается в формате ГГГГ-ММ-ДД")
    public List<OrderResponseDTO> getOrders(
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate orderDate
    ) {
        return adminService.getOrders(customerId, status, orderDate);
    }

    @GetMapping("/getOrderById/{id}")
    @Operation(summary = "Получение заказа по ID")
    public OrderResponseDTO getOrderById(@PathVariable("id") Long id) {
        return adminService.getOrderById(id);
    }

    @PatchMapping("/changeOrderStatus/{id}")
    @Operation(summary = "Изменение статуса заказа")
    public OrderStatus changeOrderStatus(
            @PathVariable("id") Long id,
            @RequestParam OrderStatus status
    ) {
        return adminService.changeOrderStatus(id, status);
    }

    @PatchMapping("/changeOrderDetails/{id}")
    @Operation(summary = "Изменение деталей заказа")
    public OrderResponseDTO changeOrderDetails(
            @PathVariable("id") Long id,
            @RequestBody @Valid OrderDTO orderDTO
    ) {
        return adminService.changeOrderDetails(id, orderDTO);
    }
}
