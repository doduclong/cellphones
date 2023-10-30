package com.example.cellphones.service.impl;

import com.example.cellphones.dto.CategoryDto;
import com.example.cellphones.mapper.CategoryMapper;
import com.example.cellphones.model.Category;
import com.example.cellphones.repository.CategoryRepository;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.response.ResponseStatus;
import com.example.cellphones.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepo;
    @Override
    public ResponseObject<CategoryDto> createCategory(String name) {
        ResponseObject<CategoryDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        try {
            Category category = Category.builder()
                    .name(name)
                    .build();
            category = this.categoryRepo.save(category);
            res.setData(CategoryMapper.responseCategoryDtoFromModel(category));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public boolean deleteCategory(Long id) {
        try {
            this.categoryRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ResponseObject<List<CategoryDto>> getCategories() {
        ResponseObject<List<CategoryDto>> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        List<Category> categories = this.categoryRepo.findAll();
        res.setData(categories.stream().map(CategoryMapper::responseCategoryDtoFromModel).collect(Collectors.toList()));
        return res;
    }

    @Override
    public ResponseObject<CategoryDto> updateCategory(String name) {
        ResponseObject<CategoryDto> res = new ResponseObject<>(true, ResponseStatus.DO_SERVICE_SUCCESSFUL);
        Category oldCategory = this.categoryRepo.findByName(name);
        oldCategory.setName(name);
        oldCategory = this.categoryRepo.saveAndFlush(oldCategory);
        res.setData(CategoryMapper.responseCategoryDtoFromModel(oldCategory));
        return res;
    }
}
