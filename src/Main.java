import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        KeyGenerator keyGenerator = new KeyGenerator();
        Key key = keyGenerator.keyGen();
        System.out.println("kp = " + key.getKp());
        System.out.println("ks  = "+ key.getKs());
        String msg = "hello";
        Signature s = keyGenerator.sign(msg , key);
        System.out.println("J'ai signé : " + s);
        System.out.println(keyGenerator.verify(key , s , msg));
        System.out.println("j'ai verifé");






    }
}
