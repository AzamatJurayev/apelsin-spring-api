package uz.pdp.apelsinspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apelsinspringapi.entity.Category;
import uz.pdp.apelsinspringapi.entity.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

//    List<Payment> findAllByInvoice_IdBetween(List<Integer> id);

}
