import java.util.ArrayList;

public class Player implements Runnable{
    // This is a Bingo Player
    // A Bingo Player has a name
    String name;
    BingoCard card;
    Double money;
    Double credits;
    int gamesWon;

    // Numbers of the balls that player has
    ArrayList<Integer> playerCheckedNumbers = new ArrayList<>();

    public Player(String name)  {
        this.name = name;
        this.money = 0.0;
        this.credits = 10.0;
    }

    public int[][] getCard() {
        return card.getCard();
    }

    public void addWin(Double prize) {
        this.gamesWon++;
        this.money += prize;
    }

    public void displayCard() {
        int[][] card = this.getCard();
        for (int i = 0; i < card.length; i++) {
            for (int j = 0; j < card[i].length; j++) {
                System.out.print(card[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void buyCredits() {
        // Buy credits
        // generate random even number between 2 and 10
        int creditsToBuy = (int) (Math.random() * 5 + 1) * 2;

        // If player has enough money, buy credits
        if (this.money >= creditsToBuy * 2) {
            this.money -= creditsToBuy * 2;
            this.credits += creditsToBuy;
        }
        System.out.println("Player " + this.name + " bought " + creditsToBuy + " credits.\n - BALANCE:\n CREDITS " + this.credits + "  ||| MONEY: " + this.money + " €");
    }

    public boolean buyCard() {
        
        if (this.credits <= 2) {
            this.buyCredits();
        }
        if (this.credits >= 2 ){
            this.credits -= 2.0;
            this.card = new BingoCard();

            System.out.println("Player " + this.name + " bought a card");
            return true;
        }

        System.out.println("Player " + this.name + " doesn't have enough credits to buy a card");
        return false;

    }

    public boolean checkBall(int ball) {
        int[][] card = this.getCard();
        for (int i = 0; i < card.length; i++) {
            for (int j = 0; j < card[i].length; j++) {
                if (card[i][j] == ball) {
                    System.out.println("Player " + this.name + " has the number " + ball);
                    playerCheckedNumbers.add(ball);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Integer> getCheckedNumbers() {
        return playerCheckedNumbers;
    }

    public boolean startNewGame() {
        this.playerCheckedNumbers = new ArrayList<>();

        return this.buyCard();
    }


    public boolean checkBingo() {
        int[][] card = this.getCard();
        for (int i = 0; i < card.length; i++) {
            for (int j = 0; j < card[i].length; j++) {
                if (card[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        System.out.println("Player: " + name);
        System.out.println("Card: ");
        int[][] card = this.card.getCard();
        for (int i = 0; i < card.length; i++) {
            for (int j = 0; j < card[i].length; j++) {
                System.out.print(card[i][j] + " ");
            }
            System.out.println();
        }
        return "";
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        this.buyCard();
    }

     
}
