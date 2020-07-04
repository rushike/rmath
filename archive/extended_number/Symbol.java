package extended_number;

import java.util.ArrayList;

import extended_number.Operators;

public class Symbol extends Number{
    StringBuilder sym;
    public static final int  _SYMBOL_ = 0;
    public static final int _WORD_ = 1;
    public static final int _OPERATOR_ = 2;
    public static final int _BLANK_ = Integer.MAX_VALUE / 2;
    public static final int _FINAL_ = Integer.MAX_VALUE;
    
    public Symbol(){

    }

    public Symbol(String sym){
        this.sym = new StringBuilder(sym);
    }

    public void append(String s){
        sym.append(s);
    }
    
    public String sym(){
        return sym.toString();
    }

    public boolean is_word(){return false;}

    public static boolean is_word(String sm){
        boolean check;
        int base = W._DIGIT_.length;
        for(int i = 0; i < sm.length(); i++){
            check = false;
            for(int j = 0; j < base; j++){
                if(sm.charAt(i) == W._DIGIT_[j]) check = true; 
            }if(!check) return false;
        }return true;
    }
    public static boolean is_word(char sm){
        int base = W._DIGIT_.length;
        for(int j = 0; j < base; j++){
            if(sm == W._DIGIT_[j]) return true; 
        }return false;
    }


    public boolean is_operator(){return false;}
    
    public static boolean is_operator(char sym_ch){
        for(int i = 0; i < Operators.OPERATOR_LIST.length; i++){
            if(sym_ch == Operators.OPERATOR_LIST[i]) return true;
        }return false;
    }

    public static boolean is_operator(String sym){
        if(sym.length() > 1) return false;
        char sym_ch = sym.charAt(0);
        for(int i = 0; i < Operators.OPERATOR_LIST.length; i++){
            if(sym_ch == Operators.OPERATOR_LIST[i]) return true;
        }return false;
    }

    public static int type(char ch){
        Symbol syw = new W(Character.toString(ch));
        Symbol syo = new W(Character.toString(ch));
        if(syw.is_word()){
            return _WORD_;
        }else if(syo.is_operator()){
            return _OPERATOR_;
        }
        return _SYMBOL_;
    }

    public static boolean contains(ArrayList<Symbol> expr_tokens, String sym){
        for(int i = 0; i < expr_tokens.size(); i++){
            if(expr_tokens.get(i).sym.equals(sym)) return true;
        }return false;
    }

    @Override
    public double doubleValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    @Override
    public float floatValue() {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int intValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long longValue() {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public boolean equals(Object obj){
        Symbol sy = (Symbol)obj;
        return this.sym.equals(sy.sym);
    }

    public String toString(){
        return sym.toString();
    }
}