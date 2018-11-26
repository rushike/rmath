
import extended_number.*;

public class Main{
    public static void main(String[] args) {
        Zt z = new Zt("1656");
        Zt c = new Zt("6");
        Zt y = z.rem(c);
        System.out.println(z);
        System.out.println(y);
        int num = 500000;
        System.out.println("Factorial Length : " + Zt.factorial_length(num, 10));
        System.out.println("Trailing  : " + Zt.factorial_trailing_zeros(num));
        long start = System.currentTimeMillis();
        System.out.println(" After i value :" + c.factorial_concurrent(num, 8, true));
        System.out.println("TIme : " + (System.currentTimeMillis() - start));
        System.out.println("Basic : " + Basic.largest_prime_factor(15625));
    }
}