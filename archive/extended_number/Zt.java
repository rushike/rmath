package extended_number;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.concurrent.Semaphore;

import extended_number.Iterator;
import extended_number.helper.math.*;

public class Zt extends W{
    long value = 0;

    private BigInteger valb;

    private int[] mag;

    private int signf;

    /**
     * Stores bit_length of number in binary system, 
     * e.g. 16 length is 5
     */
    int length = 0;
    
    /**
     * Indicates radix of number, default 10 
     */
    int radix = 10;
    
    /**
     * WRAP is use to get long unsigned integer value
     */
    public static final long WRAP = 0xffffffffL;

    public long wrap = WRAP;
    
    /**
     * WRAP_BITS are no. of bits of number that can WRAP to long value
     */
    public static final int WRAP_BITS = 32;
    
    public long wrap_bits = WRAP_BITS;
    /**
     * @since  INcomplete definition 
     * INCR holds the number of bits that the current wrap in radix 10  
     */
    public int[] INCR = {10};
    
    /**
     * Zero representation in Z 
     */
    public static Zt ZERO = new Zt("0");
    
    /**
     * ONE representation of ONE - Unity
     */
    public static Zt ONE = new Zt("1");

    public static Zt NEG_ONE = new Zt("-1");
    

    /**
     * Flags
     */
    int CARRY_FLAG = 0;

    int Z_FLAG = 0;

    int woringstate = -1;

    Semaphore sync_t = new Semaphore(1);

    Semaphore semaphore = new Semaphore(1);
    

    /**
     * Primary Constructor
     * @param num
     */
    public Zt(String num){
        super(num);
        valb = new BigInteger(num);
        mag = valb.mag;
        signf = valb.signum;
    }

    public Zt(){
        valb = BigInteger.ZERO;
        mag = valb.mag;
        signf = valb.signum;
    }

    public Zt(BigInteger bval){
        valb = bval;
        mag = valb.mag;
        signf = valb.signum;
    }

    public Zt(long val){
        this.value = val;
        valb = new BigInteger(val);
        mag = valb.mag;
        signf = valb.signum;
    }

    public void set(Zt valt){
        valb = valt.valb;
        mag = valb.mag;
        signf = valb.signum;
    }

    public void  workingstate(int state){
        woringstate = state;
    }

    // public void synct(boolean t){
    //     sync = t;
    // }

    public Zt add(Zt zt){
       return new  Zt(valb.add(zt.valb));
    }

    public static Zt add(Zt a, Zt b){
        BigInteger bt = a.valb.add(b.valb);
        return new Zt(bt);
    }

    public Zt sub(Zt zt){
        return new  Zt(valb.subtract(zt.valb));
    }

    public static Zt sub(Zt a, Zt b){
        BigInteger bt = a.valb.subtract(b.valb);
        return new Zt(bt);
    }

    public Zt multiply(Zt zt){
        return new Zt(valb.multiply(zt.valb));
    }

    public static Zt multiply(Zt a, Zt b){
        BigInteger bt = a.valb.multiply(b.valb);
        return new Zt(bt);
    }

    public Zt additive_inverse(){
        return multiply(NEG_ONE);
    }

    /**
     * Multiplies on other thread
     * Stores result in this = this * b
     * @param b second arrugument of multiplication
     */
    public void multilply_concurrent(Zt b){
        new Thread(){
            Zt a = null, b = null;
            Thread init(Zt a, Zt b){
                this.a = a;
                this.b = b;
                return this;
            }

            @Override
            public void run(){
                Thread.currentThread().setPriority(10);
                a.workingstate(0);// Enters in working state
                b.workingstate(0);// Enters in working state
                a.set(a.multiply(b));
                a.workingstate(1);//Enters in final state
                b.workingstate(-1);//Enters in notworking state
            }
        }.init(this, b).start(); 
    }

    public Zt divide(Zt a){
        return new Zt(valb.divide(a.valb));
    }

    public static Zt divide(Zt a, Zt b){
        BigInteger bt = a.valb.divide(b.valb);
        return new Zt(bt);
    }

