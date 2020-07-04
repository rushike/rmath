/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extended_number;

import extended_number.helper.math.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author rushi
 */
public class Z extends W {
    long value;
    
    BigInteger val;
    /**
     * Represents sign of integer, value of signum() function<br>
     *               _
     *              |  1 if x > 0
     * signum(x) = -|  0 if x = 0
     *              |_ -1 if x < 0
     */            
     int signf = 0;
    /**
     * Represents number as int array  in <b>big-endian form</>, here number is
     * represented as whole. For e.g. if 24 is to represented int array of length 2
     * and element can hold 4 bit of information. 
     * 24 in arr = {1,8}, 
     * 24 = 18<br>
     * xt is basically number representation in base 2**32
     */
    int[] xt = new int[0];
     
    /**
     * Stores bit_length of number in binary system, 
     * e.g. 16 length is 5
     */
    int length = 0;
    
    /**
     * Indicates radix of number, default 10 
     */
    int radix = 10;
    
    /**
     * WRAP is use to get long unsigned integer value
     */
    public static final long WRAP = 0xffffffffL;
    
    @Deprecated
    public long wrap = WRAP;
    
    /**
     * WRAP_BITS are no. of bits of number that can WRAP to long value
     */
    public static final int WRAP_BITS = 32;
    
    @Deprecated
    public long wrap_bits = WRAP_BITS;
    /**
     * @since  INcomplete definition 
     * INCR holds the number of bits that the current wrap in radix 10  
     */
    public int[] INCR = {10};
    
    /**
     * Zero representation in Z 
     */
    public static Z ZERO = new Z("0");
    
    /**
     * ONE representation of ONE - Unity
     */
    public static Z ONE = new Z("1");
    
    /**
    public Z(int[] xt, int signf) {
        this.xt = xt;
        this.length = length();
        this.signf = signf;
    }
    
    public Z(String num, int radix){
       this.radix = radix;
       char[] di = num.toCharArray(); // String num to char digit array, 
       int itr = 0, len = 0, sign = 1;
       if(di.length == 0) {
          throw new NumberFormatException("NOT_IN_SPECIFIC_RADIX : " + radix + "Index : 0" );
       }
       if(di[0] == '+' || di[0] == '-') itr = 1;
       for(int i = itr; i < di.length; i++, len++) {
            if(Character.digit(di[i], radix) == -1) throw new NumberFormatException("NOT_IN_SPECIFIC_RADIX : " + radix + "Index : " + i);
       }
       if(len == num.length() - 1) throw new NumberFormatException("NOT_IN_SPECIFIC_RADIX : " + radix + "Index : 0" );
       
       while (itr < len && Character.digit(di[itr], radix) == 0) itr++;
       
       if( itr == len ) {
           signf = 0;
           xt = ZERO.xt;
           length = length();
           return ;
       }
       signf = sign;
       length = len - itr;
       
       
       
    }*/
    
    public Z(String num) {
        val = new BigInteger(num);
        xt = to_int_array();
        signf = val.signum();
        length = xt.length;
    }
    Z(BigInteger val) {
        this.val = val;
        xt = to_int_array();
        signf = val.signum();
        length = xt.length;
    }
    public Z() {
        this.value = 0;
        this.val = ZERO.val;
        xt = to_int_array();
        signf = val.signum();
        length = xt.length;
    }
    public Z(long value) {
        this.value = value;
        val = new BigInteger(Long.toString(value));
        xt = to_int_array();
        signf = val.signum();
        length = xt.length;
    }
    public Z(int[] xt, int signf) {
        this.xt = xt;
        val = new BigInteger(1, to_byte_array(xt));
        this.signf = signf;
        value = 0;
    }
  
    @Override
    public W add(W num) {
        Z q = (Z)num;
        return new  Z(val.add(((Z)num).val));
    }
    public W add(Z num, boolean test){
        int[] ans = new int[num.length + 1];
        
        return num;
    } 
    

