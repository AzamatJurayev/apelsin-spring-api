package uz.pdp.apelsinspringapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.dto.OrderDTO;
import uz.pdp.apelsinspringapi.entity.*;
import uz.pdp.apelsinspringapi.repository.*;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    public ApiResponse getAll() {
        return ApiResponse.builder().message("Mana").success(true).data(orderRepository.findAll()).build();
    }

    public ApiResponse save(OrderDTO orderDTO) {
        Order order = new Order();
        Optional<Customer> customer = customerRepository.findById(orderDTO.getCustomerId());
        if (customer.isPresent()) {
            order.setCustomer(customer.get());
        } else {
            return ApiResponse.builder().message("Not Found Customer!").build();
        }
        orderRepository.save(order);

        Optional<Product> product = productRepository.findById(orderDTO.getProductId());
        if (product.isPresent()) {
            order.setCustomer(customer.get());
        } else {
            return ApiResponse.builder().message("Not Found Product!").build();
        }
        Detail detail = new Detail();
        detail.setOrder(order);
        detail.setProduct(product.get());
        detail.setQuantity(orderDTO.getDetailQuantity());
        detailRepository.save(detail);

        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setAmount(detail.getProduct().getPrice().multiply(BigDecimal.valueOf(detail.getQuantity())));
        invoiceRepository.save(invoice);

        if (order != null) {
            return ApiResponse.builder().data(order).message("Saved!").success(true).build();
        } else {
            return ApiResponse.builder().data(order).message("Error!").success(false).build();
        }
    }

    public ApiResponse getOne(Integer id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            return ApiResponse.builder().data(byId.get()).success(true).message("Mana").build();
        } else {
            return ApiResponse.builder().success(false).message("Not Found").build();
        }
    }

    public ApiResponse edit(Integer id, OrderDTO orderDTO) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            Order order = byId.get();

            Optional<Customer> customer = customerRepository.findById(orderDTO.getCustomerId());
            if (customer.isPresent()) {
                order.setCustomer(customer.get());
            } else {
                return ApiResponse.builder().message("Not Found Customer!").build();
            }
            orderRepository.save(order);

            Optional<Product> product = productRepository.findById(orderDTO.getProductId());
            if (product.isPresent()) {
                order.setCustomer(customer.get());
            } else {
                return ApiResponse.builder().message("Not Found Product!").build();
            }
            Detail detail = new Detail();
            detail.setOrder(order);
            detail.setProduct(product.get());
            detail.setQuantity(orderDTO.getDetailQuantity());
            detailRepository.save(detail);

            Invoice invoice = new Invoice();
            invoice.setOrder(order);
            invoice.setAmount(detail.getProduct().getPrice().multiply(BigDecimal.valueOf(detail.getQuantity())));
            invoiceRepository.save(invoice);

            orderRepository.save(order);
            return ApiResponse.builder().message("Edited!").success(true).data(order).build();
        } else {
            return ApiResponse.builder().success(false).message("Not Found!").build();
        }
    }

    public ApiResponse delete(Integer id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            orderRepository.deleteById(id);
            return ApiResponse.builder().message("Deleted!").success(true).build();
        } else {
            return ApiResponse.builder().success(false).message("Not Found!").build();
        }
    }
}
