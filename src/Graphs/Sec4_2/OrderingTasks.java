package Graphs.Sec4_2;

import java.util.*;
import java.io.*;

class OrderingTasks {
    static boolean[] visited;
    static ArrayList<Integer> sequence;
    static ArrayList<ArrayList<Integer>> adjList;
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/OrderingTasks.txt"));
        while (true) {
            int n = in.nextInt();
            int m = in.nextInt();
            sequence = new ArrayList<>();
            visited = new boolean[n];
            if (n + m == 0)
                return;
            adjList = new ArrayList<>();
            for (int i = 0; i < n; i++)
                adjList.add(new ArrayList<>());
            for (int i = 1; i <= m ; i++) {
                int first = in.nextInt() - 1;
                int second = in.nextInt() - 1;
                adjList.get(first).add(second);
            }
            for (int i = 0; i < n ; i++) {
                if (!visited[i])
                    dfs(i);
            }
            for (int i = sequence.size() - 1; i >= 0; i--) {
                System.out.print((sequence.get(i) + 1) + " ");
            }
            System.out.println();
        }
    }

    static void dfs(int current) {
        visited[current] = true;
        for (Integer adj : adjList.get(current)) {
            if (!visited[adj])
                dfs(adj);
        }
        sequence.add(current);
    }
}
