package uz.pdp.apelsinspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apelsinspringapi.entity.Category;
import uz.pdp.apelsinspringapi.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
}
