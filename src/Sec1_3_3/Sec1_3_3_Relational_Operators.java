package Sec1_3_3;
import java.util.*;
import java.io.*;

class Sec1_3_3_Relational_Operators {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0)
            System.setIn(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        for (int i = 1 ;i <= times; i++) {
            long a = in.nextLong();
            long b = in.nextLong();
            System.out.println(a < b ? "<" : a == b ? "=" : ">");
        }
    }
}
