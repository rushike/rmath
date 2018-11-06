/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extended_number;

/**
 *
 * @author rushi
 * @param <E>
 */
public class Matrix<E extends W> extends W{
    /**
     * Matrix of type E
     */
    private final Object[][] matrix;
    /**
     * Indicates no. of rows in matrix
     */
    private final int m;
    /**
     * Indicates no. of columns in matrix
     */
    private final int n;
    /**
     * 
     */
    private final E e;
  
    public Matrix(E e, int m, int n){
        this.m = m;
        this.n = n;
        this.e = e;
        matrix = e.get_2D_array(m, n);
    } 
    /**
     * initialize matrix buffer to mat of type E
     * @param mat of class type E
     */
    public void ini_matrix(E[][] mat){
        for(int i = 0; i < m; i++){
            System.arraycopy(mat[i], 0, matrix[i], 0, n);
        }
    }
    
    @Override
    public String toString(){
        String s = "Matrix{"+m+"}{"+n+"}\n";
        s += toString(true);
        return s;
    }
    /**
     * To print just matrix elements
     * @param mat boolean variable decides control of toString to print matrix info, 
     *  false calls and returns toString()
     * @return 
     */
    public String toString(boolean mat){
        if(!mat) return toString();
        String s = "";
        for(int i = 0; i < m; i++ ){
            for(int j = 0; j < n; j++){
                E et = (E)matrix[i][j];
                s = s + et  + "   ";
            }s += "\n";
        }s += "\n";
        return s;
    }
    /*
    Getters / Setters
    */
    public int get_m() {
        return m;
    }
    public int get_n() {
        return n;
    }
    public Object get_matrix(){
        return matrix;
    }
    public E get_element(int i, int j){
        return (E)matrix[i][j];
    }
     /**
     * Setter Method 
     * @param i i<sup>th</sup> row of this.matrix 
     * @param j j<sup>th</sup> column of this.matrix
     * @param item element to be initialize
     */
    public void set(int i, int j, E item){
        if( i < m && j <n ) matrix[i][j] = item;
    }
    /**
     * 
     * @param i i<sup>th</sup> row of this.matrix
     * @param items is E[] array to be initialize to ith row 
     */
    public void set_row(int i, E[] items){
        if(i < m) System.arraycopy(items, 0, matrix[i], 0, n);
    }
    /**
     * 
     * @param i i th column of of this.matrix
     * @param items is E[] array to be initialize to ith row
     */
    public void set_column(int i, E[] items){
        if(i < n) {
            for(int j = 0; j < m; j++) matrix[j][i] =  items[j];
        }
    }
    
    /**
     * adds this.matrix with  A.matrix 
     * @param A Matrix  to be  added to this 
     * @return ans added Matrix  
     */
    public Matrix add(Matrix A){
        E p, q;
        Matrix<E> ans = new Matrix<>(e, m, n);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                p = (E)this.matrix[i][j];
                q = (E)A.matrix[i][j];
                ans.matrix[i][j] = p.add(q);
            }
        }return ans;
    }
    @Override
    public W add(W num) {
        Matrix mt = (Matrix)num;
        return super.add(mt); 
    }
    
    /**
     * adds this.matrix with  A.matrix 
     * @param A Matrix  to be  added to this 
     * @return ans added Matrix  
     */
    public Matrix subtract(Matrix A){
        E p, q;
        Matrix<E> ans = new Matrix<>(e,m, n);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                p = (E)this.matrix[i][j];
                q = (E)A.matrix[i][j];
                ans.matrix[i][j] = p.subtract(q);
            }
        }return ans;
    }
    
    @Override
    public W subtract(W num) {
        Matrix mt = (Matrix)num;
        return this.subtract(mt); 
    }
    
    /**
     *
     * @param A int[][] matrix 2D Array
     * @param B int[][] matrix 2D Array
     * @param scale is factor to which matrix should scale , 1 to add and -1 to subtract
     * @return
     */
    public static int[][] add(int [][] A, int [][] B, int scale){
        if(A.length == B.length && A[0].length == B[0].length){
            int m = A.length, n = A[0].length;
            int[][] ans = new int[m][n];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    ans[i][j] = A[i][j] + (scale)*B[i][j];
                }
            } 
            return ans;
        }return new int[0][0];
    }
    /**
     * Matrix Multiplication <br>
     * C = <i>this</i> * A<br>
     * Formula:c_ij = sum{<i>this_ik</i> * a_kj}
     * @param A
     * @return 
     */
    public Matrix multiply(Matrix A){
        if(n != A.m) return new Matrix(e,0, n);
        Matrix<E> ans = new Matrix<>(this.e, m, A.n);
        E first, second, third;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < A.n; j++){
                for(int k = 0; k < n; k++){
                    first = (E)matrix[i][k];
                    second = (E)A.matrix[k][j];
                    third = (E)ans.matrix[i][j];
                    ans.matrix[i][j] = third.add(first.multiply(second));
                }
            }
        }
        return ans;
    }
    @Override
    public W multiply(W num) {
        Matrix mt = (Matrix)num; 
        return this.multiply(mt); 
    }
    /**
     * <p>Scalar Multiplication</p>
     * C = k.<i>this</i> = <i>this</i>.k<br>
     * multiply constant k to every element in <b>this</b> matrix 
     * @param k <b> constant </b>
     * @return 
     */
    public Matrix multiply(int k){
        E et;
        Z x = new Z(k); 
        
        Matrix<E> ans = new Matrix<>(e, m, n);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                et = (E)matrix[i][j];
                ans.matrix[i][j] = et.multiply(x);
            }
        }return ans;
    }
    
    public static int[][] multiply(int[][] A, int[][] B){
        if(A[0].length != B.length) return new int[0][0];
        int[][] ans = new int[A.length][B[0].length];
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B[0].length; j++){
                for(int k = 0; k < B.length; k++){
                    ans[i][j] = A[i][k] + B[k][j];
                }
            }
        }
        return ans;
    }
    
    public  Matrix transpose(){
        Matrix<E> ans = new Matrix<>(e, n, m);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                ans.matrix[j][i] = (E) matrix[i][j];
            }
        }
        return ans;
    }
    
    public static int[][] transpose(int[][] A){
        int[][] ans = new int[A[0].length][A.length];
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[0].length; j++){
                ans[j][i] =  A[i][j];
            }
        }
        return ans;
    }
}
