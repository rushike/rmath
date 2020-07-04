package rmath.helpers;

public class basic {
    public static void swap(Object[] arr, int ind1, int ind2){
        Object temp = arr[ind1];
        arr[ind1] = arr[ind2];
        arr[ind2] = temp;
    }
}