package parser;

import java.util.ArrayList;
import java.util.Arrays;

import extended_number.Operators;
import extended_number.Symbol;
import extended_number.W;



public class MathExprParser extends Parser{
    StringBuilder expr;
    public ArrayList<Symbol> expr_tokens;
    public ArrayList<Symbol> postfix;
    public MathExprParser(){
        expr = new StringBuilder("-0");
        expr_tokens = new ArrayList<>();
        expr_tokens.add(new W(expr.toString()));
        postfix = new ArrayList<>();
        postfix.add(new W(expr.toString()));
    }
    @Override
    public void parse(String expr){
        this.expr = new StringBuilder(expr);
        expr_tokens = new ArrayList<>();
        postfix = new ArrayList<>();
        operator_tokener(expr);
        // anaylse_tokens();
    }


    public void operator_tokener(String esp){
        esp = esp.trim();
        System.out.println("esp : " + esp + ", expr_tokens : " + expr_tokens);
        if(Symbol.is_word(esp)){
            expr_tokens.add(new W(esp));
            return;
        }else if(Symbol.is_operator(esp)){
            expr_tokens.add(new Operators(esp));
            return;
        }else if(esp.equals("")) return;
        else if(esp == null) return;
        boolean check = true;
        try{
            
            for(int i = 0; i < Operators.LEN; i++){
                if(esp.contains(Operators.OPERATOR_LIST[i] + "")){
                    System.out.println("esp..... : " + esp + ", regex : " + Operators.OPERATOR_LIST[i] + ", expr_tokens : " + expr_tokens);
                    try{
                        String[] tokens = split(esp, Operators.OPERATOR_LIST[i] + "");
                        System.out.println("tokens list : " + Arrays.toString(tokens));
                        for(int j = 0; j < tokens.length; j++){
                            if(check){
                                operator_tokener(tokens[j]);
                            }
                        }
                        check = false;
                    }catch(Exception e){
                         e.printStackTrace();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void anaylse_tokens(){
        System.out.println("Tokens : " + expr_tokens);
        ArrayList<Symbol> temp_tokens = new ArrayList<>();
        boolean token = true;
        for(int i = 0; i < expr_tokens.size(); i++){
            if(expr_tokens.get(i) instanceof Operators){
                System.out.println("i." + i + " instance operator : " + expr_tokens.get(i));
                if(((Operators)expr_tokens.get(i)).is_uni_operator()){
                    System.out.println("i." + i + " instance uni_operator : " + expr_tokens.get(i));
                    temp_tokens.add(expr_tokens.get(i));
                    token = false;                    
                }else if(token && ((Operators)expr_tokens.get(i)).is_semi_operator() && i < (expr_tokens.size() - 1)){
                    String str;
                    if(expr_tokens.get(i) instanceof W) str = expr_tokens.get(i).sym() + expr_tokens.get( i + 1).sym();
                    else return;
                    temp_tokens.add(new W(str));
                    i++;
                    token = false;
                }else{
                    token = true;
                    temp_tokens.add(expr_tokens.get(i));
                }
            }else {
                System.out.println("i." + i + " instance word : " + expr_tokens.get(i));
                temp_tokens.add(expr_tokens.get(i));
                token = false;
            }
        }
        expr_tokens.clear();
        expr_tokens.addAll(temp_tokens);
    }

    public String[] split(String exprc, String regex){
        if(regex.equals(" ")) return exprc.split(regex);
        ArrayList<String> list = new ArrayList<>();
        String spce_buff = "";
        int i = 0, j = 0;
        while(i < exprc.length()){
            if(Symbol.is_word(exprc.charAt(i))){
                spce_buff = spce_buff + exprc.charAt(i);
            }else if(Symbol.is_operator(exprc.charAt(i))){
                if(i != 0)list.add(spce_buff);
                list.add(exprc.charAt(i) + "");
                spce_buff = "";
            }
            i++;
        }if(Symbol.is_word(spce_buff)) list.add(spce_buff + "");
        // System.out.println("split tokens : " + list);
        return list.toArray(new String[list.size()]);
    }

    
}

/**
 * public void create_tokens(){
        StringBuilder cr_str = new StringBuilder();
        int stype = Symbol.type(expr.charAt(0)), pretype = 0;
        //Machine Iterate over expr making
        int i = 0;
        while(i < expr.length()){
            System.out.println("out.i. " + i + "  ch : "+ expr.charAt(i) + " ....  tokens : " + expr_tokens);
             if(expr.charAt(i) == '\b') {
                stype = Symbol.type(expr.charAt(i));
                i++;
                continue;
            }
            System.out.println("stype : " + stype + ",   pretype : " + pretype + "\n");
           
            if(pretype == Symbol._SYMBOL_){// State A
                //Its Start ....
                //May has entered in invalid state
                System.out.println("STATE A");
                System.out.println("crstr : " + cr_str);
                cr_str.append(expr.charAt(i++));
                pretype = stype == Symbol._WORD_ ? 1 : 2;
                continue;
            }

            if(stype == Symbol._WORD_){// State B
                System.out.println("STATE B");
                System.out.println("crstr : " + cr_str);
                while(stype == pretype && i < expr.length()) {
                    System.out.println("B.out.i : " + i );
                    System.out.println("crstr : " + cr_str);
                    cr_str.append(expr.charAt(i++));
                    pretype = stype;
                    if(i < expr.length()) {
                        stype = Symbol.type(expr.charAt(i));
                        if(stype == Symbol._OPERATOR_){
                            System.out.println("STATE D");
                            System.out.println("crstr : " + cr_str);        
                            expr_tokens.add(new W(cr_str.toString()));
                        }
                    }
                    else {
                        expr_tokens.add(new W(cr_str.toString()));
                        return;
                    }
                }
            }else if(stype == Symbol._OPERATOR_){// State C
                System.out.println("C.out.i : " + i );
                System.out.println("STATE C");
                System.out.println("crstr : " + cr_str);
                //Search for word next ... 
                pretype = stype;
                stype = Symbol.type(expr.charAt(++i));
                if(stype == Symbol._OPERATOR_) return;
                else{//State D
                    System.out.println("STATE D");
                    System.out.println("crstr : " + cr_str);    
                    expr_tokens.add(new Operators(cr_str.toString()));
                }

            }else {
                System.out.println("InElse : ////////////////// ..................... ");
                i++;
            }
            // pretype = stype;
        }
    }
 */