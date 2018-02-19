package Graphs.Sec4_2;

import java.util.*;
import java.io.*;

/*
Errors:
1. Wasn't resetting numOdd and numEven between cases
2. Was counting adjacent nodes that knight can't reach instead of can (== 1 instead of != 1)
3. Was running DFS on nodes that had water (didn't have additional && grid[targetY][targetX] != 1)
4. This was causing Runtime error: xi and yi given for water position was actually y and x
//(they were treating x as the row coordinate)
5. I wasn't removing duplicate moves when M == 0 || N == 0 || M == N
 */
class KnightWar {
    static int[][] grid;
    static boolean[][] visited;
    static HashSet<Map.Entry<Integer, Integer>> set = new HashSet<>();
    static int M;
    static int N;
    static int numOdd = 0;
    static int numEven = 0;

    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/KnightWar.txt"));
        int times = in.nextInt();
        for (int i = 1; i <= times; i++) {
            numOdd = 0;
            numEven = 0;
            int R = in.nextInt();
            int C = in.nextInt();
            M = in.nextInt();
            N = in.nextInt();
            int W = in.nextInt();
            grid = new int[R][C];
            visited = new boolean[R][C];
            int[] dx = {-M, -M, M, M, N, N, -N, -N};
            int[] dy = {-N, N, -N, N, M, -M, M, -M};
            set.clear();
            for (int j = 0; j < 8; j++)
                set.add(new AbstractMap.SimpleEntry<>(dx[j], dy[j]));
            for (int j = 1; j <= W; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                grid[x][y] = 1;
            }
            dfs(0, 0);
            System.out.printf("Case %d: %d %d\n", i, numEven, numOdd);
        }
    }

    static void dfs(int x, int y) {
        int count = 0;
        visited[y][x] = true;
        for (Map.Entry<Integer, Integer> pair : set) {
            int targetX = pair.getKey() + x;
            int targetY = pair.getValue() + y;
            if (targetX >= 0 && targetX < grid[0].length && targetY >= 0 && targetY < grid.length) {
                if (grid[targetY][targetX] != 1)
                    count++;
                if (!visited[targetY][targetX] && grid[targetY][targetX] != 1)
                    dfs(targetX, targetY);
            }
        }
        if (count % 2 == 0)
            numEven++;
        else
            numOdd++;
    }
}
