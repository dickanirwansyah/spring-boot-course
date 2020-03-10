package com.pageable.springpageable.controller;

import com.pageable.springpageable.commandservice.response.Response;
import com.pageable.springpageable.entity.Category;
import com.pageable.springpageable.model.CategoryRequest;
import com.pageable.springpageable.model.ResponseApi;
import com.pageable.springpageable.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping(value = "/update-category/{categoryId}")
    public ResponseEntity<ResponseApi> updateCategory(@PathVariable("categoryId")int categoryId,
                                                      @RequestBody @Valid CategoryRequest requestBody){

        Category category = categoryRepository.findById(categoryId);
        if (category == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (category.getName().equals(requestBody.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        category.setName(requestBody.getName());
        category.setCreatedAt(category.getCreatedAt());
        category.setUpdatedAt(new Date());
        categoryRepository.save(category);

        ResponseApi responseApi = ResponseApi
                .builder()
                .code(HttpStatus.OK.value())
                .timestamp(new Date())
                .data(category)
                .message("success")
                .build();

        return ResponseEntity.ok(responseApi);
    }

    @PostMapping(value = "/delete-category/{categoryId}")
    public ResponseEntity<ResponseApi> deleteCategory(@PathVariable("categoryId")int categoryId){

        Category category = categoryRepository.findById(categoryId);
        ResponseApi responseApi = null;
        if (category == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            categoryRepository.delete(category);
            responseApi = ResponseApi.builder()
                    .message("success")
                    .timestamp(new Date())
                    .code(HttpStatus.OK.value())
                    .data(category)
                    .message("success delete category with id "+categoryId)
                    .build();
        }

        return ResponseEntity.ok(responseApi);
    }

    @PostMapping(value = "/save-category")
    public ResponseEntity<ResponseApi> createCategory(@RequestBody @Valid CategoryRequest requestBody){

        //check name
        Optional<Category> categoryName = categoryRepository.findByName(requestBody.getName());
        ResponseApi responseApi = null;
        if (categoryName.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }else{
            Category category = Category
                    .builder()
                    .name(requestBody.getName())
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();
            categoryRepository.save(category);
            responseApi = ResponseApi.builder()
                    .timestamp(new Date())
                    .code(HttpStatus.OK.value())
                    .message("success")
                    .data(category)
                    .build();
        }
        return ResponseEntity.ok(responseApi);
    }

    @GetMapping(value = "/get-category/{categoryId}")
    public ResponseEntity<ResponseApi> getCategoryId(@PathVariable("categoryId")int categoryId){
        Category category = categoryRepository.findById(categoryId);
        ResponseApi responseApi = null;
        if (category == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            responseApi = ResponseApi.builder()
                    .message("success")
                    .data(category)
                    .code(HttpStatus.OK.value())
                    .timestamp(new Date())
                    .build();
        }
        return ResponseEntity.ok(responseApi);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_AGENT_FINANCE')")
    @GetMapping(value = "/list-category")
    public ResponseEntity<ResponseApi> listCategory(Pageable pageable){
        Page<Category> categories = categoryRepository.findAll(pageable);
        ResponseApi responseApi = null;
        if (categories.isEmpty() && categories == null){
            responseApi = ResponseApi.builder()
                    .timestamp(new Date())
                    .code(HttpStatus.NOT_FOUND.value())
                    .message("data not found")
                    .data(categories)
                    .build();
        }else{
            responseApi = ResponseApi.builder()
                    .timestamp(new Date())
                    .code(HttpStatus.OK.value())
                    .message("success")
                    .data(categories)
                    .build();
        }
        return ResponseEntity.ok(responseApi);
    }
}
