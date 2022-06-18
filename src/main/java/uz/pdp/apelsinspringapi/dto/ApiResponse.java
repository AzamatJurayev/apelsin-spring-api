package uz.pdp.apelsinspringapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponse<T> {
    private String message;
    private boolean success;
    private T data;
}
