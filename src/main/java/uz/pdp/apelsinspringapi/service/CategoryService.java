package uz.pdp.apelsinspringapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.entity.Category;
import uz.pdp.apelsinspringapi.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse getAll() {
        return ApiResponse.builder().message("Mana").success(true).data(categoryRepository.findAll()).build();
    }

    public ApiResponse save(Category category) {
        Category save = categoryRepository.save(category);
        if (save != null) {
            return ApiResponse.builder().data(save).message("Saved!").success(true).build();
        } else {
            return ApiResponse.builder().data(save).message("Error!").success(false).build();
        }
    }

    public ApiResponse getOne(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            return ApiResponse.builder().data(byId.get()).success(true).message("Mana").build();
        } else {
            return ApiResponse.builder().success(false).message("Not Found").build();
        }
    }

    public ApiResponse edit(Integer id,Category category) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()){
            Category category1 = byId.get();
            category1.setName(category.getName());
            category1.setProducts(category.getProducts());
            categoryRepository.save(category1);
            return ApiResponse.builder().message("Edited!").success(true).data(category1).build();
        }
        else {
            return ApiResponse.builder().success(false).message("Not Found!").build();
        }
    }

    public ApiResponse delete(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()){
            categoryRepository.deleteById(id);
            return ApiResponse.builder().message("Deleted!").success(true).build();
        }
        else {
            return ApiResponse.builder().success(false).message("Not Found!").build();
        }
    }
}
