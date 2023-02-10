package PrimeNumber.PrimeNumber_Generator.Models;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="PrimeNumbers")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PrimeNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    @CreationTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdTime;

    @Column(nullable = false)
    private int  min_Range;

    @Column(nullable = false)
    private int max_Range;

    private String timeElapsed;

    @Column(nullable = false)
    private String algorithm;

    private int primeNumberCount;


    @OneToMany(mappedBy ="primeNumber",cascade = CascadeType.ALL)
    private List<Primes> primesList;

}
