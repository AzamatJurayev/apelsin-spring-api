package uz.pdp.apelsinspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apelsinspringapi.entity.Category;
import uz.pdp.apelsinspringapi.entity.Invoice;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {

//    List<Integer> findAllByOrder_IdBetween(List<Integer> ids);

}
