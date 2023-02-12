package PrimeNumber.PrimeNumber_Generator.Service;

import PrimeNumber.PrimeNumber_Generator.RequestDTO.PrimeNumberRequest;
import PrimeNumber.PrimeNumber_Generator.ResponseDto.PrimeNumberResponse;

public interface PrimeNumberService{


     String SetPrimes(PrimeNumberRequest primeNumberRequest) throws Exception;

     PrimeNumberResponse GetPrimes(String email) throws  Exception;
}
