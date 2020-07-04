/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extended_number;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;


/**
 *
 * @author rushi
 */
public class Operators extends Symbol {
    public Operators(String opr){
        super(opr);
    }
    
    public static final char[] OPERATOR_LIST = new char[]{' ', '+', '-', '%', '*', '/', '^', '!'};

    public static final int LEN = 8;

    public static final char[] UNI_OPERATORS_LIST = new char[]{'!', '#'};

    public static final char[] SEMI_OPERATORS_LIST = new char[]{'+', '-'};

    public static final HashMap<String, Double> OPERATOR_PRECEDENCE_MAP = new HashMap<>();

    // static{
       
    //     OPERATOR_LIST = new char[]{' ', '+', '-', '%', '*', '/', '^', '!'};
        
    //     HashMap<String, Double> map = new HashMap<>();
    //     map.put("+", 1.5); map.put("-", 1.0);
    //     map.put("%", 2.0);
    //     map.put("*", 3.0); map.put("/", 3.5);
    //     map.put("^", 4.0);
    //     map.put("!", 5.0);
    //     OPERATOR_PRECEDENCE_MAP = Collections.unmodifiableMap(map);
    // }
    
    public boolean is_operator(){
        char sym_ch = this.sym.charAt(0);
        for(int i = 0; i < OPERATOR_LIST.length; i++){
            if(sym_ch == OPERATOR_LIST[i]) return true;
        }return false;
    }

    public boolean is_semi_operator(){
        return is_semi_operator(sym.charAt(0));
    }

    public static boolean is_semi_operator(char ch){
        for(int i = 0; i < SEMI_OPERATORS_LIST.length; i++){
            if(ch == SEMI_OPERATORS_LIST[i]) return true;
        }return false;
    }

    public boolean is_uni_operator(){
        return is_uni_operator(sym.charAt(0));
    }

    public static boolean is_uni_operator(char ch){
        for(int i = 0; i < SEMI_OPERATORS_LIST.length; i++){
            if(ch == UNI_OPERATORS_LIST[i]) return true;
        }return false;
    }

    public String toString(){
        return sym.toString();
    }
}
