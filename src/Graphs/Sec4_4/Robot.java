//package Graphs.Sec4_4;

import java.util.*;
import java.io.*;

/*
Notes: This problem took me forever to code - I really need to practice this
Errors:
1. Directions corresponding to orients were wrong
2. I was moving through obstacles as long as the final destination was inhabitable, needed to break move loop as soon as one couldn't be reached
3. I was marking moving along the edge of the grid as not allowed
4. I wasn't checking if finish location was same as start location
5. This is dumb, the problem made it seem like traversing on the edges was allowed, so in fact my error correction (3) was actually making
the problem less solved and if I had gone ahead and fixed error #4 I would have been done. Reversed my "fix #3" to get accepted
 */

@SuppressWarnings("Duplicates")
class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        //Scanner in = new Scanner(new File("data/Robot.txt"));
        //North - 0, East - 1, South - 2, West - 3
        while (in.hasNextInt()) {
            int numRows = in.nextInt();
            int numColumns = in.nextInt();
            if (numColumns + numRows == 0)
                return;
            int[][] grid = new int[numRows][numColumns];
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++) {
                    grid[i][j] = in.nextInt();
                }
            }
            int startRow = in.nextInt();
            int startColumn = in.nextInt();
            int finishRow = in.nextInt();
            int finishColumn = in.nextInt();
            String direction = in.next();
            int orientation = 0;
            switch (direction) {
                case "north":
                    orientation = 0;
                    break;
                case "east":
                    orientation = 1;
                    break;
                case "south":
                    orientation = 2;
                    break;
                case "west":
                    orientation = 3;
                    break;
            }
            //HashMap<RobotState, Integer> distance = new LinkedHashMap<>();
            int[][][] distance = new int[numRows][numColumns][4];
            int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            int[][] neighbors = { {0, 0}, {-1, 0}, {0, -1}, {-1, -1}};
            Queue<RobotState> queue = new ArrayDeque<>();
            RobotState initial = new RobotState(startRow, startColumn, orientation);
            for (int i = 0; i < distance.length; i++)
                for (int j = 0; j < distance[i].length; j++)
                    Arrays.fill(distance[i][j], 1000000);
            distance[startRow][startColumn][orientation] = 0;
            queue.add(initial);
            int shortestDistance = 1000000;
            if (startRow == finishRow && startColumn == finishColumn) {
                shortestDistance = 0;
                queue.clear();
            }
            outer: while (!queue.isEmpty()) {
                RobotState state = queue.poll();
                RobotState left = new RobotState(state.row, state.column, (state.orient + 3) % 4);
                if (distance[left.row][left.column][left.orient] > distance[state.row][state.column][state.orient] + 1) {
                    queue.add(left);
                    distance[left.row][left.column][left.orient] = distance[state.row][state.column][state.orient] + 1;
                }
                RobotState right = new RobotState(state.row, state.column, (state.orient + 1) % 4);
                if (distance[right.row][right.column][right.orient] > distance[state.row][state.column][state.orient] + 1) {
                    queue.add(right);
                    distance[right.row][right.column][right.orient] = distance[state.row][state.column][state.orient] + 1;
                }
                for (int move = 1; move <= 3; move++) {
                    int targetRow = state.row + dir[state.orient][0] * move;
                    int targetColumn = state.column + dir[state.orient][1] * move;
                    boolean noObstacle = targetRow >= 0 && targetRow < numRows && targetColumn >= 0 && targetColumn < numColumns;
                    for (int i = 0; i < neighbors.length; i++) {
                        int tRow = targetRow + neighbors[i][0];
                        int tCol = targetColumn + neighbors[i][1];
                        boolean inBounds = tRow >= 0 && tRow < numRows && tCol >= 0 && tCol < numColumns;
                        noObstacle = noObstacle && inBounds && grid[tRow][tCol] == 0;
                    }
                    if (noObstacle) {
                        if (targetRow == finishRow && targetColumn == finishColumn) {
                            shortestDistance = Math.min(shortestDistance, distance[state.row][state.column][state.orient] + 1);
                            break outer;
                        }
                        RobotState newState = new RobotState(targetRow, targetColumn, state.orient);
                        if (distance[newState.row][newState.column][newState.orient] > distance[state.row][state.column][state.orient] + 1) {
                            distance[newState.row][newState.column][newState.orient] = distance[state.row][state.column][state.orient] + 1;
                            queue.add(newState);
                        }
                    }
                    else
                        break;
                }
            }
            if (shortestDistance == 1000000)
                System.out.println(-1);
            else
                System.out.println(shortestDistance);
        }
    }

    static class RobotState {
        @Override
        public String toString() {
            return "RobotState{" +
                    "row=" + row +
                    ", column=" + column +
                    ", orient=" + orient +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            RobotState that = (RobotState) o;

            if (row != that.row) return false;
            if (column != that.column) return false;
            return orient == that.orient;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + column;
            result = 31 * result + orient;
            return result;
        }

        int row, column, orient;

        public RobotState(int row, int column, int orient) {
            this.row = row;
            this.column = column;
            this.orient = orient;
        }
    }
}
