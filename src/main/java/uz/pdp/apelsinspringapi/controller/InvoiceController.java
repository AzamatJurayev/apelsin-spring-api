package uz.pdp.apelsinspringapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.dto.InvoiceDTO;
import uz.pdp.apelsinspringapi.service.InvoiceService;

@RestController
@RequestMapping("invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping
    public ApiResponse getAll(){
        return invoiceService.getAll();
    }

    @PostMapping
    public ApiResponse save(@RequestBody InvoiceDTO invoiceDTO){
        return invoiceService.save(invoiceDTO);
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Integer id){
        return invoiceService.getOne(id);
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody InvoiceDTO invoiceDTO){
        return invoiceService.edit(id,invoiceDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return invoiceService.delete(id);
    }
}
