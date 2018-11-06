/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extended_number;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/**
 *
 * @author rushi
 */
public class R extends W {
    long value;
    
    private final BigDecimal val;
    
    Z xt;
    
    private int scale;
    
    public R(String num) {
        val = new BigDecimal(num);
        xtsc();
    }
    
    R(BigDecimal num) {
        val = num;
        xtsc();
    }
    
    R() {
        val = BigDecimal.ZERO;
        xtsc();
    }
    
    private void xtsc() {
        xt = new Z(val.unscaledValue());
        scale = val.scale();
    }
    
    
    @Override
    public W add(W num) {
        return new  R(val.add(((R)num).val));
    }
    public W add(Z num, boolean test){
        int[] ans = new int[num.length + 1];
        
        return num;
    } 
    
    @Override
    public W additive_inverse() {
        return new R(val.negate());
    }
    
    public int bits(int radix) {
        return 0;
    }
    
    @Override
    public int compareTo(W num){
        return val.compareTo(((R)num).val);
    }
    
    @Override
    public int compare_to(W num) {
        return compareTo(num);
    }
    
    @Override
    public W divide(W num) {
        return new R(val.divide(((R)num).val));
    }
    
    @Override
    public boolean equals(Object obj) {
        Z cmp = (Z)obj;
        return val.equals(cmp.val);
    }
    
    @Override
    public W[] get_1D_array(int m){
        W[] mat = new R[m];
        for(int i = 0; i < m; i++ ){
                mat[i] = new R();
        }return mat;
    }
   
    @Override
     public W[][] get_2D_array(int m, int n){
        W[][] mat = new R[m][n];
        for(int i = 0; i < m; i++ ){
            for(int j = 0; j < n; j++){
                mat[i][j] = new R();
            }
        }return mat;
     }
    
     public Z get_xt(){
        return xt;
    }
    
      /**
     * 
     * @return length for Z in binary system
     */
    public final int length(){
        return 0;
    }
    
    private int length(String num) {
        return 0;
    }
    
    @Override
    public W multiply(W num) {
        return new R(val.multiply(((R)num).val)); 
    }
    
    @Override
    public W power(int exp) {
        return new R(val.pow(exp));
    }
 
    @Override
    public W remainder(W num) {
        return new R(val.remainder(((R)num).val));
    }
    
    @Override
    public W square() {
        return multiply(this);
    }
    
    @Override
    public W subtract(W num) {
        return new R(val.subtract(((R)num).val));
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
    /**
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
    **/    
    private String wrap_bits_int_toString(int num){
        return "NOT_READY";
    }

    
}
