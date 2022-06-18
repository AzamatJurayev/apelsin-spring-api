package uz.pdp.apelsinspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apelsinspringapi.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
