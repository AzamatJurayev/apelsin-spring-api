package uz.pdp.apelsinspringapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDTO {

    private Integer customerId;
    private Integer productId;
    private Short detailQuantity;



}
