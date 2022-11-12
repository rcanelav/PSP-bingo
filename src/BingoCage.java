import java.util.HashSet;
import java.util.Set;

public class BingoCage {
    
    Integer[] ballsArray = new Integer[75];
    public BingoCage() {
        // Create a set 
        Set<Integer> balls = new HashSet<>();
        // Generate 75 random numbers between 1 and 99
        while (balls.size() < 75) {
            balls.add((int) (Math.random() * 99) + 1);
        }
        // Convert balls to an Integer Array
        ballsArray = balls.toArray(ballsArray);
        
    }

    public void resetGame() {
        // Create a set 
        Set<Integer> balls = new HashSet<>();
        // Generate 75 random numbers between 1 and 99
        while (balls.size() < 75) {
            balls.add((int) (Math.random() * 99) + 1);
        }
        // Convert balls to an Integer Array
        ballsArray = balls.toArray(ballsArray);
    }
    
    public Integer[] getBalls() {
        return ballsArray;
    }

    public int extractBall() {
        boolean ballExtracted = false;
        int extractedBall = 0;
        while (!ballExtracted) {
            int randomBall = (int) (Math.random() * 75);
            if (ballsArray[randomBall] != 0) {
                System.out.println("Ball extracted: " + ballsArray[randomBall]);
                extractedBall = ballsArray[randomBall];
                ballsArray[randomBall] = 0;
                ballExtracted = true;
            }
        }
        return extractedBall;
    }



    
    
    
}
