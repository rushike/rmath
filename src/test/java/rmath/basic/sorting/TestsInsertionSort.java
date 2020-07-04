package tests.rmath.basic.sorting;

import rmath.basic.sorting.*;
import java.util.*;

public class TestsInsertionSort {
    static double[] arr = {1, 2, 34 ,53, 3, 4, 5};
    
    public static void main(String[] args) throws Exception {
        InsertionSort is = new InsertionSort(arr);
        double[] res = is.sort();
        print("result is : ", Arrays.toString(res));
    }
    static void print(String str){
        System.out.println(str);
    }
    static void print(String ...str){
        for(String s : str){
            System.out.print(s + "");
        }
    }
}
