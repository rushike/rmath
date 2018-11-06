/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extended_number;

import number_theory.Basic;

/**
 *
 * @author rushi
 */
public class Q extends W{
    /**
     * Fraction representation
     */
    private Z x;
    private Z y;
    
    public Q(){
        this.x = new Z() ;
        this.y = new Z(1L);
    }
    public Q(long x, long y){
        this.x = new Z(x);
        this.y = new Z(y);
    }
    public Q(W x, W y) {
       this.x = (Z)x;
       this.y = (Z)y;
    }
    
    public Q(Z x, Z y) {
       this.x = x;
       this.y = y;
   }
    public Q add(Q frac){
        Q ans;
        if(frac.y.equals(this.y)) {
             ans = new Q(x.add(frac.x),y);
             ans = ans.simple_fraction();
        }
        else{
            Z g = (Z)Basic.gcd(x, y);
            ans = new Q((x.multiply(frac.y.divide(g)).add(frac.x.multiply(y.divide(g)))),y.multiply(frac.y).divide(g));
        }return ans;
    }
    @Override
    public W add(W num){
        Q q = (Q)num;
        return this.add(q);
    }
    public Q subtract(Q frac){
        return add(new Q((x.additive_inverse()), y ));
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
  
    public Q divide(Q frac){
        return multiply(new Q(frac.y, frac.x));
    }
    @Override
    public W divide(W num) {
        return this.divide((Q)num);
    }
    
     @Override
    public boolean equals(Object frac){
        Q a = (Q)frac;
        if(a.x == this.x && a.y == this.y) return true;
        return false;
    }
    
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public W[] get_1D_array(int m){
        W[] mat = new Q[m];
        for(int i = 0; i < m; i++ ){
                mat[i] = new Q();
        }return mat;
    }
    
    /**
     * a &gt b
     * gcd(a, q)
     * Using Euclid Algorithm
     * a = q * m + r
     * q = r * m1 + r1;
     * if r_i = 0 terminate return q
     * @param a first number
     * @param q second number
     * @return q
     */
    public long gcd(long a, long q ){
        if(a % q == 0){
            return q;
        }return gcd(q, a % q);
    }
    
    @Override
    public W[][] get_2D_array(int m, int n){
        W[][] mat = new Q[m][n];
        for(int i = 0; i < m; i++ ){
            for(int j = 0; j < n; j++){
                mat[i][j] = new Q();
            }
        }return mat;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    public Q multiply(Q frac){
        return new Q(x.multiply(frac.x), y.multiply(frac.y));
    }
   
    @Override
    public W multiply(W num) {
        Q q = (Q)num;
        return this.multiply(q); 
    }
    
    
    /**
     * If a / b = 4 / 12 = 1 / 3
     * @return Fraction simplified
     */
    public Q simple_fraction() {
        /**
         * Swap if a > b
         */
        if(x.compare_to(y) == 1) {
            Z cmp = x;
            y = x;
            x = cmp;
        }
        W g = Basic.gcd(x, y);
        return new Q(x.divide(g), y.divide(g));
    }
    
    @Override
    public W subtract(W num) {
        return this.subtract((Q)num); 
    }
    
    
    @Override
    public String toString(){
        return "[{"+this.x+"}"+", {"+this.y+"}]";
    }
    
}
/*Getters and Setters
 protected long getX() {
        return x;
    }
    protected long getY() {
        return y;
    }
    protected void setX(int x) {
        this.x = x;
    }
    protected void setY(int y) {
        this.y = y;
    }
*/