package org.pizzashop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.pizzashop.dto.CategoryDTO;
import org.pizzashop.services.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Создание новой категории")
    public Long create(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO).getId();
    }
}
