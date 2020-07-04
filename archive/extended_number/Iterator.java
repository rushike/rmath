package extended_number;

public class Iterator{
    int i;

    public Iterator(int i){
        this.i = i;
    }
    public Iterator(){
        i = 0;
    }

    public void iplusplus(){
        i++;
    }
    public void iplusx(int x){
        i += x;
    }

    public int ival(){
        return i;
    }
}