import java.math.BigInteger;
import java.security.SecureRandom;

public class Main {
    public static void main(String[] args){

        BigInteger q , k , p;

        q = BigInteger.probablePrime(256 , new SecureRandom());
        System.out.println("q = "+ q);

        do {
            k = BigInteger.probablePrime(1792, new SecureRandom());
            System.out.println("k = " + k);

            p = (k.multiply(q)).add(BigInteger.ONE);
            System.out.println("p = " + p);

        }while (!p.isProbablePrime(1));

        System.out.println(p.isProbablePrime(1));


    }
}
