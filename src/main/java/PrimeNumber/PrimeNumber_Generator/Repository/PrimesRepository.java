package PrimeNumber.PrimeNumber_Generator.Repository;

import PrimeNumber.PrimeNumber_Generator.Models.Primes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimesRepository extends JpaRepository<Primes,Integer> {
}