    public Zt rem(Zt zt){
        return new Zt(valb.remainder(zt.valb));
    }

    public static Zt rem(Zt a, Zt b){
        BigInteger bt = a.valb.remainder(b.valb);
        return new Zt(bt);
    }

    public Zt pow(int exp){
        return new Zt(valb.pow(exp));
    }

    public static Zt pow(Zt a, int exp){
        BigInteger bt = a.valb.pow(exp);
        return new Zt(bt);
    }

    public Zt gcd(Zt a) {
        return gcd(this, a);
    }
    public static Zt gcd(Zt a, Zt b) {
        Zt rem = a.rem(b);
        if(rem.equals(Zt.ZERO))
            return b;
        return gcd(b, rem);
    }

    public  int[] factorial(int num, boolean internal) {
        
        int len = factorial_length(num, 2); // factorial length if in specific radix = 2
        
        int int_len = len / WRAP_BITS + 2; // num length allocated in int array, + 1
        
        int[] fact = new int[int_len]; //   allocating space for array
        
        fact[fact.length - 1] = 1; //   assigning unit value to num fact 

        for(int i = 2; i <= num; i++) {
            len = factorial_length(i, 2);
            scale(fact, i, len);
        }return fact;
    }
    
    public static Zt factorial_op(int num){
        Zt v = new Zt("0");
        return new Zt(new BigInteger(1, v.factorial_op(num, true)));
    }

    public int[] factorial_op(int num, boolean test){
        int len = factorial_length(num, 2); // factorial length if in specific radix = 2
        
        int int_len = len / WRAP_BITS + 2; // num length allocated in int array, + 1
        
        int[] fact = new int[int_len]; //   allocating space for array
        
        fact[fact.length - 1] = 1; //   assigning unit value to num fact
        
        int start_offset, end_offset;
        for(int i = 2; i <= num; i++){
            end_offset = int_len - factorial_length(i, 2) / WRAP_BITS - 1;
            start_offset = int_len - factorial_trailing_zeros(i, 2) / WRAP_BITS;
            end_offset = end_offset < 0 ? 0 : end_offset;
           // System.out.println("start-offset : " + start_offset + " , end-offset : " + end_offset + " ..... diff : " + (start_offset - end_offset));
            scale_in_between(fact, start_offset, end_offset, i, 0);
        }
        return fact;
    }


    public Zt factorial_b(int g){
        BigInteger b = new BigInteger("1");
        for(int i = 2; i <= g; i++){
            b = b.multiply(new BigInteger(""+i));
        }
        return new Zt(b);
    }

    public Zt factorial_tree(int n){
        if(n < 2) return Zt.ONE;
        return factorial_consec_tree_struct(1, n + 1);
    }

