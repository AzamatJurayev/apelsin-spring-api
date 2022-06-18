package uz.pdp.apelsinspringapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.dto.PaymentDTO;
import uz.pdp.apelsinspringapi.service.PaymentService;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping
    public ApiResponse getAll(){
        return paymentService.getAll();
    }

    @PostMapping
    public ApiResponse save(@RequestBody PaymentDTO paymentDTO){
        return paymentService.save(paymentDTO);
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Integer id){
        return paymentService.getOne(id);
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody PaymentDTO paymentDTO){
        return paymentService.edit(id,paymentDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return paymentService.delete(id);
    }
}
