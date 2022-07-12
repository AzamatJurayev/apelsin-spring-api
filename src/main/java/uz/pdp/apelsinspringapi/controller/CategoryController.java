package uz.pdp.apelsinspringapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.entity.Category;
import uz.pdp.apelsinspringapi.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @PreAuthorize(value = "hasAuthority('READ_CATEGORY')")
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }
    @PreAuthorize(value = "hasAuthority('ADD_CATEGORY')")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Category category){
        ApiResponse save = categoryService.save(category);
        return ResponseEntity.status(save.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(save);
    }
    @PreAuthorize(value = "hasAuthority('READ_CATEGORY')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        ApiResponse response = categoryService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
    @PreAuthorize(value = "hasAuthority('UPDATE_CATEGORY')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id,@RequestBody Category category){
        ApiResponse response = categoryService.edit(id, category);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }
    @PreAuthorize(value = "hasAuthority('DELETE_CATEGORY')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        ApiResponse response = categoryService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
}
