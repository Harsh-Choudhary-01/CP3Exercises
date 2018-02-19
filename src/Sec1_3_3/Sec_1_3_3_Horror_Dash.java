package Sec1_3_3;

import java.util.*;
import java.io.*;
//4 minutes 32 seconds
class Sec_1_3_3_Horror_Dash {
    public static void main(String[] args) throws IOException {
        if (args.length > 0)
            System.setIn(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        for (int i = 1; i <= times; i++) {
            int num = in.nextInt();
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < num; j++) {
                int speed = in.nextInt();
                if (speed > max)
                    max = speed;
            }
            System.out.printf("Case %d: %d\n", i, max);
        }
    }
}
