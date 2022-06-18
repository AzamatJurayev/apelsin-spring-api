package uz.pdp.apelsinspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apelsinspringapi.entity.Category;
import uz.pdp.apelsinspringapi.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
