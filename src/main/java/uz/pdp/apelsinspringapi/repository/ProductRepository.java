package uz.pdp.apelsinspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apelsinspringapi.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
