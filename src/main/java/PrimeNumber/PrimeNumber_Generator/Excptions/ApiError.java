package PrimeNumber.PrimeNumber_Generator.Excptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError implements Serializable {
    private String message;
    private long httpStatus;
}
