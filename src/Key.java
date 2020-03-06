import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.SignatureDSA;
import jdk.nashorn.internal.ir.debug.ClassHistogramElement;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Key {
    private BigInteger q;
    private BigInteger p;
    private BigInteger g;
    private BigInteger kp;
    private BigInteger ks;

    public Key(BigInteger q, BigInteger p, BigInteger g) throws NoSuchAlgorithmException {
        BigInteger upperLimit = new BigInteger(String.valueOf(((q.subtract(new BigInteger("4"))))));
        do {
            ks = new BigInteger(p.bitLength(), new SecureRandom());
            System.out.println(ks);
        } while (upperLimit.compareTo(ks) == 1 && ks.compareTo(new BigInteger("1")) == 1);
        ks.add(new BigInteger("2"));

        kp = (new KeyGenerator()).expMod(g, ks, p);

        String text = "hello";
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
    }
    public BigInteger getKp() {
        return kp;
    }

    public BigInteger getKs() {
        return ks;
    }
}
