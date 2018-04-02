package Graphs.Sec4_3;

import java.util.*;
import java.io.*;

@SuppressWarnings("Duplicates")
class Audiophobia {
    static ArrayList<ArrayList<Map.Entry<Double, Integer>>> adjList = new ArrayList<>();
    static double maxSound = 0;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/Audiophobia.txt"));
        int caseNum = 0;
        while (in.hasNextInt()) {
            caseNum++;
            int numCrossings = in.nextInt();
            int numEdges = in.nextInt();
            int numQueries = in.nextInt();
            if (numCrossings + numEdges + numQueries == 0)
                return;
            if (caseNum > 1)
                System.out.println();
            ArrayList<Edge> edgeList = new ArrayList<>();
            for (int i = 0; i < numEdges; i++) {
                int first = in.nextInt();
                int second = in.nextInt();
                int weight = in.nextInt();
                edgeList.add(new Edge(weight, first - 1, second - 1));
            }
            UnionFind uf = new UnionFind(numCrossings);
            visited = new boolean[numCrossings];
            adjList.clear();
            for (int i = 0; i < numCrossings; i++)
                adjList.add(new ArrayList<>());
            Collections.sort(edgeList);
            for (Edge edge : edgeList) {
                if (!uf.isSameSet(edge.first, edge.second)) {
                    adjList.get(edge.first).add(new AbstractMap.SimpleEntry<>(edge.weight, edge.second));
                    adjList.get(edge.second).add(new AbstractMap.SimpleEntry<>(edge.weight, edge.first));
                    uf.unionSet(edge.first, edge.second);
                }
            }
            System.out.println("Case #" + caseNum);
            for (int i = 0; i < numQueries; i++) {
                int first = in.nextInt() - 1;
                int second = in.nextInt() - 1;
                Arrays.fill(visited, false);
                maxSound = 0;
                boolean found = find(first, second);
                if (found)
                    System.out.println((int)maxSound);
                else
                    System.out.println("no path");
            }
        }

    }

    static boolean find(int current, int dest) {
        visited[current] = true;
        for (Map.Entry<Double, Integer> edge : adjList.get(current)) {
            if (visited[edge.getValue()])
                continue;
            if (edge.getValue() == dest) {
                maxSound = Math.max(maxSound, edge.getKey());
                return true;
            }
            else {
                boolean found = find(edge.getValue(), dest);
                if (found) {
                    maxSound = Math.max(maxSound, edge.getKey());
                    return true;
                }
            }
        }
        return false;
    }

    static class Edge implements Comparable<Edge> {
        double weight;
        int first;
        int second;

        public Edge(double weight, int first, int second) {
            this.weight = weight;
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(weight, other.weight);
        }
    }

    static class UnionFind {
        private int[] parent, rank;
        int numDisjointSets;

        public UnionFind(int N) {
            numDisjointSets = N;
            parent = new int[N];
            rank = new int[N];
            for (int i = 0; i < N ; i++)
                parent[i] = i;
        }

        public int findSet(int i) {
            if (parent[i] == i)
                return i;
            else
                return parent[i] = findSet(parent[i]);
        }

        public boolean isSameSet(int i, int j) {
            return findSet(i) == findSet(j);
        }

        public void unionSet(int i, int j) {
            if (!isSameSet(i, j)) {
                numDisjointSets--;
                int x = findSet(i);
                int y = findSet(j);
                if (rank[x] > rank[y])
                    parent[y] = x;
                else {
                    parent[x] = y;
                    if (rank[x] == rank[y])
                        rank[y]++;
                }
            }
        }


    }
}
