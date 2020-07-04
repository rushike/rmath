/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extended_number;

import extended_number.helper.math.*;
import java.util.Arrays;

/**
 *
 * @author rushi
 */
public class R extends W {
    
    double value;
    
    public BigDecimal val;
    
    Zt xt;
    
    public int scale;
    
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
    
    public void xtsc() {
        xt = new Zt(val.unscaledValue());
        scale = val.scale();
    }
    
    
    @Override
    public W add(W num) {
        return new  R(val.add(((R)num).val));
    }
    public W add(Zt num, boolean test){
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
        Zt cmp = (Zt)obj;
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
    
     public Zt get_xt(){
        return xt;
    }
    
      /**
     * 
     * @return length for Z in binary system
     */
    public final int length(){
        return 0;
    }
    
    public int length(String num) {
        return 0;
    }
    
    @Override
    public W multiply(W num) {
        return new R(val.multiply(((R)num).val)); 
    }
    
    @Override
    public W pow(int exp) {
        return new R(val.pow(exp));
    }
 
    @Override
    public W rem(W num) {
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
        
    public String wrap_bits_int_toString(int num){
        return "NOT_READY";
    }

    
}
