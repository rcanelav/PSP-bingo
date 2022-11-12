import java.util.HashSet;
import java.util.Set;

public class BingoCard {
    // Make a Bingo Card 3x3
    // A Bingo Card has a 3x3 matrix
    int[][] matrix;
    public BingoCard() {
        this.matrix = new int[3][9];
        
        for (int i = 0; i < matrix.length; i++) {
            int[] row = matrix[i];
            // Fill the row with 0
            for (int j = 0; j < row.length; j++) {
                row[j] = 0;
            }
            // Create a set 
            Set<Integer> randomNumbers = new HashSet<>();
            // Generate 5 random numbers between 1 and 99
            while (randomNumbers.size() < 5) {
                randomNumbers.add((int) (Math.random() * 99) + 1);
            }
            Object[] randomNumbersArray = randomNumbers.toArray();

            // Generate 5 random positions between 0 and 9
            Set<Integer> randomPositions = new HashSet<>();
            while (randomPositions.size() < 5) {
                randomPositions.add((int) (Math.random() * 9));
            }
            Object[] randomPositionsArray = randomPositions.toArray();
            // Fill the row with the random numbers in the random positions
            for (int j = 0; j < randomNumbersArray.length; j++) {
                row[(int) randomPositionsArray[j]] = (int) randomNumbersArray[j];
            }
        }
    }
    public int[][] getCard() {
        return matrix;
    }
}
