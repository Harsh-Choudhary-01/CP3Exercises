package Graphs.Sec4_2;

import java.util.*;
import java.io.*;

class Network {
    static int[] dfs_parent;
    static int[] dfs_num;
    static int[] dfs_low;
    static int counter = 0;
    static int dfsRoot;
    static int rootChildren = 0;
    static ArrayList<ArrayList<Integer>> adjList;
    static HashSet<Integer> points= new HashSet<>();
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("data/Network.txt"));
        //Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int numPlaces = in.nextInt();
            if (numPlaces == 0)
                return;
            dfs_num = new int[numPlaces];
            for (int i = 0; i < dfs_num.length; i++)
                dfs_num[i] = -1;
            dfs_parent = new int[numPlaces];
            dfs_low = new int[numPlaces];
            points.clear();
            adjList = new ArrayList<>();
            for (int i = 1; i <= numPlaces; i++)
                adjList.add(new ArrayList<>());
            in.nextLine();
            while (in.hasNextLine()) {
                String[] edges = in.nextLine().split(" ");
                if (edges.length == 1)
                    break;
                for (int i = 1; i < edges.length; i++) {
                    int first = Integer.parseInt(edges[0]);
                    int second = Integer.parseInt(edges[i]);
                    adjList.get(first - 1).add(second - 1);
                    adjList.get(second - 1).add(first - 1);
                }
            }
            for (int i = 0; i < numPlaces; i++) {
                if (dfs_num[i] == -1) {
                    dfsRoot = i;
                    rootChildren = 0;
                    articulationPoint(i);
                    if (rootChildren > 1)
                        points.add(dfsRoot);
                    else
                        points.remove(new Integer(dfsRoot));
                }
            }
            System.out.println(points.size());
        }
    }

    static void articulationPoint(int u) {
        dfs_low[u] = dfs_num[u] = counter++;
        for (int i = 0; i < adjList.get(u).size(); i++) {
            int neighbor = adjList.get(u).get(i);
            if (dfs_num[neighbor] == -1) {
                dfs_parent[neighbor] = u;
                if (u == dfsRoot)
                    rootChildren++;

                articulationPoint(neighbor);

                if (dfs_low[neighbor] >= dfs_num[u])
                    points.add(u);

                dfs_low[u] = Math.min(dfs_low[u], dfs_low[neighbor]);
            }
            else if (dfs_parent[u] != neighbor)
                dfs_low[u] = Math.min(dfs_low[u], dfs_num[neighbor]);
        }
    }
}