    public int[] add(int[] A, int[] B){
        if(A.length < B.length ){
            int[] swap = A;
            A = B;
            B = swap;
        }
        int[] ans = new  int[A.length + 1];
        long sum = 0L;
        int i;
        for(i = A.length; i > B.length; i--) {
            // A + B + carry
            sum = (A[i] & WRAP) + (B[i] & WRAP) + (sum >>>WRAP_BITS);
            ans[i] = (int)sum;
        }do{
            sum = (A[i] & WRAP) + (sum >>> WRAP_BITS);
            ans[i--] = (int)sum;
        }while(i >= 0);
        return ans;
    }
    
     @Override
    public W additive_inverse() {
        return new Z(val.negate());
    }
    
    public int bits(int radix) {
        return 0;
    }
    
    @Override
    public int compareTo(W num){
        return val.compareTo(((Z)num).val);
    }
    
    @Override
    public int compare_to(W num) {
        return compareTo(num);
    }
    
    /**
     * //default number system decimal base 10
     * Assumes String to be positive integer, pure base 10 number
     * @param num
     * @return 
     */
    public int[] convert_in(String num) {
        final int mulx  = 1000000000;
        final int mulx_bits = 9;
        final int len = length(num.length(), 10) + 1;
        int[] arr = new  int[len];
        System.out.println("len : " + len);
        int i = 0, numt, bits = len * WRAP_BITS;
        
        //Pre - Proccessing
        int mod_len  = num.length() % mulx_bits;
        numt = Integer.parseInt(num.substring(0, mod_len));
        offset(arr, numt);
        scale(arr, mulx, bits);
        i = mod_len;
        while((i = i + mulx_bits) < num.length()) {
            System.out.println("S1 :" + num.substring(i - mulx_bits , i));
            numt = Integer.parseInt(num.substring(i - mulx_bits , i));
            System.out.println("numt : " + numt);
            offset(arr, numt);
            System.out.println("arro : " + Arrays.toString(arr));
            scale(arr, mulx, bits);
            System.out.println("arrs : " + Arrays.toString(arr));
        }
        return arr;
    }
    
    public String convert_to() {
        int[] dividend = to_int_array();
        int divisor = 1000000000;
        int iter = 0;
        StringBuilder sb_xt = new  StringBuilder();
        while(iter++ < length) {
            sb_xt.insert(0, scale_invert(dividend, divisor, 0));
        }if(dividend[dividend.length - 1] != 0) sb_xt.insert(0, dividend[dividend.length - 1]);
        return sb_xt.toString();
    }
    
    @Override
    public W divide(W num) {
        return new Z(val.divide(((Z)num).val));
    }
    
    public W divide(int k) {
        int[] numb = to_int_array(); 
        scale_invert(numb, k ,0);
        return new Z(new BigInteger(1, to_byte_array(numb)));  
    }
    
    @Override
    public boolean equals(Object obj) {
        Z cmp = (Z)obj;
        return val.equals(cmp.val);
    }
    
    public Z factorial(int num) {
        return new Z(new BigInteger(1, to_byte_array(factorial(num, true))));
    }
    
    public  int[] factorial(int num, boolean internal) {
        
        int len = factorial_length(num, 2); // factorial length if in specific radix = 2
        
        int int_len = len / WRAP_BITS + 2; // num length allocated in int array, + 1
        
        int[] fact = new int[int_len]; //   allocating space for array
        
        fact[fact.length - 1] = 1; //   assigning unit value to num fact 

        for(int i = 2; i <= num; i++) {
            len = factorial_length(i, 2);
            scale(fact, i, len);
        }return fact;
    }
    
    public Z factorial_op(int num){
        return new Z(new BigInteger(1, to_byte_array(factorial_op(num, true))));
    }

    public int[] factorial_op(int num, boolean test){
        int len = factorial_length(num, 2); // factorial length if in specific radix = 2
        
        int int_len = len / WRAP_BITS + 2; // num length allocated in int array, + 1
        
        int[] fact = new int[int_len]; //   allocating space for array
        
        fact[fact.length - 1] = 1; //   assigning unit value to num fact
        
        int start_offset, end_offset;
        for(int i = 2; i <= num; i++){
            end_offset = int_len - factorial_length(i, 2) / WRAP_BITS;
            start_offset = int_len - factorial_trailing_zeros(i, 2) / WRAP_BITS;
            scale_in_between(fact, start_offset, end_offset, i, 0);
        }
        return fact;
    }

