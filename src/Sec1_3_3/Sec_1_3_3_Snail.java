package Sec1_3_3;

import java.util.*;
import java.io.*;
//12 minutes 3 seconds
//Sec1_3_3.Sec_1_3_3_Snail.txt
class Sec_1_3_3_Snail {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0)
            System.setIn(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int H = in.nextInt();
            int U = in.nextInt();
            int D = in.nextInt();
            double lossPerDay = in.nextInt() / 100.0 * U;
            if (H == 0)
                break;
            double position = 0;
            int daysElapsed = 0;
            while (position >= 0 && position <= H) {
                position += Math.max(0 , U - daysElapsed * lossPerDay);
                if (position > H) {
                    daysElapsed++;
                    break;
                }
                position -= D;
                daysElapsed++;
            }
            System.out.println((position > H ? "success on day " : "failure on day ") + daysElapsed);
        }
    }
}
