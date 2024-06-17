public class Prelab {

    public static void main(String[] args) {
        int[][] matrix = new int[3][3];
        fillMatrixMinus1(matrix);

        System.out.println("Matrix filled with -1:");
        displayMatrix(matrix);
    }

    // Method to fill the matrix with a specified value
    public static void fillMatrixMinus1(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = -1;
            }
        }
    }

    // Method to display the matrix
    public static void displayMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col] + "\t");
            }
            System.out.println();
        }
    }
}