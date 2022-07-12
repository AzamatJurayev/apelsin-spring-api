package uz.pdp.apelsinspringapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apelsinspringapi.dto.ApiResponse;
import uz.pdp.apelsinspringapi.dto.PaymentDTO;
import uz.pdp.apelsinspringapi.dto.PaymentDTO;
import uz.pdp.apelsinspringapi.service.PaymentService;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PreAuthorize(value = "hasAuthority('READ_PAYMENT')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(paymentService.getAll());
    }

    //    @PreAuthorize(value = "hasAuthority('READ_OWN_PAYMENT')")
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOwnPayment(@PathVariable Integer id){
//        return ResponseEntity.ok(paymentService.getOwnPayment(id));
//    }
    @PreAuthorize(value = "hasAuthority('ADD_PAYMENT')")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody PaymentDTO payment) {
        ApiResponse save = paymentService.save(payment);
        return ResponseEntity.status(save.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(save);
    }
    @PreAuthorize(value = "hasAuthority('READ_PAYMENT')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse response = paymentService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
    @PreAuthorize(value = "hasAuthority('UPDATE_PAYMENT')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody PaymentDTO payment) {
        ApiResponse response = paymentService.edit(id, payment);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }
    @PreAuthorize(value = "hasAuthority('DELETE_PAYMENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ApiResponse response = paymentService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
}
