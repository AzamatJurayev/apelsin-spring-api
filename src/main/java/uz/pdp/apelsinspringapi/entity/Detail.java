package uz.pdp.apelsinspringapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "datail")
public class Detail {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ord_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "pr_id")
    private Product product;

    @Column(nullable = false)
    private Short quantity;
}
