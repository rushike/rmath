package rmath.num;

public interface Word<E> {
    public E abs();
    
    public E add(E val);

    public E divide(E val);    

    public E multiply(E val);

    public E subtract(E val);
}