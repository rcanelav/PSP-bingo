import java.util.ArrayList;

public class BingoGame implements Runnable {
    BingoCage cage;
    ArrayList<Player> players;

    public BingoGame() {
        this.cage = new BingoCage();
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        this.players.add(player);

    }

    public void givePrize(Player player) {
        // Generate random number between 50 and 100, with two decimals
        double prize = (double) Math.round((Math.random() * 50 + 50) * 100) / 100;
        player.addWin(prize);

        System.out.println("Player " + player.name + " won " + prize + " credits");
    }

    public boolean notifyPlayers(int ball, ArrayList<Integer> extractedBalls) {
        for (Player player : players) {
            boolean playerHasBall = player.checkBall(ball);
            if (playerHasBall) {
                boolean didSomeoneWin = checkWinner(player, extractedBalls);
                if (didSomeoneWin) {
                    // Print winner card
                    player.displayCard();

                    this.givePrize(player);


                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkWinner(Player player, ArrayList<Integer> extractedBalls) {
        int[][] card = player.getCard();
        // If player has at least 5 numbers in a row, he wins
        // Check rows
        for (int i = 0; i < card.length; i++) {
            int count = 0;
            for (int j = 0; j < card[i].length; j++) {
                    Boolean playerHasBall = player.getCheckedNumbers().contains(card[i][j]);
                    if (Boolean.TRUE.equals(playerHasBall)) {
                        if(++count == 5) {
                            System.out.println("Player " + player.name + " won the game filling the row " + i);
                            return true;
                        }
                    }
                    
                }
                count = 0;
            
            
        }

        // Check columns
        for(int i = 0;i<card[0].length;i++){
            int count = 0;
            for (int j = 0; j < card.length; j++) {
                // Check if the cage.getBalls() contains the number
                if(Boolean.TRUE.equals(player.getCheckedNumbers().contains(card[j][i]))){
                    if(++count == 3) {
                        System.out.println("Player " + player.name + " won the game filling the COLUMN " + i);
                        return true;
                    }
                }
                count = 0;
            }
            

        }return false;
    }

    public void play() {
        boolean bingo = false;
        int ball = 0;
        ArrayList<Integer> extractedBalls = new ArrayList<>();
        while (!bingo) {
            try {
                ball = cage.extractBall();
                extractedBalls.add(ball);
                Thread.sleep(100);
                bingo = notifyPlayers(ball, extractedBalls);
                
                if(extractedBalls.size() == 75){
                    System.out.println("No one won");
                    bingo = !bingo;
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void removePlayerFromGame(Player player) {
        this.players.remove(player);
        System.out.println("Player " + player.name + " left the game");
    }

    @Override
    public void run() {
        try {
        Thread.sleep(1000);
        
        while(true){
            // Start game
            this.play();
            
            // Send restart new round message
            System.out.println("New game starting in 2 seconds");
            Thread.sleep(2000);

            // Verify if each player can afford to play
            players.forEach(player -> {
                boolean playerKeepPlaying = player.startNewGame();
                if(!playerKeepPlaying){
                    removePlayerFromGame(player);
                }
            });

            // Check if there are still players
            if(players.isEmpty()){
                break;
            }

            // Restart game
            cage.resetGame();

            // Clean console
            // System.out.print("\033[H\033[2J");
            
        }
    } catch (Exception e) {
        System.err.println(e.getMessage());
    }
        
    }

}
