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
public  class W extends Symbol implements Comparable<W>{
    
    public static final int FORMAT_NOT_SPECIFIED = 0x6ea;

    public static final char[] _DIGIT_ = new char[]{'0', '1', '2', '3', '4','5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F'}; 


    public W(){
          
    }

    public W(String num){
        super(num);
    }

    public boolean is_word(){
        boolean check;
        int base = _DIGIT_.length;
        for(int i = 0; i < sym.length(); i++){
            check = false;
            for(int j = 0; j < base; j++){
                if(sym.charAt(i) == _DIGIT_[j]) check = true; 
            }if(!check) return false;
        }return true;
    }
    
    /**
     *<p> Adds, this + num </p> 
     * @param num
     * @return sum
     */
    public W add(W num){
        return num;
    }
    
    public W additive_inverse() {
         return this;
     }
    
    
    
    public int compare_to(W num) {
        return 0;
    }
    
    /**
     * <p>Divides, this/num
     * @param num
     * @return 
     */
     public W divide(W num){
        return num;
     }
     
       /**
     * returns 1D array of W of size m , 
     * also it instantiate the array
     * @param m
     * @param n
     * @return 
     */ 
    public W[] get_1D_array(int m){
        W[] mat = new W[m];
        for(int i = 0; i < m; i++ ){
                mat[i] = new W();
        }return mat;
    }
    /**
     * returns 2D array of W of size m &#x2613 n, 
     * also it instantiate the array
     * @param m
     * @param n
     * @return 
     */ 
     public W[][] get_2D_array(int m, int n){
        W[][] mat = new W[m][n];
        for(int i = 0; i < m; i++ ){
            for(int j = 0; j < n; j++){
                mat[i][j] = new W();
            }
        }return mat;
     }
    
     
    /**
     * <p>Multiplies, this * num  </p>
     * @param num
     * @return 
     */
     public W multiply(W num){
        return num;
     }
     
     public W power(int exp) {
         return new W();
     }
          
     public W remainder(W num) {
         return num;
     }
     
     public W square() {
         return this;
     }
     
     /**
     * <p> Subtracts , this - num </p>
     * @param num
     * @return difference
     */
     public W subtract(W num){
        return num;
     }

    @Override
    public int compareTo(W o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
    @Override
     public String toString(){
         return sym.toString();
        // return "Word.FORMAT_NOT_SPECIFIED : " + FORMAT_NOT_SPECIFIED;
     } 
}
