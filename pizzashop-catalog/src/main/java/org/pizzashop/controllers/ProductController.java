package org.pizzashop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pizzashop.dto.ProductDTO;
import org.pizzashop.dto.ProductResponseDTO;
import org.pizzashop.services.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Получение всех продуктов")
    public List<ProductResponseDTO> findAll(int page, int size) {
        return productService.findAll(page, size);
    }

    @PostMapping("/create")
    @Operation(summary = "Создание нового продукта")
    public Long create(@RequestBody @Valid ProductDTO productDTO) {
        return productService.save(productDTO).getId();
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение продукта по его id")
    public ProductResponseDTO findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping("{id}/uploadImage")
    @Operation(summary = "Загрузка изображения продукта")
    public ProductResponseDTO uploadImage(@PathVariable("id") Long id, @RequestParam(value = "image") MultipartFile image) throws IOException {
        return productService.uploadImage(id, image);
    }

    @GetMapping("/{id}/getImage")
    @Operation(summary = "Получение изображения продукта")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        byte[] image = productService.getProductImageById(id);
        if (image == null || image.length == 0) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(image.length);

        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