    public static int factorial_trailing_zeros(int num){
        return factorial_trailing_zeros(num, 10);
    }

    public static int factorial_trailing_zeros(int num, int base){
        base = (int)Basic.largest_prime_factor(base);
        int n = (int)(Math.log(num) / Math.log(base));
        int sum = 0, mul = base;
        for(int i = 0; i < n; i++){
            sum  += num / mul;
            mul *= base;
        }
        return sum;
    }

    /**
     * finding length of num! by using Stirling Approximation
     * n! ~= root2(2*pi*n)*(n/e)^n
     * e = 2.73... natural logarithm base
     * @param num Integer number whose factorial needed
     * @param radix base in which factorial is represented
     * @return length of factorial
     */
    public static int factorial_length(int num, int radix) {
        return (int)((Math.log(2 * Math.PI * num) / 2 + num * Math.log(num) - num ) / Math.log(radix)) + 1;
    }
    
    public Z gcd(Z a) {
        return gcd(this, a);
    }
    public Z gcd(Z a, Z b) {
        Z rem = (Z)a.remainder(b);
        if(rem.equals(Z.ZERO))
            return b;
        return gcd(b, rem);
    }
    
    @Override
    public W[] get_1D_array(int m){
        W[] mat = new Z[m];
        for(int i = 0; i < m; i++ ){
                mat[i] = new Z(0);
        }return mat;
    }
    
    @Override
    public W[][] get_2D_array(int m, int n){
        W[][] mat = new Z[m][n];
        for(int i = 0; i < m; i++ ){
            for(int j = 0; j < n; j++){
                mat[i][j] = new Z();
            }
        }return mat;
    }
    
    public int[] get_xt(){
        return xt;
    }
    
      /**
     * 
     * @return length for Z in binary system
     */
    public final int length(){
        return (int)(length / Math.log(10) * Math.log(WRAP_BITS));
    }
    
    public final int length(int radix) {
        return (int)(length / Math.log(radix) * Math.log(WRAP_BITS));
    }
    public final int length(int len, int radix) {
        return (int)(length / Math.log(WRAP+1) * Math.log(radix) + 1);
    }
    public int length(String num) {
        return 0;
    }
    
    @Override
    public W multiply(W num) {
        return new Z(val.multiply(((Z)num).val)); 
    }
    
     /**
     * Overflow negalacted
     * @param num
     * @return 
     */
    public W offset(int[] arr, long num) {
        int i = arr.length;
        long carry =  (arr[--i] & WRAP) + num;
        arr[i] = (int)carry;
        carry = carry >>> WRAP_BITS;
        while( i > 0) {
            carry = carry + (arr[--i] & WRAP);
            arr[i] = (int)carry;
            carry = carry >>> WRAP_BITS;
        }return new Z(arr, 1);
    }
    
    @Override
    public W power(int exp) {
        return new Z(val.pow(exp));
    }
 
