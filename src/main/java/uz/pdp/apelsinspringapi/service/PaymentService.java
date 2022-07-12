package uz.pdp.apelsinspringapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.dto.PaymentDTO;
import uz.pdp.apelsinspringapi.entity.Invoice;
import uz.pdp.apelsinspringapi.entity.Payment;
import uz.pdp.apelsinspringapi.repository.InvoiceRepository;
import uz.pdp.apelsinspringapi.repository.OrderRepository;
import uz.pdp.apelsinspringapi.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    OrderRepository orderRepository;

    public ApiResponse getAll() {
        return ApiResponse.builder().message("Mana").success(true).data(paymentRepository.findAll()).build();
    }

//    public ApiResponse getOwnPayment(Integer id){
//        List<Integer> orders = orderRepository.findAllByCustomer_IdBetween(id);
//        List<Integer> invoices = invoiceRepository.findAllByOrder_IdBetween(orders);
//        return ApiResponse.builder().message("Mana").success(true).data(paymentRepository.findAllByInvoice_IdBetween(invoices)).build();
//    }

    public ApiResponse save(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        Optional<Invoice> byId = invoiceRepository.findById(paymentDTO.getInvoiceId());
        if (byId.isPresent()) {
            payment.setInvoice(byId.get());
            payment.setAmount(paymentDTO.getAmount());
        }else return ApiResponse.builder().message("Not Found Invoice!").build();
        Payment save = paymentRepository.save(payment);
        if (save != null) {
            return ApiResponse.builder().data(save).message("Saved!").success(true).build();
        } else {
            return ApiResponse.builder().data(save).message("Error!").success(false).build();
        }

    }

    public ApiResponse getOne(Integer id) {
        Optional<Payment> byId = paymentRepository.findById(id);
        if (byId.isPresent()) {
            return ApiResponse.builder().data(byId.get()).success(true).message("Mana").build();
        } else {
            return ApiResponse.builder().success(false).message("Not Found").build();
        }
    }

    public ApiResponse edit(Integer id, PaymentDTO paymentDTO) {
        Optional<Payment> byId = paymentRepository.findById(id);
        if (byId.isPresent()){
            Payment payment = byId.get();
            Optional<Invoice> invoice = invoiceRepository.findById(paymentDTO.getInvoiceId());
            if (byId.isPresent()) {
                payment.setInvoice(invoice.get());
                payment.setAmount(paymentDTO.getAmount());
            }else return ApiResponse.builder().message("Not Found Invoice!").build();
            paymentRepository.save(payment);
            return ApiResponse.builder().message("Edited!").success(true).data(payment).build();
        }else return ApiResponse.builder().message("Not Found Payment!").build();
    }

    public ApiResponse delete(Integer id) {
        Optional<Payment> byId = paymentRepository.findById(id);
        if (byId.isPresent()) {
            paymentRepository.deleteById(id);
            return ApiResponse.builder().message("Deleted!").success(true).build();
        } else {
            return ApiResponse.builder().success(false).message("Not Found!").build();
        }
    }
}
