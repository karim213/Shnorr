import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class Main {
    public static void main(String[] args){

        KeyGenerator keyGenerator = new KeyGenerator();
        Key key = keyGenerator.keyGen();
        System.out.println("g = " + key.getG());
        System.out.println("q = "+ key.getQ());
        System.out.println("p = " + key.getP());



    }
}
