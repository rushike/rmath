package extended_number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 *
 * @author rushi
 */
public class Basic {

    public Basic(){
        
    }

    public static final String[] __DIGITS = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
                                            "a", "b", "c", "d", "e", "f", "g", "h", "i"};
   

    /** 
     * Prime Number Section
     **/                                        
    public static boolean is_prime(long num){
        int mid_sq = (int)Math.sqrt(num), iter = 2;
        while(iter < mid_sq){
            if(num % iter == 0) return false;
            iter++;
        }
        return true;
    }


    public static ArrayList<Integer> sieve_eratosthenes(int limit){
        boolean[] not_primes = new boolean[limit + 1];
        ArrayList<Integer> prime_list;
        int current_prime = 2;
        not_primes[0] = true; not_primes[1] = true; 
        int mid_sq = (int)Math.sqrt(limit) + 1, iter = 0;
        while(current_prime < mid_sq){
            iter = current_prime;
            while(iter <= limit){
                iter += current_prime;
                if(iter <= limit) not_primes[iter] = true;
            }
            current_prime++;
            while(not_primes[current_prime] == false) break;
        }
        prime_list = new ArrayList<>();
        for(int i = 0; i < not_primes.length; i++){
            if(!not_primes[i]) prime_list.add(i);
        }
        return prime_list;
    }

    public static long largest_mid_prime_factor(long number){
        int mid = (int)Math.sqrt(number);
        ArrayList<Integer> prime_list = Basic.sieve_eratosthenes(mid);
        //System.out.println("Prime List : " + prime_list);
        int sz = prime_list.size(),  in = sz - 1;
        while(in > 0){
            if(number % prime_list.get(in) == 0 ) return prime_list.get(in);
            in--;
        }
        return number;
    }

    public static long largest_prime_factor(long number){
        int mid = (int)Math.sqrt(number);
        ArrayList<Integer> prime_list = Basic.sieve_eratosthenes((int)(number));
        //System.out.println("Prime List f : " + prime_list);
        int sz = prime_list.size(),  in = sz - 1;
        while(in > 0){
            if(number % prime_list.get(in) == 0 ) return prime_list.get(in);
            in--;
        }
        return number;
    }

    public static long prime_counting_upper_bound(long n){
        return (int)(1.25506 * n / Math.log(n));
    }

    public static long nth_prime(int n){
        if(n == 1) return 2;
        int max_len = (int)(n * Math.log(n * Math.log(n))) + 6; 
        ArrayList<Integer> list = sieve_eratosthenes(max_len);
        return list.get(n - 1);
    }
    
    /**
     * Number Section
     */
    public static long largest_factor(long number){
        int mid = (int)Math.sqrt(number);
        int in = mid;
        while(in > 1){
            if(number % in == 0 ) return in;
            in--;
        }
        return number;
    }

    /**
     * a &gt b
     * gcd(a, q)
     * Using Euclid Algorithm
     * a = q * m0 + r0
     * q = r0 * m1 + r1;
     * if r_i = 0 terminate return q = r_(i-1)
     * @param x first number
     * @param y second number
     * @return q
     */
    public static long gcd(long x, long y){
        if(x % y == 0){
            return y;
        }return gcd(y, x % y);
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
        return (a * q) / gcd(a, q);
    }


    public static long smallest_positive_multiple(int ... num){
        long spm = 1;
        for(int n : num){
            spm = lcm(spm, n);
        }return spm;
    }


    /**
     * Series Section
     */
    public static int[] arithmetic_array(int start, int diff, int terms){
        int[] series = new int[terms];
        for (int i = 0; i < terms; i++) {
            series[i] = start + diff * i;
        }return series;
    }

    public static ArrayList<Integer> arithmetic_list(int start, int diff, int terms, ArrayList<Integer> list){
        if(list == null) list = new ArrayList<Integer>(terms);
        int init = 0;
        if(list.size() > 0) init = list.size() - 1;
        for (int i = init; i < init + terms; i++) {
            list.add(start + diff * i);
        }return list;
    }

    public static long sum_series(ArrayList<Integer> series) {
        long sum = 0;
        for(int ele : series){
            sum += ele;
        }return sum;
    }


    /**
     * Utils Section 
     **/

    public static long reverse_to_long(long number) {
        return Long.parseLong(new StringBuilder(to_base(number, 10)).reverse().toString());
    }
    public static String reverse(long number) {
        return new StringBuilder(to_base(number, 10)).reverse().toString();
    }

    public static String to_base(long number, int base) {
        StringBuilder sb = new StringBuilder();
        long numt = number;
        while(numt > 0){
            sb.append(__DIGITS[(int)(numt % base)]);
            numt /= base;
        }
        if(sb.toString().equals("")) return "0";
        return sb.reverse().toString();
    }
    
}
