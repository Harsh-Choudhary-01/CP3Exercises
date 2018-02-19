package Sec1_3_3;
import java.util.*;
import java.io.*;

//Sec1_3_3_Nlogonia.txt
class Sec1_3_3_Nlogonia {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0)
            System.setIn(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int times = in.nextInt();
            if (times == 0)
                return;
            int divX = in.nextInt();
            int divY = in.nextInt();
            for (int i = 0 ; i < times ; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                if (x == divX || y == divY)
                    System.out.println("divisa");
                else {
                    if (x < divX) {
                        if (y > divY)
                            System.out.println("NO");
                        else
                            System.out.println("SO");
                    }
                    else {
                        if (y > divY)
                            System.out.println("NE");
                        else
                            System.out.println("SE");
                    }
                }
            }
        }
    }
}
