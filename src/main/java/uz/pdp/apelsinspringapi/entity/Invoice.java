package uz.pdp.apelsinspringapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial
    private Integer id;

    @OneToOne
    private Order order;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal amount;

    @CreatedDate //sistemadan oladi
    private Date issued;

    @CreatedDate
    private Date due;


}
