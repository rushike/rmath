
import java.io.File;

import extended_number.*;

public class Main{
    public static void main(String[] args) {
        Zt z = new Zt("1656");
        Zt c = new Zt("6");
        Zt y = z.rem(c);
        System.out.println(z);
        System.out.println(y);
        System.out.println("Divide : " + z.divide(c));
        Q q1 = new Q(2, 6);
        System.out.println((q1.add(q1)));
        System.out.println("Current : " + System.getProperty("user.dir"));
        FileRemover fr = new FileRemover(System.getProperty("user.dir"));
        fr.remove(new File(System.getProperty("user.dir")), "class");
    }
}