package Graphs.Sec4_2;

import java.util.*;
import java.io.*;

class Bicoloring {
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/Bicoloring.txt"));
        while (in.hasNextInt()) {
            int nodes = in.nextInt();
            if (nodes == 0)
                return;
            boolean[] visited = new boolean[nodes];
            int[] color = new int[nodes];
            int edges = in.nextInt();
            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
            for (int i = 1; i <= nodes; i++)
                adjList.add(new ArrayList<>());
            for (int i = 1; i <= edges; i++) {
                int first = in.nextInt();
                int second = in.nextInt();
                adjList.get(first).add(second);
                adjList.get(second).add(first);
            }
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(0);
            color[0] = 1;
            boolean isBicolorable = true;
            outer: while (!queue.isEmpty()) {
                int val = queue.poll();
                for (int i = 0; i < adjList.get(val).size(); i++) {
                    int neighbor = adjList.get(val).get(i);
                    if (!visited[neighbor]) {
                        queue.add(neighbor);
                        visited[neighbor] = true;
                        color[neighbor] = 1 - color[val];
                    }
                    else if (color[neighbor] == color[val]) {
                        isBicolorable = false;
                        break outer;
                    }
                }
            }
            System.out.println(isBicolorable ? "BICOLORABLE." : "NOT BICOLORABLE.");
        }
    }
}
