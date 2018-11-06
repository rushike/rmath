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
public class GeometricSeries<E extends W> extends Series{
    
    E ratio;
   
    public GeometricSeries(E base, E ratio, int terms){
        this.base = base;
        this.ratio = ratio;
        this.terms = terms;
        series = new Matrix<>(base, 1, terms);
        build_series();
    }
    @Override
    public void build_series(){
         E item;
         series.set(0, 0, base);
        for(int i = 1; i < terms; i++){
            item = (E)series.get_element(0,i - 1);
            item = (E) item.multiply(ratio);
            series.set(0, i, item);
        }
    }
    @Override
    public String toString(){
        String s = "Geometric Series : {a: "+base+", r: "+ratio+"}\n ";
        s = s + super.toString();
        s += "\n";
        return s;
    }
}
