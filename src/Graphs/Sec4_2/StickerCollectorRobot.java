package Graphs.Sec4_2;

import java.util.*;
import java.io.*;

class StickerCollectorRobot {
    public static void main(String[] args) throws IOException {
        //Scanner in = new Scanner(System.in);
        Scanner in = new Scanner(new File("data/StickerCollectorRobot.txt"));
        while (in.hasNextInt()) {
            int rows = in.nextInt();
            int col = in.nextInt();
            int instructions = in.nextInt();
            if (rows + col + instructions == 0)
                return;
            int[][] grid = new int[rows][col];
            int direction = 0;
            int currentRow = 0;
            int currentCol = 0;
            for (int i = 0; i < rows ; i++) {
                String row = in.next();
                for (int j = 0; j < col; j++) {
                    if (row.charAt(j) == '#')
                        grid[i][j] = 2;
                    else if (row.charAt(j) == '*')
                        grid[i][j] = 1;
                    else if (row.charAt(j) == 'N') {
                        currentRow = i;
                        currentCol = j;
                        direction = 0;
                    }
                    else if (row.charAt(j) == 'L') {
                        currentRow = i;
                        currentCol = j;
                        direction = 1;
                    }
                    else if (row.charAt(j) == 'S') {
                        currentRow = i;
                        currentCol = j;
                        direction = 2;
                    }
                    else if (row.charAt(j) == 'O') {
                        currentRow = i;
                        currentCol = j;
                        direction = 3;
                    }
                }
            }
            int[] dx = {0, 1, 0, -1};
            int[] dy = {-1, 0, 1, 0};
            int sticker = 0;
            String instruction = in.next();
            for (int i = 0; i < instructions; i++) {
                char instruct = instruction.charAt(i);
                if (instruct == 'F') {
                    int targetX = dx[direction] + currentCol;
                    int targetY = dy[direction] + currentRow;
                    if (targetX >= 0 && targetX < col && targetY >= 0 && targetY < rows && grid[targetY][targetX] != 2) {
                        currentCol = targetX;
                        currentRow = targetY;
                        if (grid[currentRow][currentCol] == 1) {
                            sticker++;
                            grid[currentRow][currentCol] = 0;
                        }
                    }
                }
                else if (instruct == 'D')
                    direction = (direction + 1) % 4;
                else
                    direction = (direction + 3) % 4;
            }
            System.out.println(sticker);
        }
    }
}
