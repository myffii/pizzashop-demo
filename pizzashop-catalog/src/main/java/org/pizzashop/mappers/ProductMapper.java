package org.pizzashop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pizzashop.dto.ProductDTO;
import org.pizzashop.models.Category;
import org.pizzashop.models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.id")
    ProductDTO toProductDTO(Product product);

    @Mapping(target = "category", source = "categoryId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    Product toProduct(ProductDTO productDTO);

    Category map(Long id);
}
