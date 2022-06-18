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
@Table(name = "customer")
public class Customer {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial
    private Integer id;

    @Column(nullable = false,length = 14)
    private String name;

    @Column(nullable = false,length = 3)
    private String country;

    @Column(columnDefinition = "text")
    private String address; //255 tadan belgini qabul qila oladi

    @Column(length = 50)
    private String phone;
}
