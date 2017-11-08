/**
 * Created by harsh on 10/28/17.
 */

import java.util.*;
import java.io.*;

//Sec1_3_3_Cost_Cutting.txt
class Sec1_3_3_Cost_Cutting {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0)
            System.setIn(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        for (int i = 1; i <= times; i++) {
            int[] nums = new int[3];
            for (int a  = 0 ; a < 3; a++)
                nums[a] = in.nextInt();
            Arrays.sort(nums);
            System.out.printf("Case %d: %d\n", i, nums[1]);
        }
    }
}
