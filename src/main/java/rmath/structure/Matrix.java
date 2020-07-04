package rmath.structure;

import java.math.BigDecimal;

import rmath.helpers.display;
import rmath.num.R;
import rmath.num.Word;
import rmath.num.Z;

public class Matrix<E extends Word<E>> implements Word<Matrix<E>> {
    public static final long serialVersionUID = 1L;
    /**
     * Matrix of type E
     */
    @SuppressWarnings("rawtypes")
    public final Word[][] matrix;
    /**
     * Indicates no. of rows in matrix
     */
    public final int m;
    /**
     * Indicates no. of columns in matrix
     */
    public final int n;

    // public final Class<E> eClass;

    public Matrix(int m, int n){
        this.m = m;
        this.n = n;
        this.matrix = new Word[m][n];
        this.zero_matrix();
    }
    public Matrix(E[][] mat){
        this.m = mat.length;
        this.n = mat[0].length;
        this.matrix = new Word[m][n];
        this.ini_matrix(mat);
    }

    public Matrix(Number[][] mat){
        this.m = mat.length;
        this.n = mat[0].length;
        this.matrix = new Word[m][n];
        this.number_matrix(mat);
    }

    public void number_matrix(Number[][] mat){
        Number base_el = mat[0][0];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(base_el instanceof Integer || base_el instanceof Long)
                    this.matrix[i][j] = new Z(mat[i][j].toString());
                else  
                    this.matrix[i][j] = new R(mat[i][j].toString());
            }
        }
    }

    public void zero_matrix(){
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){                
                this.matrix[i][j] = new R("0");
            }
        }
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

    @SuppressWarnings("unchecked")
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
     * Operators 
     */
    /**
     * adds this.matrix with  A.matrix 
     * @param A Matrix  to be  added to this 
     * @return ans added Matrix  
     */
    @SuppressWarnings("unchecked")
    public Matrix<E> add(Matrix<E> A){
        E p, q;
        Matrix<E> ans = new Matrix<>(m, n);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                p = (E)this.matrix[i][j];
                q = (E)A.matrix[i][j];
                ans.matrix[i][j] = (E)p.add(q);
            }
        }return ans;
    }

    /**
     * adds this.matrix with  A.matrix 
     * @param A Matrix  to be  added to this 
     * @return ans added Matrix  
     */
    @SuppressWarnings("unchecked")
    public Matrix<E> subtract(Matrix<E> A){
        E p, q;        
        Matrix<E> ans = new Matrix<>(m, n);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                p = (E)this.matrix[i][j];
                q = (E)A.matrix[i][j];
                ans.matrix[i][j] =  p.subtract(q);
            }
        }return ans;
    }

    /**
     * Matrix Multiplication <br>
     * C = <i>this</i> * A<br>
     * Formula:c_ij = sum{<i>this_ik</i> * a_kj}
     * 
     * @param A
     * @return
     */
    @SuppressWarnings("unchecked")
    public Matrix<E> multiply(Matrix<E> A){
        if(n != A.m) return new Matrix<E>(0, n);
        Matrix<E> ans = new Matrix<>(m, A.n);
        E first, second, third;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < A.n; j++){
                for(int k = 0; k < n; k++){
                    first = (E)matrix[i][k];
                    second = (E)A.matrix[k][j];
                    third = (E)ans.matrix[i][j];                    
                    ans.matrix[i][j] = (E)third.add((E)first.multiply(second));
                }
            }
        }
        return ans;
    }
    
    public Matrix<E> divide(Matrix<E> A){
        println("ERR 003 : Matrix can't divide other matrix. Illogical !");
        return this;
    }

    @SuppressWarnings("unchecked")
    public Matrix<E> abs(){
        Matrix<E> ans = new Matrix<>(m, n);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                ans.matrix[i][j] = (E)matrix[i][j].abs();
            }
        }
        return ans;
    }

    /**
     * Self methods / function
     * 
     */

    /**
     *           T
     * ans = this
     * @return
     */
    @SuppressWarnings("unchecked")
    public Matrix<E> transpose(){
        Matrix<E> ans = new Matrix<>(n, m);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                ans.matrix[j][i] = (E)matrix[i][j];
            }
        }
        return ans;
    }

    /**
     * Utils
     * 
     */
    public static void print(Object ...objs){
        display.print(objs);
    }
    public static void println(Object ...objs){
        display.println(objs);
    }

     /**
     * To print just matrix elements
     *  false calls and returns toString()
     * @return 
     */
    @Override
    @SuppressWarnings("unchecked")
    public String toString() {
        String s = "";
        for(int i = 0; i < m; i++ ){
            for(int j = 0; j < n; j++){
                E et = (E)matrix[i][j];
                s = s + et  + "   ";
            }s += "\n";
        }s += "\n";
        return s;
    }    
}