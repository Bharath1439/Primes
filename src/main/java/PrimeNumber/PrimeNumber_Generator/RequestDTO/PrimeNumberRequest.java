package PrimeNumber.PrimeNumber_Generator.RequestDTO;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrimeNumberRequest {


    private int min_Range;

    @Column(nullable = false)
    private String email;

    private int max_Range;

    private String algorithm;
}
