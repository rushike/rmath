package rmath.structure;

import rmath.helpers.display;
import rmath.num.Z;
import rmath.structure.*;

public class TestMatrix {
    public static void main(final String[] args) {
        final Matrix<Z> matrix1 = new Matrix<Z>(new Double[][] {
            {2.3 ,4.2},
            {1.2, 6.3}
        });

        final Matrix<Z> matrix2 = new Matrix<Z>(new Double[][] {
            {2.0 ,4.0},
            {1.7, 6.5}
        });

        display.println("matrix A is : \n", matrix1);
        display.println("matrix B is : \n", matrix2);
        
        
        display.println("matrix add is : \n", matrix1.add(matrix2));
        display.println("matrix subtract is : \n", matrix1.subtract(matrix2));
        display.println("matrix multiply is : \n", matrix1.multiply(matrix2));
        display.println("matrix transpose is : \n", matrix1.transpose());
    }   
}