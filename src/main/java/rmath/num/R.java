package rmath.num;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;


public class R extends BigDecimal implements Word<R> {
    public static final long serialVersionUID = -8287574255923472291L;
    public R(BigInteger val){
        super(val);
    }
    public R(BigDecimal val){
        super(val.toString());
    }
    public R(String val){
        super(val);
    }
    public R(String val, MathContext arg){
        super(val, arg);
    }
    public R(double val, MathContext arg){
        super(val, arg);
    }
    public R(int val, MathContext arg){
        super(val, arg);
    }
    public R(double val){
        super(val);
    }
    public R(int val){
        super(val);
    }

    @Override
    public R abs() {
        return this;
    }

    @Override
    public R add(R val) {
        return new R(this.add((BigDecimal)val));
    }

    @Override
    public R divide(R val) {
        return new R(this.divide((BigDecimal)val));
    }

    @Override
    public R multiply(R val) {
        return new R(this.multiply((BigDecimal)val));
    }

    @Override
    public R subtract(R val) {
        return new R(this.subtract((BigDecimal)val));
    }
}