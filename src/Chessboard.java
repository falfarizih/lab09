import java.util.ArrayList;
import java.util.List;

public class Chessboard {
    private static final int SIZE = 8;
    private Queen[] queens;
    private List<Queen[]> solutions;

    public Chessboard() {
        queens = new Queen[SIZE];
        solutions = new ArrayList<>();
    }

    // Method to check if a position is safe for placing a queen
    public boolean isSafe(int row, int column) {
        for (int i = 0; i < column; i++) {
            Queen q = queens[i];
            if (q.getRow() == row || Math.abs(q.getRow() - row) == Math.abs(q.getColumn() - column)) {
                return false;
            }
        }
        return true;
    }

    // Method to check if any queens are threatening each other
    public boolean hasThreats() {
        for (int i = 0; i < SIZE; i++) {
            if (queens[i] != null) {
                for (int j = i + 1; j < SIZE; j++) {
                    if (queens[j] != null && !isSafe(queens[j].getRow(), queens[j].getColumn())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Method to place queens on the board
    public boolean placeQueens(int column) {
        if (column == SIZE) {
            solutions.add(queens.clone()); // Store the solution
            return true; // All queens are placed successfully
        }

        boolean foundSolution = false;
        for (int row = 0; row < SIZE; row++) {
            if (isSafe(row, column)) {
                queens[column] = new Queen(row, column);
                foundSolution = placeQueens(column + 1) || foundSolution;
                queens[column] = null; // Backtrack
            }
        }

        return foundSolution; // No safe position found in this column
    }
    // Method to print the board configuration
    public void printBoard(Queen[] queens) {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                boolean queenPlaced = false;
                for (Queen q : queens) {
                    if (q != null && q.getRow() == row && q.getColumn() == column) {
                        queenPlaced = true;
                        break;
                    }
                }
                if (queenPlaced) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Method to print all solutions
    public void printAllSolutions() {
        for (Queen[] solution : solutions) {
            printBoard(solution);
        }
        System.out.println("Total number of solutions: " + solutions.size());
    }
}