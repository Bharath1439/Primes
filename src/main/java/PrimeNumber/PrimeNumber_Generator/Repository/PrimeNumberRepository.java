package PrimeNumber.PrimeNumber_Generator.Repository;

import PrimeNumber.PrimeNumber_Generator.Models.PrimeNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrimeNumberRepository extends JpaRepository<PrimeNumber,Integer> {


    List<PrimeNumber> findByEmail(@Param("email") String email);
}