    @Override
    public W remainder(W num) {
        return new Z(val.remainder(((Z)num).val));
    }
    /**
     * Scales / multiplies num integer k times upto bit length bits from LSB 
     * @param arr number representation in 2<sup>32</sup> base 
     * @param k scaling factor
     * @param bits length to which to scale, if number was in binary
     * @return scaled number
     */  
    public int[] scale(int[] arr, int k, int bits) {
        int len = arr.length - bits / WRAP_BITS ;
        long scl = 0;
        for(int i = arr.length; i >= len;) {
            scl = (arr[--i] & WRAP) * k + (scl >>> WRAP_BITS);
            arr[i] = (int)(scl & WRAP) ;           
        }return arr;
    }
    /**
     * It will used to optimese the performance,<br>
     * If we result is n length array, but start with 1 unit array gradually grow in size,<br>
     * Also consist zeros at start ... <b>e.g.</b> <i>factorials</i>, then only scale the relavant part<br>
     * @param arr
     * @param start_offset LSB start index, in Big-Endian nearer to arr.length
     * @param end_offset MSB start index, in Big-Endian nearer to 0
     * @param k constant to scale
     * @param bits bits involed in int, base of multiplication, e.g decimal, hexadecimal etc;
     * @return
     */
    public int[] scale_in_between(int[] arr, int start_offset, int end_offset, int k, int bits){
        // int len = arr.length - start_offset + end_offset - bits / WRAP_BITS;
        long scl = 0L;
        for(int i = start_offset; i > end_offset;){
            scl = (arr[--i] & WRAP) * k + (scl >>> WRAP_BITS);
            // System.out.println("scl : " + scl);
            arr[i] = (int)(scl & WRAP);
        }if((scl >>> WRAP_BITS) > 0 && end_offset > 0){
            arr[end_offset] = (int)(scl >>> WRAP_BITS);
        }
        return arr;
    }
    /**
     * Its is reverse or inverse function to scale<br>
     * If n is scale and de_scale t times
     * n = scale(scale_invert(n, t), t)   
     * @param arr 2 ^ WRAP base Integer representation of n
     * @param k scaling factor
     * @param bits 
     * @return 
     */
    public int scale_invert(int[] arr, long k, int bits) {
        int len = arr.length;
        int i = 0;
        long divd = arr[i] & WRAP;
        while(i < len) {
            //System.out.println("i: "+i+"  divd: "+ divd);
            //Making divd > k
            while(divd < k) {
              arr[i++] = 0;
              if(i == len) return (int)divd;
              divd = divd * (WRAP + 1) + (arr[i] & WRAP);
              //System.out.println("W::i: "+i+"  divd: "+ divd);
            }
            arr[i] =(int)(divd / k );
            //System.out.println("OW::i: "+i+"  divd: "+ divd+"  arri: "+arr[i]);
            divd = divd - (arr[i++] & WRAP) * k;
            if(i < len) divd = divd * (WRAP + 1)+ (arr[i] & WRAP);
        }return (int)divd;
    }
    
   /**
    * security 
     * @param wrap_bits
    */ 
   protected void set_secure_wrap(int wrap_bits){
       this.wrap_bits = wrap_bits;
       this.wrap = (long)Math.pow(2, WRAP_BITS) - 1; 
   }
    
    @Override
    public W square() {
        return multiply(this);
    }
    
    @Override
    public W subtract(W num) {
        return new Z(val.subtract(((Z)num).val));
    }


    /**
     * Trims a  arr array from start for value matching di
     * @param arr array to be trim
     * @param di byte digit
     * @return trimed array
     */
    public byte[] trim_start(byte[] arr, int di) {
        int check = 0;
        for(int i= 0; i < arr.length && arr[check] == di; i++, check++);
        if(check > 0) return Arrays.copyOfRange(arr, check, arr.length);
        return arr;
    }
     
    @Override
    public String toString() {
        return val.toString();
    }
    
    public String toString(boolean test) {
        StringBuilder s = new StringBuilder(Integer.toString(xt[0]));
        int i = 1;
        while(i < length ) {
            s = s.append(wrap_bits_int_toString(xt[i++]));
        }return s.toString();
    }
    
    public byte[] to_byte_array() {
        return val.toByteArray();
    }
    
    public byte[] to_byte_array(int[] art) {
        byte[] arr = new byte[art.length * 4];
        int i, j = arr.length;
        wrap = 0xff;
        wrap_bits = 8;
        long intr;
        for(i = art.length; i > 0; ) {
            intr = art[--i];
            arr[--j] = (byte)(intr & wrap);
            intr = intr >>> wrap_bits;
            while(j % 4 != 0 ) {
                arr[--j] = (byte)(intr & wrap);
                intr = intr >>> wrap_bits;
            }
        }
        return trim_start(arr, 0);
    }
    
    public final int[] to_int_array() {
        byte[] bys = val.toByteArray();
        double len = bys.length * 1.0 / 4;
        int j = len - Math.floor(len) > 0 ? (int)Math.ceil(len) : (int)len;
        int[] arr = new int[j];
        wrap = 0xffL;
        int i, k;
        long xd;
        for(i = bys.length ; i > 0 && j > 0; ) {
            xd = 1; k = 0;
            arr[--j] = (int)(bys[--i] & wrap);
            while(i > 0 && k++ < 3) {
                xd = xd * 256;
                arr[j] = (int)((bys[--i] & wrap) * xd) + arr[j];
            }
        }return arr;
    }
        
    public String wrap_bits_int_toString(int num){
        return "NOT_READY";
    }
}
