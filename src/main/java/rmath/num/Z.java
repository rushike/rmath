package rmath.num;

import java.math.BigInteger;

public class Z extends BigInteger implements Word<Z> {
    public static final long serialVersionUID = -8287574255923472291L;
    public Z(byte[] val){
        super(val);
    }
    public Z(BigInteger val){
        super(val.toString());
    }
    public Z(String val){
        super(val);
    }
    public Z(String val, int radix){
        super(val, radix);
    }

    @Override
    public Z abs() {
        return this;
    }

    @Override
    public Z add(Z val) {
        return new Z(this.add((BigInteger)val));
    }

    @Override
    public Z divide(Z val) {
        return new Z(this.divide((BigInteger)val));
    }

    @Override
    public Z multiply(Z val) {
        return new Z(this.multiply((BigInteger)val));
    }

    @Override
    public Z subtract(Z val) {
        return new Z(this.subtract((BigInteger)val));
    }
}