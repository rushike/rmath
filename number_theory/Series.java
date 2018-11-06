/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package number_theory;

import extended_number.Matrix;
import extended_number.W;

/**
 *
 * @author rushi
 * @param <E>
 */
public class Series<E extends W> extends W{
    Matrix<E> series;
    
    E base;
    
    E e;

    int terms;
    
 
    public void  build_series(){
        
    }
    public Matrix get_series(){
        return series;
    }
    public int get_terms(){
        return terms;
    }
    public E get_base(){
        return base;
    }
    @Override
    public String toString() {
        String s = "";
        for(int i = 0; i < series.get_n(); i++){
            s = s + i+".{" + series.get_element(0, i)+"}   ";
        }
        return s; 
    }
}
