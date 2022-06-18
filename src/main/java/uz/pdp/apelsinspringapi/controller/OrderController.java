package uz.pdp.apelsinspringapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.dto.OrderDTO;
import uz.pdp.apelsinspringapi.entity.Order;
import uz.pdp.apelsinspringapi.service.OrderService;
import uz.pdp.apelsinspringapi.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public ApiResponse getAll(){
        return orderService.getAll();
    }

    @PostMapping
    public ApiResponse save(@RequestBody OrderDTO orderDTO){
        return orderService.save(orderDTO);
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Integer id){
        return orderService.getOne(id);
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody OrderDTO orderDTO){
        return orderService.edit(id,orderDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return orderService.delete(id);
    }
}
