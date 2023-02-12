package PrimeNumber.PrimeNumber_Generator.Service.Impl;

import PrimeNumber.PrimeNumber_Generator.Models.PrimeNumber;
import PrimeNumber.PrimeNumber_Generator.Excptions.PrimeNumberException;
import PrimeNumber.PrimeNumber_Generator.Models.Primes;
import PrimeNumber.PrimeNumber_Generator.Repository.PrimeNumberRepository;
import PrimeNumber.PrimeNumber_Generator.Repository.PrimesRepository;
import PrimeNumber.PrimeNumber_Generator.RequestDTO.PrimeNumberRequest;
import PrimeNumber.PrimeNumber_Generator.ResponseDto.PrimeNumberResponse;
import PrimeNumber.PrimeNumber_Generator.Service.PrimeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;


import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@Service
public class PrimeNumberServiceImpl implements PrimeNumberService {

    @Autowired
    PrimeNumberRepository primeNumberRepository;

    @Autowired
    PrimesRepository primesRepository;
    @Override
    public String SetPrimes(PrimeNumberRequest primeNumberRequest) {

        String ans = null;
        String PrimeString=null;
        if(primeNumberRequest.getEmail()==null || !primeNumberRequest.getEmail().contains("@gmail.com")){
            throw new PrimeNumberException("Please enter proper email");
        }
        PrimeNumber primeNumber = PrimeNumber.builder().email(primeNumberRequest.getEmail()).min_Range(primeNumberRequest.getMin_Range()).max_Range(primeNumberRequest.getMax_Range()).algorithm(primeNumberRequest.getAlgorithm()).build();


            if (primeNumber.getMin_Range() >= primeNumber.getMax_Range()) {
                throw new PrimeNumberException("Please enter range properly");
            }

            if (primeNumber.getMax_Range() <= 0 ) {
                throw new PrimeNumberException("Please enter Positive Maximum Number");
            }
            if (primeNumber.getMin_Range() < 0) {
                primeNumber.setMin_Range(0);
            }
            String Algo = primeNumber.getAlgorithm();
            List<Integer> list = new ArrayList<>();
            Instant start = Instant.now();

            if (Algo.contentEquals("Method1")) {
                list = PrimeGeneratorMethod1(primeNumber.getMin_Range(), primeNumber.getMax_Range());

            } else if (Algo.contentEquals("Method2")) {
                list = PrimeGeneratorMethod2(primeNumber.getMin_Range(), primeNumber.getMax_Range());

            } else if (Algo.contentEquals("Method3")) {
                list = PrimeGeneratorMethod3(primeNumber.getMin_Range(), primeNumber.getMax_Range());
            } else {

                throw new PrimeNumberException("Please enter Proper Method");
            }
            if(list.size()==0){
                throw new PrimeNumberException("There are no primes found in this range");
            }
            List<Primes> x=new ArrayList<>();
            PrimeString = settingPrimesList(list,x);

            for(Primes p: x){
                p.setPrimeNumber(primeNumber);
            }
            primeNumber.setPrimesList(x);
            
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            primeNumber.setTimeElapsed(String.valueOf(timeElapsed));


           //adding created time to Database
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            primeNumber.setCreatedTime(timestamp);
            primeNumber.setPrimeNumberCount(list.size());
            StopWatch timeMeasure = new StopWatch();
            primeNumberRepository.save(primeNumber);



        return PrimeString;
        }



    private String settingPrimesList(List<Integer> list, List<Primes> list1) {
            try {
                 for(int i=0;i<list.size()-1;i++){
                     Primes primes=new Primes();
                    // p=p+list.get(i)+",";

                     //Setting primes to primenumber

                     primes.setPrimenumber(list.get(i));
                     list1.add(primes);
                     //primes.setPrimeNumber(primeNumber);
                 }
                Primes primes=new Primes();
                 //p=p+(list.get(list.size()-1));
                 primes.setPrimenumber(list.get(list.size()-1));
                 //primes.setPrimeNumber(primeNumber);
                 list1.add(primes);
                 primesRepository.saveAll(list1);


            } catch (Exception e) {
                return String.valueOf(new PrimeNumberException("Error Occured while converting from List to String"));
            }

        return "Primes are Successfully Created"   ;
    }


    public  List<Integer> PrimeGeneratorMethod1(int LowerLimit,int HigherLimit){
            List<Integer> ans=new ArrayList<>();
            for(int i=LowerLimit;i<=HigherLimit;i++){
                int n=i;
                boolean isPrime=true;
                int j=2;
                while(j*j<=n){
                    if(n%j==0){
                        isPrime=false;
                        break;
                    }
                    j++;
                }
                if(isPrime && n!=1)
                    ans.add(n);
            }
            return ans;
    }
    public  List<Integer> PrimeGeneratorMethod2(int LowerLimit,int HigherLimit){
        List<Integer> ans=new ArrayList<>();
        for(int i=LowerLimit;i<=HigherLimit;i++){
            int n=i;
            boolean isPrime=true;
            for(int j=2;j<=n/2;j++){
                if(n%j==0){
                    isPrime=false;
                    break;
                }
            }
            if(isPrime && n!=1)
                ans.add(n);

        }
        return ans;
    }
    public List<Integer> PrimeGeneratorMethod3(int LowerLimit,int HigherLimit){
        List<Integer> ans=new ArrayList<>();
        for(int i=LowerLimit;i<=HigherLimit;i++){
            int n=i;
            boolean isPrime=true;
            //checking condition
            for(int j=2;j<n;j++){
                if(n%j==0){
                   isPrime=false;
                   break;
                }
            }
            if(isPrime && n!=1 && n!=0)
                ans.add(n);

        }
        return ans;
    }
    @Override
    public PrimeNumberResponse GetPrimes(String email){

        List<PrimeNumber> primeNumberList=primeNumberRepository.findByEmail(email);
        if(primeNumberList==null || primeNumberList.size()==0){
            throw new PrimeNumberException("There is No past records with this emailId");
        }
        PrimeNumber primeNumber=primeNumberList.get(primeNumberList.size()-1);
        List<Primes > primesList=primeNumber.getPrimesList();
        List<Integer> numbersList=new ArrayList<>();
        for(Primes x: primesList){
            numbersList.add(x.getPrimenumber());
        }
        PrimeNumberResponse primeNumberResponse=PrimeNumberResponse.builder().max_Range(primeNumber.getMax_Range()).min_Range(primeNumber.getMin_Range()).timeElapsed(primeNumber.getTimeElapsed()).algorithm(primeNumber.getAlgorithm()).primeNumberCount(primeNumber.getPrimeNumberCount()).createdTime(primeNumber.getCreatedTime()).build();
        primeNumberResponse.setPrimeList(numbersList);
        return primeNumberResponse;
    }

}
