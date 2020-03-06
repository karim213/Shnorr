import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = new KeyGenerator();
        Key key = keyGenerator.keyGen();
        System.out.println("kp = " + key.getKp());
        System.out.println("ks  = "+ key.getKs());



    }
}
