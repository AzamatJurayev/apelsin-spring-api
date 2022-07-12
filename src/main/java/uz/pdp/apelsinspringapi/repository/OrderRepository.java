package uz.pdp.apelsinspringapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apelsinspringapi.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

//    List<Integer> findAllByCustomer_IdBetween(Integer id);

}
