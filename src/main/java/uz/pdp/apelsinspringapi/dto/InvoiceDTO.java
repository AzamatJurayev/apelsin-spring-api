package uz.pdp.apelsinspringapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InvoiceDTO {
    private BigDecimal amount;
    private Integer orderId;
}
