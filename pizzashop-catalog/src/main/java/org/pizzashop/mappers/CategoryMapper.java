package org.pizzashop.mappers;

import org.mapstruct.Mapper;
import org.pizzashop.dto.CategoryDTO;
import org.pizzashop.models.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toCategoryDTO(Category category);

    Category toCategory(CategoryDTO categoryDTO);
}