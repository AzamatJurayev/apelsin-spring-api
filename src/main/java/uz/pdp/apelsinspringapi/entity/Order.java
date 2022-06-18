package uz.pdp.apelsinspringapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial
    private Integer id;

    @Column(nullable = false)
    @CreatedDate
    private Date date;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

}
