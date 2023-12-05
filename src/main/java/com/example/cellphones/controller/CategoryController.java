package com.example.cellphones.controller;

import com.example.cellphones.dto.CategoryDto;
import com.example.cellphones.dto.request.category.UpdateCategoryReq;
import com.example.cellphones.response.ResponseObject;
import com.example.cellphones.response.ResponseStatus;
import com.example.cellphones.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(path = "/")
    public ResponseEntity<?> getCategory() {
        ResponseObject<List<CategoryDto>> res = categoryService.getCategories();
        return ResponseEntity.ok(res);
    }

    @PostMapping(path = "/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> createCategory(@RequestBody UpdateCategoryReq req) {
        ResponseObject<CategoryDto> res = categoryService.createCategory(req.getName());
        return ResponseEntity.ok(res);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody UpdateCategoryReq req) {
        ResponseObject<CategoryDto> res = categoryService.updateCategory(Long.parseLong(id), req.getName());
        return ResponseEntity.ok(res);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        boolean deleted = categoryService.deleteCategory(Long.parseLong(id));
        if (deleted){
            return ResponseEntity.ok(new ResponseObject<>(true, com.example.cellphones.response.ResponseStatus.DO_SERVICE_SUCCESSFUL, "Deleted category address successfully !"));
        }
        return ResponseEntity.ok(new ResponseObject<>(false, ResponseStatus.UNHANDLED_ERROR, "Deleted category address failed !"));
    }
}
