package rmath.basic.sorting;

public class InsertionSort extends Sort {
    private double[] arr;
    private String type = "double";
    private String order = Sort.ASCENDING;

    public InsertionSort(double[] arr, String order) {
        this.arr = arr;
        this.order = order;
    }
    public InsertionSort(double[] arr) {
        this.arr = arr;
        this.order = Sort.ASCENDING;
    }

    public boolean bool_type(double current_el, double array_el){
        if(this.order == Sort.ASCENDING){
            return current_el < array_el;
        }else{
            return current_el > array_el;
        }
    }

    public double[] sort(String type){
        for(int i = 0; i < this.arr.length; i++){
            int j = i;
            double current_el = this.arr[i];
            while( j > 0 && this.bool_type(current_el, this.arr[j - 1])){
                this.arr[j] = this.arr[j - 1];
                j--;
            }
            this.arr[j] = current_el;
        }
        return this.arr;
    }
    public double[] sort(){
        return this.sort(this.type);
    }
}