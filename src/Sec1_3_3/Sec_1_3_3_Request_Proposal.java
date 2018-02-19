package Sec1_3_3;

import java.util.*;
import java.io.*;
//13 minutes 30 seconds
//Sec_1_3_3_Request_Proposal.txt
class Sec_1_3_3_Request_Proposal {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0)
            System.setIn(new FileInputStream(args[0]));
        Scanner in = new Scanner(System.in);
        int counter = 0;
        while (in.hasNextInt()) {
            int numRequirements = in.nextInt();
            int numProposals = in.nextInt();
            if (numProposals == 0 && numRequirements == 0)
                return;
            if (counter > 0)
                System.out.println();
            counter++;
            String bestProposalName = "";
            double bestProposalPrice = Double.MAX_VALUE;
            int bestCompliance = Integer.MIN_VALUE;
            in.nextLine();
            for (int i = 0 ; i < numRequirements; i++)
                in.nextLine();
            for (int i = 0 ; i < numProposals; i++) {
                String proposalName = in.nextLine();
                double price = in.nextDouble();
                int requirements = in.nextInt();
                in.nextLine();
                for (int j = 0 ; j < requirements; j++)
                    in.nextLine();
                if (requirements >= bestCompliance) {
                    if (requirements == bestCompliance) {
                        if (price < bestProposalPrice) {
                            bestProposalName = proposalName;
                            bestProposalPrice = price;
                        }
                    }
                    else {
                        bestProposalName = proposalName;
                        bestCompliance = requirements;
                        bestProposalPrice = price;
                    }
                }
            }
            System.out.printf("RFP #%d\n", counter);
            System.out.println(bestProposalName);
        }
    }
}
