package rmath.helpers;

import java.util.Arrays;

public class display{ 
    public static void print(Object ...objs){        
        for(Object o : objs){            
            if(o instanceof Object[]) {                
                Object[] oarr = (Object [])o;                
                System.out.print(Arrays.toString(oarr) + " ");
            }
            else System.out.print(o.toString() + " ");
        }
    }

    public static void println(Object ...objs){        
        print(objs);
        System.out.println();
    }
}