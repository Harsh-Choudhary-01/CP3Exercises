package Graphs.Sec4_2;

import java.util.*;
import java.io.*;

class Dominos {
    static int[] dfs_num;
    static int[] dfs_low;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> adjList;
    static ArrayList<ArrayList<Integer>> cc = new ArrayList<>();
    static Stack<Integer> stack;
    static int counter;
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/Dominos.txt"));
        int times = in.nextInt();
        for (int time = 1; time <= times; time++) {
            int n = in.nextInt();
            int edges = in.nextInt();
            adjList = new ArrayList<>();
            cc.clear();
            stack = new Stack<>();
            for (int i = 1; i <= n; i++)
                adjList.add(new ArrayList<>());
            dfs_low = new int[n];
            dfs_num = new int[n];
            for (int i = 0 ; i < n; i++)
                dfs_num[i] = -1;
            visited = new boolean[n];
            for (int i = 1; i <= edges; i++) {
                int x = in.nextInt() - 1;
                int y = in.nextInt() - 1;
                adjList.get(x).add(y);
            }
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
        stack.push(u);
        visited[u] = true;
        for (int i =  0; i < adjList.get(u).size(); i++) {
            int neighbor = adjList.get(u).get(i);
            if (dfs_num[neighbor] == -1)
                tarjanScc(neighbor);
            if (visited[neighbor])
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[neighbor]);
        }
        if (dfs_num[u] == dfs_low[u]) {
            ArrayList<Integer> seq = new ArrayList<>();
            cc.add(seq);
            while (true) {
                int pop = stack.pop();
                seq.add(pop);
                visited[pop] = false;
                if (pop == u)
                    break;
            }
        }
    }
}
