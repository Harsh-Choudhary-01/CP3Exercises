package Graphs.Sec4_2;

import java.util.*;
import java.io.*;

class Ordering {
    static ArrayList<Integer> sequence = new ArrayList<>();
    static boolean[] visited;
    static int[] inEdges;
    static boolean printed = false;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    static ArrayList<String> valuesOfNums = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("data/Ordering.txt"));
        //Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        in.nextLine();
        for (int time = 1; time <= times; time++) {
            in.nextLine();
            valuesOfNums.clear();
            adjList.clear();
            printed = false;
            sequence.clear();
            String[] vars = in.nextLine().split(" ");
            for (String s : vars) {
                valuesOfNums.add(s);
                adjList.add(new ArrayList<>());
            }
            Collections.sort(valuesOfNums);
            visited = new boolean[adjList.size()];
            inEdges = new int[adjList.size()];
            String[] relations = in.nextLine().split(" ");
            for (String s : relations) {
                String[] vals = s.split("<");
                int index0 = valuesOfNums.indexOf(vals[0]);
                int index1 = valuesOfNums.indexOf(vals[1]);
                adjList.get(index0).add(index1);
                inEdges[index1]++;
            }

            kahn();
            if (!printed)
                System.out.println("NO");
            if (time != times)
                System.out.println();
        }
    }

    static void kahn() {
        boolean flag = false;

        for (int i = 0; i < adjList.size(); i++) {
            if (inEdges[i] == 0 && !visited[i]) {
                for (int adj = 0; adj < adjList.get(i).size(); adj++) {
                    inEdges[adjList.get(i).get(adj)]--;
                }

                sequence.add(i);
                visited[i] = true;
                kahn();

                visited[i] = false;
                sequence.remove(sequence.size() - 1);
                for (int adj = 0; adj < adjList.get(i).size(); adj++) {
                    inEdges[adjList.get(i).get(adj)]++;
                }

                flag = true;
            }
        }

        if (!flag) {
            if (sequence.size() != adjList.size())
                return;
            printed = true;
            for (int i = 0; i < sequence.size(); i++) {
                if (i != 0)
                    System.out.print(" ");
                System.out.print(valuesOfNums.get(sequence.get(i)));
            }
            System.out.println();
        }
    }
}
