package org.pizzashop.services;

import lombok.RequiredArgsConstructor;
import org.pizzashop.dto.CategoryDTO;
import org.pizzashop.mappers.CategoryMapper;
import org.pizzashop.models.Category;
import org.pizzashop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public Category save(CategoryDTO categoryDTO) {
        return categoryRepository.save(categoryMapper.toCategory(categoryDTO));
    }
}
