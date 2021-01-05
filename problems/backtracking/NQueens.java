package problems.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
 * such that no two queens attack each other.
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space,
 * respectively.
 * 
 * @author Luis Preciado <luisapreciado99@gmail.com>
 */
public class NQueens {

    /**
     * Returns a list containings all possible solution to N Queens problem given an
     * n x n board.
     */
    public static List<List<String>> solution(int n) {
        int[][] grid = new int[n][n];
        List<List<String>> out = new ArrayList<>();
        solve(grid, out, 0, 0, 0);
        return out;
    }

    public static void solve(int[][] grid, List<List<String>> out, int startRow, int startCol, int numQueens) {

        if (numQueens == grid.length) {
            addToList(grid, out);
            return;
        }

        for (int row = startRow; row < grid.length; row++) {
            for (int col = row == startRow ? startCol : 0; col < grid.length; col++) {
                if (isPossible(grid, row, col, '*')) {
                    grid[row][col] = 1;
                    solve(grid, out, row, col + 1, numQueens + 1);
                    grid[row][col] = 0;
                }
            }
        }

    }

    public static void addToList(int[][] grid, List<List<String>> list) {
        List<String> board = new LinkedList<>();
        for (int[] row : grid) {
            StringBuilder builder = new StringBuilder();
            for (int val : row) {
                builder.append(val == 1 ? 'Q' : '.');
            }
            board.add(builder.toString());
        }
        list.add(board);
    }

    public static boolean isPossible(int[][] grid, int row, int col, char dir) {
        if (row < 0 || row >= grid.length)
            return true;
        if (col < 0 || col >= grid[row].length)
            return true;
        if (grid[row][col] == 1)
            return false;
        boolean u, r, d, l, ul, ur, dl, dr;
        u = r = d = l = ul = ur = dl = dr = true;
        if (dir == 'U' || dir == '*') {
            u = isPossible(grid, row - 1, col, 'U');
        }

        if (dir == 'D' || dir == '*') {
            d = isPossible(grid, row + 1, col, 'D');
        }

        if (dir == 'L' || dir == '*') {
            l = isPossible(grid, row, col - 1, 'L');
        }

        if (dir == 'R' || dir == '*') {
            r = isPossible(grid, row, col + 1, 'R');
        }

        if (dir == '1' || dir == '*') {
            ul = isPossible(grid, row - 1, col - 1, '1');
        }

        if (dir == '2' || dir == '*') {
            ur = isPossible(grid, row - 1, col + 1, '2');
        }

        if (dir == '3' || dir == '*') {
            dl = isPossible(grid, row + 1, col - 1, '3');
        }

        if (dir == '4' || dir == '*') {
            dr = isPossible(grid, row + 1, col + 1, '4');
        }

        return u && d && r && l && ul && ur && dl && dr;
    }

    public static void main(String[] args) {
        System.out.println(solution(5));
    }
}