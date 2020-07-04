package extended_number;

import extended_number.helper.math.*;

public class Rt extends W{
    double value = 0.0;

    BigDecimal bval;

    Zt mag;

    int scale;

    int precision = 0;

    /**
     * Constants
     */

    static final long INFLATED = Long.MIN_VALUE;

    public static final BigInteger INFLATED_BIGINT = BigInteger.valueOf(INFLATED);


    /**
     * Flags 
     */

     private int CARRY_FLAG = 0;

     private boolean SM_FLAG = false;

    public Rt(){
        bval = BigDecimal.ZERO;
        mag = new Zt(bval.intVal);
        scale = bval.scale;
        precision = bval.precision;
    }
    public Rt(String num){
        super(num);
        bval = new BigDecimal(num);
        mag = new Zt(bval.intVal);
        scale = bval.scale;
        precision = bval.precision;
    }

    public Rt(Zt mag, int scale, int precision){
        this.mag = mag;
        this.scale = scale;
        this.precision = precision;
    }

    public Rt(double value){
        this.value = value;
        sm = true;
        bval = new BigDecimal(value);
        mag = new Zt(bval.intVal);
        scale = bval.scale;
        precision = bval.precision;
    }

    public Rt add(Rt a){
        return new Rt(this.bval.add(a.bval));
    }
    public static Rt add(Rt a, Rt b){
        return new Rt(a.bval.add(b.bval));
    }
    
    /**
     * Do Subtraction c = this - a
     * @param a
     * @return
     */
    public Rt sub(Rt a){
        return new Rt(this.bval.sub(a.bval));
    }
    /**
     * Do Subtraction c = a - b
     * @param a
     * @param b
     * @return c
     */
    public static Rt sub(Rt a, Rt b){
        return new Rt(a.bval.sub(b.bval));
    }

    public Rt multiply(Rt a){
        return new Rt(this.bval.multiply(a.bval));
    }
    public static Rt multiply(Rt a, Rt b){
        return new Rt(a.bval.multiply(b.bval));
    }
    /**
     * Returns division c = this / a
     * @param a
     * @return c
     */
    public Rt divide(Rt a){
        return new Rt(this.bval.divide(a.bval));
    }
    /**
     * Returns division c = a / b
     * @param a
     * @param b
     * @return
     */
    public static Rt divide(Rt a, Rt b){
        return new Rt(a.bval.divide(b.bval));
    }

    /**
     * Returns division c = this ^ exp
     * @param a
     * @return c
     */
    public Rt pow(int exp){
        return new Rt(this.bval.pow(exp));
    }
    /**
     * Returns division c = a ^ exp
     * @param a
     * @param b
     * @return c
     */
    public static Rt pow(Rt a, int exp){
        return new Rt(a.bval.pow(exp));
    }

        /**
     * Returns division c = this % a
     * @param a
     * @return c
     */
    public Rt rem(Rt a){
        return new Rt(this.bval.remainder(a.bval));
    }
    /**
     * Returns division c = a % b
     * @param a
     * @param b
     * @return c
     */
    public static Rt rem(Rt a, Rt b){
        return new Rt(a.bval.remainder(b.bval));
    }

    
}