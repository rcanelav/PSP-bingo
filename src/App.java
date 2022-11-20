public class App {
    public static void main(String[] args) throws Exception {
        Player player = new Player("John");
        Player player2 = new Player("Mary");
        Thread playerThread = new Thread(player);
        Thread playerThread2 = new Thread(player2);
        playerThread.start();

        playerThread2.start();


        BingoGame game = new BingoGame();

        game.addPlayer(player);
        game.addPlayer(player2);

        Thread gameThread = new Thread(game);
        gameThread.start();

    }
}
