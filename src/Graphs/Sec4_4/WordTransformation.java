package Graphs.Sec4_4;

import java.util.*;
import java.io.*;

/*
Errors:
1. Wasn't printing blank line in between test cases
 */

@SuppressWarnings("Duplicates")
class WordTransformation {
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/WordTransformation.txt"));
        int times = in.nextInt();
        for (int time = 1; time <= times; time++) {
            if (time != 1)
                System.out.println();
            ArrayList<String> dict = new ArrayList<>();
            String word = in.next();
            while (!word.equals("*")) {
                dict.add(word);
                word = in.next();
            }
            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < dict.size(); i++)
                adjList.add(new ArrayList<>());
            for (int i = 0; i < dict.size(); i++) {
                for (int j = i + 1; j < dict.size(); j++) {
                    if (numDiffs(dict.get(i), dict.get(j)) == 1) {
                        adjList.get(i).add(j);
                        adjList.get(j).add(i);
                    }
                }
            }
            in.nextLine();
            String[] pair = in.nextLine().split(" ");
            while (pair.length == 2) {
                int start = dict.indexOf(pair[0]);
                int finish = dict.indexOf(pair[1]);
                Queue<Integer> vertices = new ArrayDeque<>();
                int[] distance = new int[dict.size()];
                Arrays.fill(distance, 10000);
                distance[start] = 0;
                vertices.add(start);
                outer: while (!vertices.isEmpty()) {
                    int vert = vertices.poll();
                    for (int i = 0; i < adjList.get(vert).size(); i++) {
                        int dest = adjList.get(vert).get(i);
                        if (distance[dest] > distance[vert] + 1) {
                            distance[dest] = distance[vert] + 1;
                            vertices.add(dest);
                            if (dest == finish)
                                break outer;
                        }
                    }
                }
                System.out.printf("%s %s %d\n", pair[0], pair[1], distance[finish]);
                if (!in.hasNextLine())
                    return;
                pair = in.nextLine().split(" ");
            }
        }
    }

    static int numDiffs(String string1, String string2) {
        if (string1.length() != string2.length())
            return 5;
        int diff = 0;
        for (int i = 0; i < string1.length(); i++)
            if (string1.charAt(i) != string2.charAt(i))
                diff++;
        return diff;
    }
}
