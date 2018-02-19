package Sec1_3_3;

import java.util.*;
import java.io.*;
//25 minutes 42 seconds
//Sec_1_3_3_Bender.txt
class Sec_1_3_3_Bender {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0)
            System.setIn(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        HashMap<String, String> results = new HashMap<>();

        results.put("+x +y", "+y");
        results.put("+x -y", "-y");
        results.put("+x +z", "+z");
        results.put("+x -z", "-z");

        results.put("+y +y", "-x");
        results.put("+y -y", "+x");
        results.put("+y +z", "+y");
        results.put("+y -z", "+y");

        results.put("-y +y", "+x");
        results.put("-y -y", "-x");
        results.put("-y +z", "-y");
        results.put("-y -z", "-y");

        results.put("+z +y", "+z");
        results.put("+z -y", "+z");
        results.put("+z +z", "-x");
        results.put("+z -z", "+x");

        results.put("-z +y", "-z");
        results.put("-z -y", "-z");
        results.put("-z +z", "+x");
        results.put("-z -z", "-x");

        results.put("-x +y", "-y");
        results.put("-x -y", "+y");
        results.put("-x +z", "-z");
        results.put("-x -z", "+z");

        while (in.hasNextInt()) {
            int length = in.nextInt();
            if (length == 0)
                return;
            String current = "+x";
            for (int i = 0 ; i < length - 1; i++) {
                String change = in.next();
                if (!change.equals("No"))
                    current = results.get(current + " " + change);
            }
            System.out.println(current);
        }
    }
}
