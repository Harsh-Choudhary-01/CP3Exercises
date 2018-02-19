package Sec2_2;

import java.util.*;
import java.io.*;
//21 minutes 23 seconds, using HashMap is too slow need to use own array
//Sec_2_2_Newspaper.txt
class Sec_2_2_Newspaper {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0)
            System.setIn(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        int[] valueArr = new int[70000];
        for (int time = 1 ; time <= times; time++) {
            int numCharacters = in.nextInt();
            if (time > 1)
                Arrays.fill(valueArr, 0);
            for (int i = 1; i <= numCharacters; i++) {
                valueArr[in.next().charAt(0)] = in.nextInt();
            }
            int lines = in.nextInt();
            in.nextLine();
            long money = 0;
            for (int i = 1 ; i <= lines; i++) {
                for (char c : in.nextLine().toCharArray()) {
                    money += valueArr[c];
                }
            }
            String cents = String.valueOf(money % 100);
            if (cents.length() < 2)
                cents = "0" + cents;
            System.out.println(money / 100 + "." + cents + "$");
        }
    }
}
