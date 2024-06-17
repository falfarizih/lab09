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

    public boolean isSafe(int row, int column) {
        for (int i = 0; i < column; i++) {
            Queen q = queens[i];
            if (q.getRow() == row || Math.abs(q.getRow() - row) == Math.abs(q.getColumn() - column)) {
                return false;
            }
        }
        return true;
    }

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

    public boolean placeQueens(int column) {
        if (column == SIZE) {
            solutions.add(queens.clone());
            return true;
        }

        boolean foundSolution = false;
        for (int row = 0; row < SIZE; row++) {
            if (isSafe(row, column)) {
                queens[column] = new Queen(row, column);
                foundSolution = placeQueens(column + 1) || foundSolution;
                queens[column] = null;
            }
        }

        return foundSolution;
    }

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

    public void printAllSolutions() {
        for (Queen[] solution : solutions) {
            printBoard(solution);
        }
        System.out.println("Total number of solutions: " + solutions.size());
    }
}