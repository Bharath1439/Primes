package PrimeNumber.PrimeNumber_Generator.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prime")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Primes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int primenumber;

    public int getPrimenumber() {
        return primenumber;
    }

    public void setPrimenumber(int primenumber) {
        this.primenumber = primenumber;
    }

    public PrimeNumber getPrimeNumber() {
        return primeNumber;
    }

    public void setPrimeNumber(PrimeNumber primeNumber) {
        this.primeNumber = primeNumber;
    }

    @ManyToOne
    @JoinColumn
    private PrimeNumber primeNumber;

}
