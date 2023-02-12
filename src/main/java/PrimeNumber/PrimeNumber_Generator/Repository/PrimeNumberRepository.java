package PrimeNumber.PrimeNumber_Generator.Repository;

import PrimeNumber.PrimeNumber_Generator.Models.PrimeNumber;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrimeNumberRepository extends JpaRepository<PrimeNumber,Integer> {


    @Query("SELECT t from PrimeNumber t where t.email=:email")
    List<PrimeNumber> findByEmail(@Param("email") String email);





}
