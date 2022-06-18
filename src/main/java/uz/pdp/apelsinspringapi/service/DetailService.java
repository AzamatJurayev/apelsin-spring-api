package uz.pdp.apelsinspringapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.dto.DetailDTO;
import uz.pdp.apelsinspringapi.entity.Order;
import uz.pdp.apelsinspringapi.entity.Product;
import uz.pdp.apelsinspringapi.entity.Detail;
import uz.pdp.apelsinspringapi.repository.OrderRepository;
import uz.pdp.apelsinspringapi.repository.ProductRepository;
import uz.pdp.apelsinspringapi.repository.DetailRepository;

import java.util.Optional;

@Service
public class DetailService {

    @Autowired
    DetailRepository detailRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;

    public ApiResponse getAll() {
        return ApiResponse.builder().message("Mana").success(true).data(detailRepository.findAll()).build();
    }

    public ApiResponse save(DetailDTO detailDTO) {
        Detail detail = new Detail();
        Optional<Order> byIdOrd = orderRepository.findById(detailDTO.getProductId());
        Optional<Product> byIdPr = productRepository.findById(detailDTO.getProductId());
        if (byIdOrd.isPresent() && byIdPr.isPresent()) {
            detail.setProduct(byIdPr.get());
            detail.setOrder(byIdOrd.get());
            detail.setQuantity(detailDTO.getQuantity());
        }else return ApiResponse.builder().message("Not Found Product or Order").build();
        Detail save = detailRepository.save(detail);
        if (save != null) {
            return ApiResponse.builder().data(save).message("Saved!").success(true).build();
        } else {
            return ApiResponse.builder().data(save).message("Error!").success(false).build();
        }

    }

    public ApiResponse getOne(Integer id) {
        Optional<Detail> byId = detailRepository.findById(id);
        if (byId.isPresent()) {
            return ApiResponse.builder().data(byId.get()).success(true).message("Mana").build();
        } else {
            return ApiResponse.builder().success(false).message("Not Found").build();
        }
    }

    public ApiResponse edit(Integer id, DetailDTO detailDTO) {
        Optional<Detail> byId = detailRepository.findById(id);
        if (byId.isPresent()){
            Detail detail = byId.get();
            Optional<Product> product = productRepository.findById(detailDTO.getProductId());
            Optional<Order> order = orderRepository.findById(detailDTO.getProductId());
            if (product.isPresent() && order.isPresent()) {
                detail.setProduct(product.get());
                detail.setOrder(order.get());
                detail.setQuantity(detailDTO.getQuantity());
            }else return ApiResponse.builder().message("Not Found Product or Order").build();
            detailRepository.save(detail);
            return ApiResponse.builder().message("Edited!").success(true).data(detail).build();
        }else return ApiResponse.builder().message("Not Found Detail!").build();
    }

    public ApiResponse delete(Integer id) {
        Optional<Detail> byId = detailRepository.findById(id);
        if (byId.isPresent()) {
            detailRepository.deleteById(id);
            return ApiResponse.builder().message("Deleted!").success(true).build();
        } else {
            return ApiResponse.builder().success(false).message("Not Found!").build();
        }
    }
}
