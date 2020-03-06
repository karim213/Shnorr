import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class KeyGenerator {

    public BigInteger pow(BigInteger g , BigInteger a){

        BigInteger TWO = new BigInteger("2");
        if(a.equals(BigInteger.ONE)){
           return BigInteger.ONE;
        }
        BigInteger x = pow(g, a.divide(TWO));
        if((a.mod(TWO)).equals(BigInteger.ZERO)){
            return x.multiply(x);
        } else {
            return g.multiply(x.multiply(x));
        }
    }

    public BigInteger expMod(BigInteger g, BigInteger k, BigInteger p){
        return g.modPow(k, p);
    }


    public Key keyGen() throws NoSuchAlgorithmException {
        BigInteger q , k , p , g, h;


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


        //pour generer un nombre entre 2 et p-2, on genere un nombre entre 0 et p-4 et on lui ajoute 2


        BigInteger upperLimit = new BigInteger(String.valueOf(((p.subtract(BigInteger.ONE).subtract(BigInteger.ONE)).subtract(BigInteger.ONE)).subtract(BigInteger.ONE)));
        do {

            do {
                h = new BigInteger(p.bitLength() , new SecureRandom());
            } while (h.compareTo(upperLimit) >= 0);
            h.add(BigInteger.ONE.add(BigInteger.ONE));
        }while ( expMod(h , k , p) == BigInteger.ONE);

        System.out.println("h generated...");

        g = h;


        return new Key(q ,p , g);
    }


    public Signature sign(String message , Key key) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //pour generer un nombre entre 2 et q-2, on genere un nombre entre 0 et q-4 et on lui ajoute 2
        BigInteger r , R;
        String M;
        BigInteger upperLimit = new BigInteger(String.valueOf(((key.getQ().subtract(new BigInteger("4"))))));

            do {
                r = new BigInteger(key.getQ().bitLength() , new SecureRandom());
            } while (r.compareTo(upperLimit) >= 0);
            r.add(new BigInteger("2"));
         R = expMod(key.getG() , r , key.getP());

         M = R.toString() + ""+message;
        byte[] binary =  M.getBytes("UTF-8");
        byte[] hash = hash(binary);

        StringBuilder huge_num = new StringBuilder();

        String tmp = "";
        for (byte b : hash){
            tmp = tmp+Byte.toUnsignedInt(b);
            huge_num.append(new String(String.valueOf(new Integer(Byte.toUnsignedInt(b)))));
        }

        System.out.println("====");
        System.out.println(huge_num);

        BigInteger c = expMod(new BigInteger(tmp.toString()), BigInteger.ONE, key.getQ() );

        BigInteger a = expMod((r.subtract(c.multiply(key.getKs()))), BigInteger.ONE, key.getQ());


        return new Signature(c, a);


    }

    public boolean verify(Key key, Signature signature, String message) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println("debut verif");
        BigInteger a = signature.getA();
        BigInteger c = signature.getC();
        BigInteger g = key.getG();
        BigInteger h = key.getKp();
        BigInteger R0 = expMod((pow(g, a).multiply(pow(h, c))), BigInteger.ONE, key.getP());


        String M = R0.toString() + ""+message;
        byte[] binary =  M.getBytes("UTF-8");
        byte[] hash = hash(binary);

        String huge_num = new String(hash);
        BigInteger hash_Big = new BigInteger(huge_num);
        System.out.println("here23");
        return c.equals(Integer.parseInt(expMod(hash_Big, BigInteger.ONE, key.getQ()).toString()));
    }

    public byte[] hash(byte[] message) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(message);
        return hash;

    }



}
