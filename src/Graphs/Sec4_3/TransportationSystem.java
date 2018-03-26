package Graphs.Sec4_3;

import java.util.*;
import java.io.*;

/*
Errors:
1. The only error I had was in sorting my edge list. I was doing it (int)(weight - other.weight) and
this lost precision in the cost. Changing to Double.compare fixed the issue.
 */

class TransportationSystem {
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/TransportationSystem.txt"));
         int times = in.nextInt();
         for (int time = 1; time <= times; time++) {
             int numCities = in.nextInt();
             int threshold = in.nextInt();
             ArrayList<Map.Entry<Integer, Integer>> cities = new ArrayList<>();
             ArrayList<IntegerTriple> edgeList = new ArrayList<>();
             for (int city = 1; city <= numCities; city++)
                 cities.add(new AbstractMap.SimpleEntry<>(in.nextInt(), in.nextInt()));
             for (int i = 0; i < cities.size(); i++) {
                 for (int j = i + 1; j < cities.size(); j++) {
                     double dist = Math.sqrt(Math.pow(cities.get(i).getKey() - cities.get(j).getKey(), 2)
                             + Math.pow(cities.get(i).getValue() - cities.get(j).getValue(), 2));
                     edgeList.add(new IntegerTriple(dist, i, j));
                 }
             }
             Collections.sort(edgeList);
             UnionFind UF = new UnionFind(numCities);
             int numStates = 1;
             double railCost = 0;
             double roadCost = 0;
             for (IntegerTriple edge : edgeList) {
                 if (!UF.isSameSet(edge.first, edge.second)) {
                     if (edge.weight > threshold) {
                         railCost += edge.weight;
                         numStates++;
                     }
                     else
                         roadCost += edge.weight;
                     UF.unionSet(edge.first, edge.second);
                 }
             }
             System.out.printf("Case #%d: %d %d %d\n", time, numStates, (int)Math.round(roadCost), (int)Math.round(railCost));
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


