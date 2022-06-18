package uz.pdp.apelsinspringapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.dto.InvoiceDTO;
import uz.pdp.apelsinspringapi.entity.Invoice;
import uz.pdp.apelsinspringapi.entity.Order;
import uz.pdp.apelsinspringapi.entity.Product;
import uz.pdp.apelsinspringapi.repository.InvoiceRepository;
import uz.pdp.apelsinspringapi.repository.OrderRepository;
import uz.pdp.apelsinspringapi.repository.ProductRepository;

import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    OrderRepository orderRepository;

    public ApiResponse getAll() {
        return ApiResponse.builder().message("Mana").success(true).data(invoiceRepository.findAll()).build();
    }

    public ApiResponse save(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        Optional<Order> byIdOrd = orderRepository.findById(invoiceDTO.getOrderId());
        if (byIdOrd.isPresent()) {
            invoice.setOrder(byIdOrd.get());
            invoice.setAmount(invoiceDTO.getAmount());
        }else return ApiResponse.builder().message("Not Found Product or Order").build();
        Invoice save = invoiceRepository.save(invoice);
        if (save != null) {
            return ApiResponse.builder().data(save).message("Saved!").success(true).build();
        } else {
            return ApiResponse.builder().data(save).message("Error!").success(false).build();
        }

    }

    public ApiResponse getOne(Integer id) {
        Optional<Invoice> byId = invoiceRepository.findById(id);
        if (byId.isPresent()) {
            return ApiResponse.builder().data(byId.get()).success(true).message("Mana").build();
        } else {
            return ApiResponse.builder().success(false).message("Not Found").build();
        }
    }

    public ApiResponse edit(Integer id, InvoiceDTO invoiceDTO) {
        Optional<Invoice> byId = invoiceRepository.findById(id);
        if (byId.isPresent()){
            Invoice invoice = byId.get();
            Optional<Order> order = orderRepository.findById(invoiceDTO.getOrderId());
            if (byId.isPresent()) {
                invoice.setOrder(order.get());
                invoice.setAmount(invoiceDTO.getAmount());
            }else return ApiResponse.builder().message("Not Found Product or Order").build();
            invoiceRepository.save(invoice);
            return ApiResponse.builder().message("Edited!").success(true).data(invoice).build();
        }else return ApiResponse.builder().message("Not Found Invoice!").build();
    }

    public ApiResponse delete(Integer id) {
        Optional<Invoice> byId = invoiceRepository.findById(id);
        if (byId.isPresent()) {
            invoiceRepository.deleteById(id);
            return ApiResponse.builder().message("Deleted!").success(true).build();
        } else {
            return ApiResponse.builder().success(false).message("Not Found!").build();
        }
    }
}
