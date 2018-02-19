package Sec1_3_3;

import java.util.*;
import java.io.*;

//Sec1_3_3.Sec1_3_3_Loansome_Car.txt
class Sec1_3_3_Loansome_Car {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0)
            System.setIn(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int months = in.nextInt();
            if (months < 0)
                return;
            double down = in.nextDouble();
            double loanAmount = in.nextDouble();
            double paymentPerMonth = loanAmount / months;
            double carValue = down + loanAmount;
            int numDeprecation = in.nextInt();
            HashMap<Integer, Double> deprecationAmounts = new HashMap<>();
            for (int i = 0 ; i < numDeprecation; i++) {
                int month = in.nextInt();
                double percent = in.nextDouble();
                deprecationAmounts.put(month, percent);
            }
            double currentDeprecation = deprecationAmounts.get(0);
            carValue *= 1 - currentDeprecation;
            int month = 0;
            while (carValue < loanAmount) {
                month++;
                if (deprecationAmounts.containsKey(month))
                    currentDeprecation = deprecationAmounts.get(month);
                carValue *= 1 - currentDeprecation;
                loanAmount -= paymentPerMonth;
            }
            System.out.println(month + (month == 1 ? " month" : " months"));
        }
    }
}
