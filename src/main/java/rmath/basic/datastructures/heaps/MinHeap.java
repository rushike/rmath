package rmath.basic.datastructures.heaps;

import java.util.*;

import rmath.helpers.*;
import rmath.basic.sorting.Sort;

public class MinHeap<E extends Object>{
    private Object[] heap;
    private Object[] sorted;
    private Comparator<E> comparator;
    private int size = 0;
    private int quantity = 64;
    private int MAXSIZE = 1 << 20;

    private boolean init_flag = false;
    private boolean build_flag = false;
    private boolean index_str = false;

    public MinHeap(int size){
        this.size = size;
        heap = new Object[this.quantity];
    }
    public MinHeap(int size, E[] objs){
        this.size = size;
        heap = new Object[this.quantity];  
        this.set_heap(objs);        
    }

    public Object[] get_heap(){
        return this.heap;
    }        
    public void set_comparator(Comparator<E> comparator){
        this.comparator = comparator;
    }
    public void show_index(){
        this.index_str = true;
    }
    
    
    public MinHeap<E> set_heap(E[] objs){
        if(objs.length > this.quantity){
            this.quantity = this.quantity << 2;
            if(this.quantity > MAXSIZE) display.println("ERR 000: Heap full ...");
            Object[] heap_ = new Object[this.quantity];
            System.arraycopy(this.heap, 0, heap_, 0, this.heap.length);
            this.heap = heap_;
            System.gc(); // Invoke the garbage collector
        }       
        for(int i = 0; i < objs.length; i++) {
            this.heap[i] = objs[i];
        }
        this.size = objs.length;
        this.init_flag = true;
        this.default_comparator();
        return this;
    }

    public void default_comparator(){        
        if(this.comparator == null && this.heap[0] instanceof Comparable){
            this.comparator = new Comparator<E>() {
                @Override             
                @SuppressWarnings("unchecked")   
                public int compare(E o1, E o2) {
                    @SuppressWarnings("rawtypes")
                    Comparable a = (Comparable)o1, b = (Comparable)o2;
                    return a.compareTo(b);                    
                }
            };
        }            
    }

    public void check_init(){
        if(!this.init_flag) display.println("ERR 001: Probably not inistiated heap, heap allocated with nulls.");
    }
    public MinHeap<E> build_heap(){
        return this.build_heap(this.heap, 0, this.size);
    }

    public MinHeap<E> build_heap(Object[] arr, int root, int size){
        this.check_init();        
        for(int i = this.size/2 - 1; i >= 0; i--){
            this.heapify(arr, i, size);
        }
        this.build_flag = true;
        return this;
    }
    @SuppressWarnings("unchecked")
    private boolean compare_less(Object o1, Object o2, int rhs_constant){
        return (this.comparator != null && this.comparator.compare((E)o1, ((E)o2)) < rhs_constant);
    }
    public void heapify(int current_node){
        this.heapify(this.heap, current_node, this.size);
    }
    public void heapify(Object[] arr, int current_node, int size){
        int left = 2 * current_node + 1;
        int right = 2 * current_node + 2;
        int smallest = current_node;
        if(left < size && this.compare_less(arr[left], arr[smallest], 0)){
            smallest = left;
        }
        if(right < size && this.compare_less(arr[left], arr[smallest], 0)){
            smallest = right;
        }        
        if(smallest != current_node){
            basic.swap(arr, smallest, current_node);
            this.heapify(arr, smallest, size);
        }
    }

    public Object[] sort(String order){
        Object[] store = Arrays.copyOf(this.heap, this.size);
        MinHeap temp = this.build_flag ? this : this.build_heap();
        int hsize = this.size;
        for(int i = this.size - 1; i > 0; i--){
            basic.swap(store, 0, i);
            hsize -= 1;            
            this.heapify(store, 0, hsize);
        }
        this.sorted = Arrays.copyOfRange(store, 0, this.size);
        List<Object> li = Arrays.asList(this.sorted);
        if(order == Sort.ASCENDING) Collections.reverse(li);
        return li.toArray();
    }
    public E[] sort(String order, E[] res){
        if (res.length < this.size) display.println("ERR 002 : Result can't copy, res provided is smaller than expected length : ", this.size);
        Object[] temp = this.sort(order);
        for(int i = 0; i < res.length; i++){            
            res[i] = (E)temp[i];
        }
        return res;
    }
    public E[] sort(String order, Class<? extends E[]> class1){
        return Arrays.copyOf(this.sort(order), this.size, class1);
    }
    public Object[] sort(){
        return this.sort(Sort.ASCENDING);
    }
    public String toString(){
        this.check_init();
        int lg = (int)Math.log(this.size) + 2;
        int layer_size = 1;
        for(int i = 0; i < lg; i++){
            int prev_count = (1 << i);
            for(int j = 0; j < layer_size; j++){                
                if(prev_count + j > this.size) break;
                if (this.index_str) display.print("[ ", this.heap[prev_count + j - 1], "@i", prev_count + j - 1, " ] ");
                else display.print(this.heap[prev_count + j - 1]);
            }print();
            layer_size = layer_size << 1;
        }
        return "";
    }

    public static void print(Object ...objs){
        display.println(objs);
    }
};;