package Sec1_3_3;

import java.util.*;
import java.io.*;

//Sec1_3_3.Sec1_3_3_Event_Planning.txt
class Sec1_3_3_Event_Planning {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0)
            System.setIn(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int numPeople = in.nextInt();
            int budget = in.nextInt();
            int numberHotels = in.nextInt();
            int numberWeeks = in.nextInt();
            int minCost = Integer.MAX_VALUE;
            for (int i = 0 ; i < numberHotels; i++) {
                int pricePerPerson = in.nextInt();
                for (int j = 0 ; j < numberWeeks; j++) {
                    if (numPeople <= in.nextInt() && pricePerPerson * numPeople < budget && pricePerPerson * numPeople < minCost) {
                        minCost = pricePerPerson * numPeople;
                    }
                }
            }
            System.out.println(minCost == Integer.MAX_VALUE ? "stay home" : minCost);
        }
    }
}
