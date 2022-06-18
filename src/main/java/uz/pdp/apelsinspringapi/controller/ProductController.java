package uz.pdp.apelsinspringapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.dto.ProductDTO;
import uz.pdp.apelsinspringapi.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ApiResponse getAll(){
        return productService.getAll();
    }

    @PostMapping
    public ApiResponse save(@RequestBody ProductDTO productDTO){
        return productService.save(productDTO);
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Integer id){
        return productService.getOne(id);
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody ProductDTO productDTO){
        return productService.edit(id,productDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return productService.delete(id);
    }
}
