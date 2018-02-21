package Graphs.Sec4_2;

import java.util.*;
import java.io.*;

/*
Gosh this was such a super simple problem but it took me 6 tries to get it right
Errors:
1. I was assuming land and water characters would always be 'w' and 'l' but this was never specified
2. I wasn't resetting max between trials
3. Number regions could be zero (no other continents) so max had to be set to 0 not Integer.MIN_VALUE
4. I was wrapping between top and bottom of map when the problem said to wrap left and right edges
*/

class Continents {
    static int M;
    static int N;
    static char[][] grid;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int counter = 0;
    static int max = 0;
    static char landChar = 'a';
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/Continents.txt"));
        while(in.hasNextInt()) {
            max = 0;
            M = in.nextInt();
            N = in.nextInt();
            grid = new char[M][N];
            for (int i = 0; i < M; i++) {
                String row = in.next();
                for (int j = 0 ; j < N ; j++)
                    grid[i][j] = row.charAt(j);
            }
            int currentY = in.nextInt();
            int currentX = in.nextInt();
            landChar = grid[currentY][currentX];
            dfs(currentY, currentX);
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] == landChar) {
                        counter = 0;
                        dfs(i, j);
                        if (counter > max)
                            max = counter;
                    }
                }
            }
            System.out.println(max);
        }
    }

    static void dfs(int row, int col) {
        if (col == -1)
            col = N - 1;
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length)
            return;
        if (grid[row][col] != landChar)
            return;
        counter++;
        grid[row][col] = 0; //visited
        for (int i = 0; i < 4; i++)
            dfs(row + dy[i], (col + dx[i]) % N);
    }
}
