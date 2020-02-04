import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class KeyGenerator {

    public BigInteger expMod(BigInteger g , BigInteger a , BigInteger p){
           return g.modPow(a, p);
    }

    public Key keyGen(){
        BigInteger q , k , p , g;

        q = BigInteger.probablePrime(256 , new SecureRandom());

        do {
            k = new BigInteger(1792, new SecureRandom());
            p = (k.multiply(q)).add(BigInteger.ONE);
        }while (!p.isProbablePrime(1));

        //pour generer un nombre entre 2 et p-2, on genere un nombre entre 0 et p-4 et on lui ajoute 2
        BigInteger upperLimit = new BigInteger(String.valueOf(((p.subtract(BigInteger.ONE).subtract(BigInteger.ONE)).subtract(BigInteger.ONE)).subtract(BigInteger.ONE)));
        do {

            do {
                g = new BigInteger(upperLimit.bitLength(), new SecureRandom());
            } while (g.compareTo(upperLimit) >= 0);
            g.add(BigInteger.ONE.add(BigInteger.ONE));


            System.out.println(g.modPow(q, p));
        }while (expMod(g , q , p) != BigInteger.ONE || expMod(g , k , p) == BigInteger.ONE);

        return new Key(q ,p , g);
    }



}
