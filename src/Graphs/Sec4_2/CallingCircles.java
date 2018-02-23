package Graphs.Sec4_2;

import java.util.*;
import java.io.*;

class CallingCircles {
    static int[] dfs_num;
    static int[] dfs_low;
    static boolean[] visited;
    static int counter = 0;
    static Stack<Integer> stack;
    static ArrayList<ArrayList<Integer>> adjList;
    static ArrayList<String> names;
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/CallingCircles.txt"));
        int count = 0;
        while (in.hasNextInt()) {
            count++;
            int n = in.nextInt();
            int m = in.nextInt();
            if (n + m == 0)
                return;
            if (count != 1)
                System.out.println();
            names = new ArrayList<>();
            adjList = new ArrayList<>();
            stack = new Stack<>();
            dfs_low = new int[n];
            dfs_num = new int[n];
            visited = new boolean[n];
            for (int i = 0; i < n; i++)
                dfs_num[i] = -1;
            for (int i = 0; i < n; i++)
                adjList.add(new ArrayList<>());
            for (int i = 0; i < m; i++) {
                String first = in.next();
                String second = in.next();
                int firstInd = names.indexOf(first);
                int secondInd = names.indexOf(second);
                if (firstInd == -1) {
                    names.add(first);
                    firstInd = names.size() - 1;
                }
                if (secondInd == -1) {
                    names.add(second);
                    secondInd = names.size() - 1;
                }
                if (!adjList.get(firstInd).contains(secondInd))
                    adjList.get(firstInd).add(secondInd);
            }
            System.out.printf("Calling circles for data set %d:\n", count);
            for (int i = 0; i < n; i++) {
                if (dfs_num[i] == -1) {
                    counter = 0;
                    tarjanScc(i);
                }
            }
        }
    }

    static void tarjanScc(int u) {
        dfs_num[u] = dfs_low[u] = counter++;
        visited[u] = true;
        stack.push(u);
        for (int i = 0; i < adjList.get(u).size(); i++) {
            int neighbor = adjList.get(u).get(i);
            if (dfs_num[neighbor] == -1)
                tarjanScc(neighbor);
            if (visited[neighbor])
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[neighbor]);
        }

        if (dfs_low[u] == dfs_num[u]) {
            ArrayList<Integer> seq = new ArrayList<>();
            while (true) {
                int pop = stack.pop();
                seq.add(pop);
                visited[pop] = false;
                if (pop == u)
                    break;
            }
            for (int i = 0; i < seq.size(); i++) {
                if (i != 0)
                    System.out.print(", ");
                System.out.print(names.get(seq.get(i)));
            }
            System.out.println();
        }
    }
}
