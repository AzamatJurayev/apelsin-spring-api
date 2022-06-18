package uz.pdp.apelsinspringapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.dto.ProductDTO;
import uz.pdp.apelsinspringapi.entity.*;
import uz.pdp.apelsinspringapi.repository.*;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse getAll() {
        return ApiResponse.builder().message("Mana").success(true).data(productRepository.findAll()).build();
    }

    public ApiResponse save(ProductDTO productDTO) {
        Product product = new Product();
        Optional<Category> byId = categoryRepository.findById(productDTO.getCategoryId());
        if (byId.isPresent()) {
            product.setCategory(byId.get());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setName(productDTO.getName());
            product.setPhoto(productDTO.getPhoto());
        }else return ApiResponse.builder().message("Not Found Category!").build();
        Product save = productRepository.save(product);
        if (save != null) {
            return ApiResponse.builder().data(save).message("Saved!").success(true).build();
        } else {
            return ApiResponse.builder().data(save).message("Error!").success(false).build();
        }

    }

    public ApiResponse getOne(Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            return ApiResponse.builder().data(byId.get()).success(true).message("Mana").build();
        } else {
            return ApiResponse.builder().success(false).message("Not Found").build();
        }
    }

    public ApiResponse edit(Integer id, ProductDTO productDTO) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()){
            Product product = byId.get();
            Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
            if (byId.isPresent()) {
                product.setCategory(category.get());
                product.setPrice(productDTO.getPrice());
                product.setDescription(productDTO.getDescription());
                product.setName(productDTO.getName());
                product.setPhoto(productDTO.getPhoto());
            }else return ApiResponse.builder().message("Not Found Category!").build();
            productRepository.save(product);
            return ApiResponse.builder().message("Edited!").success(true).data(product).build();
        }else return ApiResponse.builder().message("Not Found Product!").build();
    }

    public ApiResponse delete(Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            productRepository.deleteById(id);
            return ApiResponse.builder().message("Deleted!").success(true).build();
        } else {
            return ApiResponse.builder().success(false).message("Not Found!").build();
        }
    }
}