    public Zt factorial_concurrent(int num, int threads, boolean test){
        // int[] st = new int[threads];
        // int[] en = new int[threads];

        // int imax = num / threads;
        // int sti = 0, eni = imax, i;
        // for(i = 0; eni < num; i++){
        //     st[i] = sti;
        //     en[i] = eni;
        //     sti = eni; eni = eni + imax;
        // }
        // st[i] = sti; en[i] = num;

        if(num < 400) return factorial_op(num);
        
        final boolean[] r = new boolean[threads];
        final Zt[] workers = new Zt[threads];
        final int imax = num / threads;
        final Iterator i = new Iterator();
        sync_t = new Semaphore(threads);
        
        // Thread.currentThread().setPriority(10);
        for(; i.ival() < threads; ){
            workers[i.ival()] = new Zt();
            new Thread(){
                private int d = 0, sti, eni;
                Thread init(int d){
                    this.d = d;
                    sti = 1; eni = imax;
                    return this;
                }
                @Override
                public void run() {
                    // Thread.currentThread().setPriority(10);
                    try{
                        sync_t.acquire();
                        sti = imax * d; eni = sti + imax;
                        // System.out.println("ival : " + d + ", sti : " + sti + ", eni : " + eni);
                        workers[d].set(factorial_consec(sti + 1, eni + 1));
                        workers[d].workingstate(1);
                        sync_t.release();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                        sync_t.release();
                    }
                }
            }.init(i.ival()).start();
            i.iplusplus();
        }
        // Thread.currentThread().setPriority(5);
       // System.out.println("Avail Per : " + sync_t.availablePermits());
        try{
            HashSet<Integer> work_set = new HashSet<>();
            while(sync_t.availablePermits() <= threads){
                // System.out.println("Avail Per : " + sync_t.availablePermits());
                //System.out.println("Available Permits : " + sync_t.availablePermits());
                if(sync_t.availablePermits() >=2 ){
                    //System.out.println("Available Permits : " + sync_t.availablePermits());
                    int[] index = indexes_of_two_instances_in_state(workers, 1);//any two instances from workers who are in final state.
                    // System.out.println("Available Permits : " + sync_t.availablePermits());
                    if(index != null && index.length == 2){
                        sync_t.acquire(2);
                        work_set.add(index[0]); work_set.add(index[1]);
                        // System.out.println("index 1 : " + index[0] + ", index 2 : " + index[1]);
                        workers[index[0]].workingstate(0);
                        workers[index[1]].workingstate(0);
                        workers[index[0]].multilply_concurrent(workers[index[1]]);
                    }
                }
                // System.out.println(".....................________________");
                // for(int k = 0; k < threads; k++){
                //     System.out.print("( " + k + ", " + workers[k].woringstate + " ),  ");   
                // }
                // System.out.println("\n.....................________________");
                if(work_set.size() == 1){
                    //System.out.println("BREAKING.>>>>>>>>>"); 
                    break;
                }
                try{
                    if(!work_set.isEmpty()){      
                        // System.out.println("Hset present : " + work_set);
                        for(int k : work_set){
                            if(workers[k].woringstate != 0){
                                work_set.remove(k);
                                sync_t.release();
                            }
                        }
                    }
                }catch(ConcurrentModificationException e){
                    // e.printStackTrace();
                }
                if(sync_t.availablePermits() == threads) break;

                try{
                    // Thread.sleep(1000/threads);
                }catch(Exception e){
                    e.printStackTrace();
                }
                // int f = total_worker_in_states(workers, 1);
                // System.out.println("f -------------- >         : " + f);
                // if(f == 1) break;
            }
        }catch(Exception e){
            e.printStackTrace();
            sync_t.release();
        }
        // for(int j  = 0; j < threads; j++){
        //     System.out.println();
        //     d = d.multiply(workers[j]);
        // }
        // System.out.println("Final result : ");
        return workers[0];
    }

    public Zt factorial_consec(int st, int en){
        BigInteger b = new BigInteger(""+st);
        for(int i = st + 1; i < en; i++){
            b = b.multiply(new BigInteger("" + i));
        }return new Zt(b);
    }

    /**
     * Multiplies all consective number in interval [low, high), i.e. low * (low  + 1) * ... (high - 1);
     * @param low lowest bound to start, inclusive
     * @param high highest bound to end , exlcusive
     * @return consectutive multiplication of numbers.
     */
    public Zt factorial_consec_tree_struct(int low, int high){
        if(high < low) return factorial_consec_tree_struct(high, low);
        if(low + 1 < high){
            int mid = (high + low + 1) / 2;
            return factorial_consec_tree_struct(low, mid).multiply(factorial_consec_tree_struct(mid, high));
        }
        return new Zt(low);
    }

    public static int factorial_trailing_zeros(int num){
        return factorial_trailing_zeros(num, 10);
    }

    public static int factorial_trailing_zeros(int num, int base){
        base = (int)Basic.largest_prime_factor(base);
        int n = (int)(Math.log(num) / Math.log(base));
        int sum = 0, mul = base;
        for(int i = 0; i < n; i++){
            sum  += num / mul;
            mul *= base;
        }
        return sum;
    }

    /**
     * finding length of num! by using Stirling Approximation
     * n! ~= root2(2*pi*n)*(n/e)^n
     * e = 2.73... natural logarithm base
     * @param num Integer number whose factorial needed
     * @param radix base in which factorial is represented
     * @return length of factorial
     */
    public static int factorial_length(int num, int radix) {
        return (int)((Math.log(2 * Math.PI * num) / 2 + num * Math.log(num) - num ) / Math.log(radix)) + 1;
    }

