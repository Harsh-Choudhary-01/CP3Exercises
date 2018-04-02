package Graphs.Sec4_4;

import java.util.*;
import java.io.*;

@SuppressWarnings("Duplicates")
class SpreadingTheNews {
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/SpreadingTheNews.txt"));
        int numEmployees = in.nextInt();
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numEmployees; i++) {
            adjList.add(new ArrayList<>());
            int edges = in.nextInt();
            for (int j = 0; j < edges; j++)
                adjList.get(i).add(in.nextInt());
        }
        int cases = in.nextInt();
        int[] daysTillHear = new int[numEmployees];
        for (int time = 1; time <= cases; time++) {
            Arrays.fill(daysTillHear, 100000);
            int start = in.nextInt();
            if (adjList.get(start).size() == 0) {
                System.out.println(0);
                continue;
            }
            Queue<Integer> verts = new ArrayDeque<>();
            verts.add(start);
            daysTillHear[start] = 0;
            HashMap<Integer, Integer> trackOfBoom = new HashMap<>();
            while (!verts.isEmpty()) {
                int source = verts.poll();
                for (int i = 0; i < adjList.get(source).size(); i++) {
                    int dest = adjList.get(source).get(i);
                    int dist = daysTillHear[source] + 1;
                    if (daysTillHear[dest] > dist) {
                        daysTillHear[dest] = dist;
                        verts.add(dest);
                        if (trackOfBoom.containsKey(dist))
                            trackOfBoom.put(dist, trackOfBoom.get(dist) + 1);
                        else
                            trackOfBoom.put(dist, 1);
                    }
                }
            }
            int maxDay = 0;
            int maxSize = 0;
            for (Map.Entry<Integer, Integer> entry : trackOfBoom.entrySet()) {
                if (entry.getValue() > maxSize) {
                    maxDay = entry.getKey();
                    maxSize = entry.getValue();
                }
            }
            System.out.println(maxSize + " " + maxDay);
        }
    }
}
