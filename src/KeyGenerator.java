import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class KeyGenerator {

    public BigInteger expMod(BigInteger g , BigInteger a , BigInteger p){
           return g.modPow(a, p);
    }

    public Key keyGen(){
        BigInteger q , k , p , g;


        //generation q \ 256bits random
        q = BigInteger.probablePrime(256 , new SecureRandom());

        assert(q.isProbablePrime(16)):"q n'est pas premier";
        assert(q != BigInteger.ONE):"q est egal a 1";
        System.out.println("q generated...");
        //generation k et p \ p = 1 + kq
        do {
            k = new BigInteger(1792, new SecureRandom());
            p = (k.multiply(q)).add(BigInteger.ONE);
        }while (!p.isProbablePrime(16)); // sur a (1 - 1/2^certainty) = 99.99%

        assert(p.isProbablePrime(16)):"p n'est pas premier";
        System.out.println("k and p generated...");
        //generation g \ g^q inverse modulaire de p
        // U(g^q) + V(p) = 1
        // sqrt_q((1 - V(p))/U) = g
        //note q et p sont premiers
        do{
            g = new BigInteger(512, new SecureRandom()); //g 512 bits random
        } while(g.modPow(q,p) != BigInteger.ONE);
        System.out.println(g.modPow(q,p));
        assert(g.modPow(q,p) == BigInteger.ONE):"g^q n'est pas l'inverse modulaire de p";
        System.out.println("g generated...");




        /*
        //pour generer un nombre entre 2 et p-2, on genere un nombre entre 0 et p-4 et on lui ajoute 2
        BigInteger upperLimit = new BigInteger(String.valueOf(((p.subtract(BigInteger.ONE).subtract(BigInteger.ONE)).subtract(BigInteger.ONE)).subtract(BigInteger.ONE)));
        do {

            do {
                g = new BigInteger(upperLimit.bitLength(), new SecureRandom());
            } while (g.compareTo(upperLimit) >= 0);
            g.add(BigInteger.ONE.add(BigInteger.ONE));


            System.out.println(g.modPow(q, p));
        }while (expMod(g , q , p) != BigInteger.ONE || expMod(g , k , p) == BigInteger.ONE);
        */


        return new Key(q ,p , g);
    }



}
