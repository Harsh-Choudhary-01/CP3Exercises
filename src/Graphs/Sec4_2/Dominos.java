package Graphs.Sec4_2;

import java.util.*;
import java.io.*;

/*
Errors:
1. My code was correct from the beginning, the only errors I had was a stack overflow.
I tried to fix this error by removing local variables in the recursive function but it
didn't help. In order to solve the problem I changed the code to an iterative algorithm.
 */
class Dominos {
    static int[] dfs_num;
    static int[] dfs_low;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> cc = new ArrayList<>();
    static Stack<Integer> visitedStack = new Stack<>();
    static int counter;
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/Dominos.txt"));
        int times = in.nextInt();
        for (int time = 1; time <= times; time++) {
            int n = in.nextInt();
            int edges = in.nextInt();
            adjList.clear();
            cc.clear();
            visitedStack.clear();
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
                    iterativeScc(i);
                }
            }
            System.out.println(cc.size());
        }
    }
    
    static void iterativeScc(int start) {
        Stack<Entry> stack = new Stack<>();
        stack.push(new Entry(start, true));
        while (!stack.isEmpty()) {
            Entry entry = stack.pop();
            int u = entry.rootVertex;
            if (entry.firstCall) {
                visitedStack.push(u);
                entry.firstCall = false;
                stack.push(entry);
                dfs_num[u] = dfs_low[u] = counter++;
                visited[u] = true;
                for (int i = 0; i < adjList.get(u).size(); i++)
                    if (dfs_num[adjList.get(u).get(i)] == -1)
                        stack.push(new Entry(adjList.get(u).get(i), true));
            }
            else {
                for (int i = 0; i < adjList.get(u).size(); i++) {
                    if (visited[adjList.get(u).get(i)])
                        dfs_low[u] = Math.min(dfs_low[u], dfs_low[adjList.get(u).get(i)]);
                    else {
                        for (int j = cc.size() - 1; j >= 0; j--) {
                            if (cc.get(j).contains(adjList.get(u).get(i)))
                                cc.remove(j);
                        }
                    }
                }
                if (dfs_num[u] == dfs_low[u]) {
                    ArrayList<Integer> seq = new ArrayList<>();
                    cc.add(seq);
                    while (true) {
                        int pop = visitedStack.pop();
                        seq.add(pop);
                        visited[pop] = false;
                        if (pop == u)
                            break;
                    }
                }
            }
        }
    }
    
    static class Entry {
        int rootVertex;
        boolean firstCall; //If false returning after recursive
        
        public Entry(int rootVertex, boolean firstCall) {
            this.rootVertex = rootVertex;
            this.firstCall = firstCall;
        }
    }
}
