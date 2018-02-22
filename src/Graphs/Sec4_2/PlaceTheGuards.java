package Graphs.Sec4_2;

import java.util.*;
import java.io.*;

/*
Errors:
1. I wasn't marking the node as visited until I removed it from the queue. I should have marked it visited right away
2. I wasn't checking that if the min was 0 to make it 1 (to account for junctions with no adjacent junctions - they
still need a guard)
3. One error was a simple typo (i instead of color[i])
 */

class PlaceTheGuards {
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/PlaceTheGuards.txt"));
        int times = in.nextInt();
        for (int time = 1; time <= times; time++) {
            int v = in.nextInt();
            int edges = in.nextInt();
            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
            boolean[] visited = new boolean[v];
            int[] color = new int[v];
            for (int i = 1; i <= v; i++)
                adjList.add(new ArrayList<>());
            for (int i = 1; i <= edges; i++) {
                int first = in.nextInt();
                int second = in.nextInt();
                adjList.get(first).add(second);
                adjList.get(second).add(first);
            }
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(0);
            color[0] = 0;
            boolean isBipartite = true;
            boolean notDone = true;
            int total = 0;
            while (notDone) {
                int numZero = 1;
                int numOne = 0;
                while (!queue.isEmpty() && isBipartite) {
                    int val = queue.poll();
                    visited[val] = true;
                    for (int i = 0; i < adjList.get(val).size(); i++) {
                        int neighbor = adjList.get(val).get(i);
                        if (!visited[neighbor]) {
                            queue.add(neighbor);
                            visited[neighbor] = true;
                            color[neighbor] = 1 - color[val];
                            if (color[neighbor] == 1)
                                numOne++;
                            else
                                numZero++;
                        } else if (color[neighbor] == color[val]) {
                            isBipartite = false;
                            break;
                        }
                    }
                }
                if (!isBipartite) {
                    System.out.println(-1);
                    notDone = false;
                }
                else {
                    int min = Math.min(numOne, numZero);
                    if (min == 0)
                        min = 1;
                    total += min;
                    notDone = false;
                    for (int i = 0; i < v; i++)
                        if (!visited[i]) {
                            queue.add(i);
                            color[i] = 0;
                            notDone = true;
                            break;
                        }
                }
            }
            if (isBipartite)
                System.out.println(total);
        }

    }
}
