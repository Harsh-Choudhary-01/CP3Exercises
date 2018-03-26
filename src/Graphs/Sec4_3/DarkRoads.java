package Graphs.Sec4_3;

import java.util.*;
import java.io.*;

@SuppressWarnings("Duplicates")
class DarkRoads {

    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/DarkRoads.txt"));
        while (in.hasNextInt()) {
            int numVertices = in.nextInt();
            int edgeCount = in.nextInt();
            int totalCost = 0;
            if (numVertices + edgeCount == 0)
                return;
            UnionFind uf = new UnionFind(numVertices);
            ArrayList<IntegerTriple> edgeList = new ArrayList<>();
            for (int num = 1; num <= edgeCount; num++) {
                int first = in.nextInt();
                int second = in.nextInt();
                int weight = in.nextInt();
                totalCost += weight;
                edgeList.add(new IntegerTriple(weight, first, second));
            }
            Collections.sort(edgeList);
            double min_cost = 0;
            for (IntegerTriple edge : edgeList) {
                if (!uf.isSameSet(edge.first, edge.second)) {
                    min_cost += edge.weight;
                    uf.unionSet(edge.first, edge.second);
                }
            }
            System.out.println((int)(totalCost - min_cost));
        }
    }

    static class IntegerTriple implements Comparable<IntegerTriple> {
        double weight;
        int first;
        int second;

        public IntegerTriple(double weight, int first, int second) {
            this.weight = weight;
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(IntegerTriple other) {
            return Double.compare(weight, other.weight);
        }


    }

    static class UnionFind {
        private int[] parent, rank;
        int numDisjointSets;

        public UnionFind(int n) {
            rank = new int[n];
            parent = new int[n];
            numDisjointSets = n;
            for (int i = 0; i < n ; i++)
                parent[i] = i;
        }

        public int findSet(int i) {
            if (parent[i] == i)
                return i;
            else {
                parent[i] = findSet(parent[i]);
                return parent[i];
            }
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

