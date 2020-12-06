package algorithms.backtracking;

public class SudokuSolver {

    /**
     * Solves a 9 x 9 sudoku board
     * 
     * @param board
     */
    public static void solve(int[][] board) {
        if (board.length != 9)
            return;
        if (board[0].length != 9)
            return;

        solveHelper(board);
    }

    /**
     * Solves a valid 9 x 9 sudoku board
     * 
     * @param board
     * @return
     */
    public static boolean solveHelper(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    for (int i = 1; i <= 9; i++) {
                        if (isPossible(board, row, col, i)) {
                            board[row][col] = i;
                            if (solveHelper(board))
                                return true;
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns true if it is possible to place a given value on a sudoku board
     * 
     * @param board
     * @param row
     * @param col
     * @param val
     * @return boolean
     */
    public static boolean isPossible(int[][] board, int row, int col, int val) {
        if (board[row][col] != 0)
            return false;

        // Check the row
        for (int i = 0; i < board.length; i++) {
            if (i == row)
                continue;
            if (board[i][col] == val)
                return false;
        }

        // Check the colummn
        for (int i = 0; i < board[0].length; i++) {
            if (i == col)
                continue;
            if (board[row][i] == val)
                return false;
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        // Check the inner square
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (r == row && c == col)
                    continue;
                if (board[r][c] == val)
                    return false;
            }
        }

        return true;
    }

    /**
     * Prints a given 2d array
     * 
     * @param board
     */
    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            System.out.print("[ ");
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        int[][] board = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

        solve(board);
        printBoard(board);
    }
}