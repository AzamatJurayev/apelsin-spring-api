package uz.pdp.apelsinspringapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "payment")
public class Payment {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial
    private Integer id;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp time;

    @Column(nullable = false,precision = 8,scale = 2)
    private BigDecimal amount;

    @ManyToOne
    private Invoice invoice; //fk

}
