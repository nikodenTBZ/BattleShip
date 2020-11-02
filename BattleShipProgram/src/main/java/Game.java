import java.util.Scanner;
import static Tools.Constants.*;

public class Game {

    public void playGame() {
        Playground playground = new Playground();

        Scanner s = new Scanner(System.in);
        createUser();

        playground.printManager(false);
        playground.placeShips();
        playground.setActivePlayer(2);
        playground.printManager(false);
        playground.placeShips();
        playground.setActivePlayer(1);
        printSpace();
        while (!playground.hasWon()) {
            playground.printManager(true);


            boolean successful = false;
            do {
                successful = playground.shoot();
                playground.printManager(true);
                if (successful){
                    System.out.println(HIT);
                } else {
                    System.out.println("No hit");
                }
            } while (successful);

            if (playground.getActivePlayer() == 1) {
                playground.setActivePlayer(2);
            } else {
                playground.setActivePlayer(1);
            }

            //Newlines to hide the board of the enemy
            printSpace();

            System.out.println("Player: " + playground.getActivePlayer() + " please type \"ok\" if ready");
            String ok = s.nextLine();


        }

        if (playground.getActivePlayer() == 1) {
            System.out.println("Player 2 has won");
            new FileManager().saveWinnerInFile("Player 2");
        } else {
            System.out.println("Player 1 has won");
            new FileManager().saveWinnerInFile("Player 1");
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

    public void printSpace() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

}
