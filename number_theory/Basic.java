/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package number_theory;

import extended_number.W;
import extended_number.Z;

/**
 *
 * @author rushi
 */
public class Basic {
    /**
     * a &gt b
     * gcd(a, q)
     * Using Euclid Algorithm
     * a = q * m0 + r0
     * q = r0 * m1 + r1;
     * if r_i = 0 terminate return q = r_(i-1)
     * @param a first number
     * @param q second number
     * @return q
     */
    public static long gcd(long a, long q){
        if(a % q == 0){
            return q;
        }return gcd(q, a % q);
    }
    
    public static W gcd(W a, W b) {
        W rem = a.remainder(b);
        if(rem.equals(Z.ZERO))
            return b;
        return gcd(b, rem);
    }
    /**
     * Gives lcm(Longest Common Multiplier) lcm(a,q)
     * Using formula lcm(a, q) = (a * q) / gcd(a, q)
     * Here a > q
     * @param a first number 
     * @param q second number
     * @return lcm
     */
    public static long lcm(long a, long q){
        swap(a, q);
        return (a * q) / gcd(a, q);
    }
    public static void  swap(long a, long q){
        if(a < q ){
            a = a + q;
            a = a - q;
            q = a - q;
        }
    }
    
}
