import java.math.BigInteger;

public class Key {
    private BigInteger q;
    private BigInteger p;
    private BigInteger g;

    public Key(BigInteger q, BigInteger p, BigInteger g) {
        this.q = q;
        this.p = p;
        this.g = g;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getG() {
        return g;
    }

    public void setG(BigInteger g) {
        this.g = g;
    }
}
