package org.pizzashop.mappers;

import org.mapstruct.Mapper;
import org.pizzashop.dto.CategoryDTO;
import org.pizzashop.dto.CategoryResponseDTO;
import org.pizzashop.models.Category;

@Mapper(componentModel = "spring")
public interface CategoryResponseDTOMapper {

    CategoryResponseDTO toCategoryResponseDTO(Category category);

    Category toCategory(CategoryResponseDTO categoryResponseDTO);

    CategoryDTO toCategoryDTO(CategoryResponseDTO categoryResponseDTO);

    CategoryResponseDTO DTOtoCategoryResponse(CategoryDTO categoryDTO);
}
