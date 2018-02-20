package Graphs.Sec4_2;

import java.util.*;
import java.io.*;

class AncientMessages {
    static int[][] grid;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int whiteAreas = 0;
    static boolean fillingBlack = false;
    static int counter = 0;
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "A");
        map.put(3, "J");
        map.put(5, "D");
        map.put(4, "S");
        map.put(0, "W");
        map.put(2, "K");
        Scanner in = new Scanner(new File("data/AncientMessages.txt"));
        int caseNum = 0;
        while (in.hasNextInt()) {
            caseNum++;
            int height = in.nextInt();
            int numCharacters = in.nextInt();
            if (height + numCharacters == 0)
                return;
            grid = new int[height][numCharacters * 4];
            for (int row = 0; row < height; row++) {
                String line = in.next();
                int counter = 0;
                for (char c : line.toCharArray()) {
                    String binary = Integer.toBinaryString(Integer.parseInt("" + c, 16));
                    for (int i = 0 ; i < 4 - binary.length(); i++) {
                        grid[row][counter] = 0;
                        counter++;
                    }
                    for (char pixel : binary.toCharArray()) {
                        grid[row][counter] = pixel == '1' ? 1 : 0;
                        counter++;
                    }
                }
            }
            ArrayList<String> letters = new ArrayList<>();
            for (int i = 0 ; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 1)
                        break;
                    dfs(j, i,  0);
                }
            }
            for (int i = 0 ; i < grid.length; i++) {
                for (int j = grid[i].length - 1; j >= 0; j--) {
                    if (grid[i][j] == 1)
                        break;
                    dfs(j, i,  0);
                }
            }
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][grid[0].length - 1] == 0)
                    dfs(grid[0].length - 1, i , 0);
            }
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][0] == 0)
                    dfs(0, i , 0);
            }
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[0][j] == 0)
                    dfs(j, 0 , 0);
            }
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[grid.length - 1][j] == 0)
                    dfs(j, grid.length - 1 , 0);
            }
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 1) { //Found black pixel
                        whiteAreas = 0;
                        fillingBlack = true;
                        dfs(j, i,  1);
                        letters.add(map.get(whiteAreas));
                    }
                }
            }
            Collections.sort(letters);
            System.out.print("Case " + caseNum + ": ");
            for (Object letter : letters)
                System.out.print(letter);
            System.out.println();
        }
    }

    static void dfs(int x, int y , int currentColor) {
        if (x >= grid[0].length || x < 0 || y < 0 || y >= grid.length)
            return;
        if (grid[y][x] == 0 && fillingBlack) {
            whiteAreas++;
            grid[y][x] = -1;
            fillingBlack = false;
            for (int i = 0; i < 4; i++)
                dfs(x + dx[i], y + dy[i], 0);
            fillingBlack = true;
            return;
        }
        if (grid[y][x] != currentColor)
            return;
        grid[y][x] = -1;
        for (int i = 0; i < 4; i++)
            dfs(x + dx[i], y + dy[i], currentColor);
    }
}
