/**
 * Created by harsh on 11/9/17.
 */

import java.util.*;
import java.io.*;
//Tried to use a boolean array with searches on both sides but timed out

//Sec_2_2_Army_Buddies.txt
class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0)
            System.setIn(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int soldiers = in.nextInt();
            int lossReports = in.nextInt();
            if (soldiers == 0)
                return;
            List<Integer> soldierList = new LinkedList<Integer>();
            for (int i = 1; i <= soldiers; i++)
                soldierList.add(i);
            for (int i = 1; i <= lossReports; i++) {
                int left = Collections.binarySearch(soldierList, in.nextInt());
                int right = Collections.binarySearch(soldierList, in.nextInt());
                System.out.println((left == 0 ? "*" : soldierList.get(left -1)) + " "
                        + (right == soldierList.size() - 1 ? "*" : soldierList.get(right + 1)));
                for (int j = right; j >= left; j--) {
                    soldierList.remove(j);
                }
            }
            System.out.println("-");
        }
    }
}
