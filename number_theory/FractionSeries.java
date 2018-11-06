/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package number_theory;

import extended_number.*;

/**
 *
 * @author rushi
 */
public class FractionSeries<E extends W> extends Series {
 
    private int limit;
    
    public FractionSeries(E base, int limit, int terms){
        this.limit = limit;
        this.terms = terms;
        this.base = base;
        series = new  Matrix<>(base, 1, terms);
        build_series();
    }
    public Matrix get_series(){
        return series;
    }
    
    @Override
    public final void build_series(){
        Matrix<Q> intr_matrix = new Matrix<>(new Q(), terms, terms);
        GeometricSeries<Q> first_series = new  GeometricSeries<>(new Q(1,1), new Q(1,2), terms);
        series = first_series.get_series();
        for(int i = 3; i < limit+1 ; i++) {
            first_series = new GeometricSeries<>(new Q(1,1), new Q(1,i), terms);
            intr_matrix = series.transpose().multiply(first_series.get_series());
             series = new  Matrix(new Q(), 1, terms);
            for(int j = 0; j < terms; j++){
                for(int k = 0; k <= terms - j; k++){
                    if(k + j < terms){
                       series.set(0, j + k, series.get_element(0, k + j).add(intr_matrix.get_element(j, k)));      
                    }
                }
            }
        }
    }
    @Override
    public String toString(){
        String s = "Fraction Series : {a: "+base+", limit: "+limit+" terms : " + terms + " }\n ";
        s = s + super.toString();
        s += "\n";
        return s;
    }
}
