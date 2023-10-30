package com.example.cellphones.service;

import com.example.cellphones.dto.CategoryDto;
import com.example.cellphones.response.ResponseObject;

import java.util.List;

public interface CategoryService {
    ResponseObject<CategoryDto> createCategory(String name);

    boolean deleteCategory(Long id);

    ResponseObject<List<CategoryDto>> getCategories();

    ResponseObject<CategoryDto> updateCategory(String name);
}
