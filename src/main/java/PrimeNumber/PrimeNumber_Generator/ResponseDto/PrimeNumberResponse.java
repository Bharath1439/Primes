package PrimeNumber.PrimeNumber_Generator.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PrimeNumberResponse {

    private Date createdTime;;

    private int  min_Range;

    private int max_Range;

    private String timeElapsed;

    private String algorithm;

    private int primeNumberCount;

    private List<Integer> primeList;
}
