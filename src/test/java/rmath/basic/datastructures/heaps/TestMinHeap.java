package rmath.basic.datastructures.heaps;

import rmath.helpers.display;
import rmath.basic.sorting.Sort;
import rmath.basic.datastructures.heaps.MinHeap;

public class TestMinHeap {    
    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<Integer>(10);
        heap.set_heap(new Integer[]{1, 3, 2, 11, 5, 12, 81, 92, 91029, 129, 322, 12});
        heap.build_heap();
        Object[] a = heap.sort();
        // @SuppressWarnings("rawtypes")
        Integer[] b = heap.sort(Sort.ASCENDING, new Integer[12]);
        Integer[] c = heap.sort(Sort.ASCENDING, Integer[].class);
        display.println(heap);
        display.println(a);    
        display.println(b);
        display.println(c);
    }
}