package PrimeNumber.PrimeNumber_Generator.Controller;

import PrimeNumber.PrimeNumber_Generator.Excptions.PrimeNumberException;
import PrimeNumber.PrimeNumber_Generator.RequestDTO.PrimeNumberRequest;
import PrimeNumber.PrimeNumber_Generator.ResponseDto.PrimeNumberResponse;
import PrimeNumber.PrimeNumber_Generator.Service.Impl.PrimeNumberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("prime")
public class PrimeNumberController {

    @Autowired
   PrimeNumberServiceImpl primeNumberService;

    @PostMapping("/generatePrimes")
    public ResponseEntity<String> SetPrimes(@RequestBody PrimeNumberRequest primeNumberRequest){
            String ans=null;
            ans = primeNumberService.SetPrimes(primeNumberRequest);

        return new ResponseEntity<>(ans, HttpStatus.OK);

    }
    @GetMapping("getPrimes")
    public ResponseEntity<PrimeNumberResponse> GetPrimes(@RequestParam String email) {
       PrimeNumberResponse primeNumberResponse=primeNumberService.GetPrimes(email);

        return new ResponseEntity<>(primeNumberResponse, HttpStatus.OK);
    }

}
