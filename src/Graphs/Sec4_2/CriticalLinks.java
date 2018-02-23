package Graphs.Sec4_2;

import java.util.*;
import java.io.*;

class CriticalLinks {
    static int[] dfs_num;
    static int[] dfs_low;
    static int[] dfs_parent;
    static int counter = -1;
    static ArrayList<ArrayList<Integer>> adjList;
    static ArrayList<IntegerPair> edges = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/CriticalLinks.txt"));
        while (in.hasNextInt()) {
            int num = in.nextInt();
            dfs_low = new int[num];
            dfs_num = new int[num];
            dfs_parent = new int[num];
            counter = 0;
            adjList = new ArrayList<>();
            for (int i = 1; i <= num; i++)
                adjList.add(new ArrayList<>());
            for (int i = 0; i < num; i++)
                dfs_num[i] = -1;
            edges.clear();
            for (int i = 1; i <= num; i++) {
                int first = in.nextInt();
                String linkInfo = in.next();
                int numLinks = Integer.parseInt(linkInfo.substring(1, linkInfo.length() - 1));
                for (int j = 1; j <= numLinks; j++)
                    adjList.get(first).add(in.nextInt());
            }
            for (int i = 0; i < num; i++) {
                if (dfs_num[i] == -1)
                    articulationBridge(i);
            }
            Collections.sort(edges);
            System.out.printf("%d critical links\n", edges.size());
            for (IntegerPair pair : edges)
                System.out.printf("%d - %d\n", pair.getKey(), pair.getValue());
            System.out.println();
        }
    }

    static void articulationBridge(int u) {
        dfs_num[u] = dfs_low[u] = counter++;
        for (int i = 0; i < adjList.get(u).size(); i++) {
            int neighbor = adjList.get(u).get(i);

            if (dfs_num[neighbor] == -1) {
                dfs_parent[neighbor] = u;
                articulationBridge(neighbor);

                if (dfs_low[neighbor] > dfs_num[u])
                    edges.add(new IntegerPair(Math.min(u, neighbor), Math.max(u, neighbor)));

                dfs_low[u] = Math.min(dfs_low[u], dfs_low[neighbor]);
            }
            else if (dfs_parent[u] != neighbor) {
                dfs_low[u] = Math.min(dfs_low[u], dfs_num[neighbor]);
            }
        }
    }
}

class IntegerPair implements Comparable<IntegerPair> {
    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    private int key;
    private int value;

    public IntegerPair(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return key + "=" + value;
    }

    public int hashCode() {
        return key * 13 + value;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof IntegerPair) {
            IntegerPair pair = (IntegerPair) o;
            if (key != pair.key) return false;
            if (value != pair.value) return false;
            return true;
        }
        return false;
    }

    public int compareTo(IntegerPair o) {
        int diff =  key - o.key;
        if (diff != 0)
            return diff;
        else
            return value - o.value;
    }

}
