
import java.io.File;

import extended_number.*;
import parser.MathExprParser;

public class Main{
    public static void main(String[] args) {
         Zt z = new Zt("23923892839847923292839283565654654645654654654652298798708754545432323221098708089870070707877898898987676656554433434546567788789989898787878778776767665655454545454656778878729839283928392839289348234798237947497878768768768768687687687686876868768687686868768687686876868768678768768767857656576576575765765765765765765765765765765765765765764554343343218837");
         Zt c = new Zt("1234456678889006322345667889905445788754467876434567876542456789876543234567890987654321234567890987654321234567890987654321234567890987654323456789098765434567890987654323456789098765432112345678909876543212356789009876543215678900987654321");
        // FileRemover frm = new FileRemover(System.getProperty("user.dir"));
        // frm.remove(new File(System.getProperty("user.dir")), "class");
		
		// System.out.println("div qutoient : \n" + z.divide(c));
		// System.out.println("div rem : \n" + z.rem(c));
		

        int n = 500000;

        long start = System.currentTimeMillis();
        System.out.println("Factorial of " + n + " is  : " + c.factorial_tree(n));
        long start2 = System.currentTimeMillis();
        // System.out.println("Factorial of " + n + " is  : " + c.factorial_concurrent(n, 1, true));
        System.out.println("Time for tree :  " + (start2 - start) + " Time for op : " + (-start2 + System.currentTimeMillis()));
        // MathExprParser mep = new MathExprParser();
        // String expr = "-2434342433 + 2454! + 565 ^ 909";
        // mep.parse(expr);
        // System.out.println("expr : " + expr);
        // System.out.println("MEP : " + mep.expr_tokens);
    }
}