package Graphs.Sec4_3;

import java.util.*;
import java.io.*;

/*
Errors:
1. Missing \n at the end of the printf all test cases were printing on the same line
2. My approach was wrong at first, this problem was in fact stopping at a certain number of disjoint sets
(the satellite channels will connect these disjoint sets with no cost)
 */
@SuppressWarnings("Duplicates")
class ArticNetwork {
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/ArticNetwork.txt"));
        int times = in.nextInt();
        for (int time = 1; time <= times; time++) {
            int numChannels = in.nextInt();
            int numOutposts = in.nextInt();
            ArrayList<Edge> edgeList = new ArrayList<>();
            UnionFind uf = new UnionFind(numOutposts);
            ArrayList<Map.Entry<Integer, Integer>> points = new ArrayList<>();
            for (int i = 0; i < numOutposts; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                for (int j = 0; j < points.size(); j++) {
                    Map.Entry<Integer, Integer> point = points.get(j);
                    edgeList.add(new Edge(Math.sqrt(Math.pow(x - point.getKey(), 2) + Math.pow(y - point.getValue(), 2)), i, j));
                }
                points.add(new AbstractMap.SimpleEntry<>(x, y));
            }
            Collections.sort(edgeList);
            int neededSets = numChannels > 0 ? numChannels : 1;
            double neededRange = 0;
            for (Edge edge : edgeList) {
                if (!uf.isSameSet(edge.first, edge.second)) {
                    uf.unionSet(edge.first, edge.second);
                    neededRange = Math.max(neededRange, edge.weight);
                    if (uf.numDisjointSets == neededSets)
                        break;
                }
            }
            System.out.printf("%.2f\n", neededRange);
        }
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
