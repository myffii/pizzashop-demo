package org.pizzashop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pizzashop.dto.ProductDTO;
import org.pizzashop.dto.ProductResponseDTO;
import org.pizzashop.models.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductResponseDTOMapper {

    @Mapping(target = "categoryId", source = "category.id")
    ProductResponseDTO toProductResponse(Product product);

    @Mapping(target = "category", ignore = true)
    Product toProduct(ProductResponseDTO productResponseDTO);

    ProductResponseDTO DTOtoProductResponse(ProductDTO productDTO);

    @Mapping(target = "categoryId", source = "category.id")
    List<ProductResponseDTO> toProductResponseList(List<Product> products);
}
