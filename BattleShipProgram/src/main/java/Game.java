import java.util.Scanner;

public class Game {
    private final Playground playground = new Playground();

    public void playGame() {
        Scanner s = new Scanner(System.in);
        createUser();

        playground.printManager(false);
        playground.placeShips();
        playground.setActivePlayer(2);
        playground.printManager(false);
        playground.placeShips();
        playground.setActivePlayer(1);

        while (!playground.hasWon()) {
            playground.shoot();
            playground.printManager(true);

            if (playground.getActivePlayer() == 1) {
                playground.setActivePlayer(2);
            } else {
                playground.setActivePlayer(1);
            }

            System.out.println("Player: " + playground.getActivePlayer() + " please type \"ok\" if ready");
            String ok = s.nextLine();
            //Newlines to hide the board of the enemy
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }

        if (playground.getActivePlayer() == 1) {
            System.out.println("Player 2 has won");
        } else {
            System.out.println("Player 1 has won");
        }

    }

    public void createUser() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please type in a username for player 1");
        String username1 = s.nextLine();
        System.out.println("Please type in a username for player 2");
        String username2 = s.nextLine();

        Player player1 = new Player(username1);
        Player player2 = new Player(username2);
    }

}