    // public boolean ifinsync(Zt ... r){
    //     for(int i = 0; i < r.length; i++){
    //         if(r[i].sync == false) return false;
    //     }return true;
    // }

    /**
     * Scales / multiplies num integer k times upto bit length bits from LSB 
     * @param arr number representation in 2<sup>32</sup> base 
     * @param k scaling factor
     * @param bits length to which to scale, if number was in binary
     * @return scaled number
     */  
    public int[] scale(int[] arr, int k, int bits) {
        int len = arr.length - bits / WRAP_BITS ;
        long scl = 0;
        for(int i = arr.length; i >= len;) {
            scl = (arr[--i] & WRAP) * k + (scl >>> WRAP_BITS);
            arr[i] = (int)(scl & WRAP) ;           
        }return arr;
    }
    /**
     * It will used to optimese the performance,<br>
     * If we result is n length array, but start with 1 unit array gradually grow in size,<br>
     * Also consist zeros at start ... <b>e.g.</b> <i>factorials</i>, then only scale the relavant part<br>
     * @param arr
     * @param start_offset LSB start index, in Big-Endian nearer to arr.length
     * @param end_offset MSB start index, in Big-Endian nearer to 0
     * @param k constant to scale
     * @param bits bits involed in int, base of multiplication, e.g decimal, hexadecimal etc;
     * @return
     */
    public int[] scale_in_between(int[] arr, int start_offset, int end_offset, int k, int bits){
        // int len = arr.length - start_offset + end_offset - bits / WRAP_BITS;
        long scl = 0L;
        for(int i = start_offset; i > end_offset;){
            scl = (arr[--i] & WRAP) * k + (scl >>> WRAP_BITS);
            // System.out.println("scl : " + scl);
            arr[i] = (int)(scl & WRAP);
        }if((scl >>> WRAP_BITS) > 0 && end_offset > 0){
            arr[end_offset] = (int)(scl >>> WRAP_BITS);
        }
        return arr;
    }
    /**
     * Its is reverse or inverse function to scale<br>
     * If n is scale and de_scale t times
     * n = scale(scale_invert(n, t), t)   
     * @param arr 2 ^ WRAP base Integer representation of n
     * @param k scaling factor
     * @param bits 
     * @return 
     */
    public int scale_invert(int[] arr, long k, int bits) {
        int len = arr.length;
        int i = 0;
        long divd = arr[i] & WRAP;
        while(i < len) {
            //System.out.println("i: "+i+"  divd: "+ divd);
            //Making divd > k
            while(divd < k) {
              arr[i++] = 0;
              if(i == len) return (int)divd;
              divd = divd * (WRAP + 1) + (arr[i] & WRAP);
              //System.out.println("W::i: "+i+"  divd: "+ divd);
            }
            arr[i] =(int)(divd / k );
            //System.out.println("OW::i: "+i+"  divd: "+ divd+"  arri: "+arr[i]);
            divd = divd - (arr[i++] & WRAP) * k;
            if(i < len) divd = divd * (WRAP + 1)+ (arr[i] & WRAP);
        }return (int)divd;
    }

    /**
     * Returns two indexes from array of workers in given state.
     * @param workers
     * @param state state of which instaces wanted from worker array
     * @return
     */
    public int[] indexes_of_two_instances_in_state(Zt[] workers, int state){
        int[] indexes = new int[2];
        int j = 0;
        for(int i = 0; i < workers.length && j < 2; i++){
            if(workers[i].woringstate == state){
                indexes[j] = i;
                j++;
            }
        }
        if(j < 2) return null;
        return indexes;
    }

    public int total_worker_in_states(Zt[] workers, int state){
        int count = 0;
        for(Zt k : workers){
            if(k.woringstate == state) count++;
        }
        return count;
    }
    @Override
    public boolean equals(Object obj) {
        Zt cmp = (Zt)obj;
        return valb.equals(cmp.valb);
    }

    @Override
    public String toString(){
        return valb.toString();
    }

    public void print_asyncly(){
    }

}