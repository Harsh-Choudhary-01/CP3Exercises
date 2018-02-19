package Sec2_2;

import java.util.*;
import java.io.*;
//30 minutes... I was getting this really weird RTE when using in.nextInt() inside a for loop instead of
//splitting next line
//Sec2_2.Sec_2_2_Jolly_Jumpers.txt
class Sec_2_2_Jolly_Jumpers {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0)
           System.setIn(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            ArrayList<Integer> num = new ArrayList<>();
            HashSet<Integer> diffs = new HashSet<>();
            int size = in.nextInt();
            for (String s : in.nextLine().split(" ")) {
                if (s.equals(""))
                    continue;
                num.add(Integer.parseInt(s));
            }
            for (int i = 1; i < num.size(); i++) {
                diffs.add(Math.abs(num.get(i) - num.get(i - 1)));
            }
            boolean jolly = true;
            for (int i = 1; i < num.size(); i++) {
                if (!diffs.contains(i)) {
                    jolly = false;
                    break;
                }
            }
            System.out.println(jolly ? "Jolly" : "Not jolly");
        }
    }
}
