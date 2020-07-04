/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extended_number;

/**
 *
 * @author rushi
 */
public class Q extends W{
    public static final long serialVersionUID = 1L;
    /**
     * Fraction representation
     */
    public Zt x;
    public Zt y;
    
    public Q(){
        this.x = new Zt() ;
        this.y = new Zt(1L);
    }
    public Q(long x, long y){
        this.x = new Zt(x);
        this.y = new Zt(y);
    }
    public Q(Zt x, Zt y) {
       this.x = x;
       this.y = y;
    }

    public Q(W x, W y){
        this.x = (Zt)x;
        this.y = (Zt)y;
    }

    public Q add(Q frac){
        Q ans;
        if(frac.y.equals(this.y)) {
             ans = new Q(x.add(frac.x),y);
            //  System.out.println("ans L Q : " + ans);
             ans = ans.simple_fraction();
        }
        else{
            Zt g = x.gcd(y);
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
    public W subtract(W num) {
        return this.subtract((Q)num); 
    }
    
    public Q multiply(Q frac){
        return new Q(x.multiply(frac.x), y.multiply(frac.y));
    }
   
    @Override
    public W multiply(W num) {
        Q q = (Q)num;
        return this.multiply(q); 
    }
    
    public Q divide(Q frac){
        return multiply(new Q(frac.y, frac.x));
    }
    @Override
    public W divide(W num) {
        return this.divide((Q)num);
    }
    

    @Override
    public W[] get_1D_array(int m){
        W[] mat = new Q[m];
        for(int i = 0; i < m; i++ ){
                mat[i] = new Q();
        }return mat;
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
    
    /**
     * If a / b = 4 / 12 = 1 / 3
     * @return Fraction simplified
     */
    public Q simple_fraction() {
        /**
         * Swap if a > b
         */
        if(x.compare_to(y) == 1) {
            Zt cmp = x;
            y = x;
            x = cmp;
        }
        Zt g = x.gcd(y);
        return new Q(x.divide(g), y.divide(g));
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean equals(Object frac){
        Q a = (Q)frac;
        if(a.x == this.x && a.y == this.y) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString(){
        return "[{"+this.x+"}"+", {"+this.y+"}]";
    }
    
}
