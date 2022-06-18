package uz.pdp.apelsinspringapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.entity.Category;
import uz.pdp.apelsinspringapi.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ApiResponse getAll(){
        return categoryService.getAll();
    }

    @PostMapping
    public ApiResponse save(@RequestBody Category category){
        return categoryService.save(category);
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Integer id){
        return categoryService.getOne(id);
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody Category category){
        return categoryService.edit(id,category);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return categoryService.delete(id);
    }
}
