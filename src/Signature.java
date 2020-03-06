import java.math.BigInteger;

public class Signature {
    BigInteger c;
    BigInteger a;
    public Signature(BigInteger c, BigInteger a) {
        this.c = c;
        this.a = a;
    }

    public BigInteger getC() {
        return c;
    }

    public void setC(BigInteger c) {
        this.c = c;
    }

    public BigInteger getA() {
        return a;
    }

    public void setA(BigInteger a) {
        this.a = a;
    }

    public String toString(){
        return "(a= "+a+", c = "+c+")";
    }
}
