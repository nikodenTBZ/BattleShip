import java.util.Scanner;

import static Tools.Constants.*;

public class Game {


    /**
     * The main logic of the game
     */
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
                if (successful) {
                    System.out.println(HIT);
                } else {
                    System.out.println(MISSED);
                }
            } while (successful);

            if (playground.getActivePlayer() == 1) {
                playground.setActivePlayer(2);
            } else {
                playground.setActivePlayer(1);
            }

            //Newlines to hide the board of the enemy
            printSpace();

            System.out.println(PLAYER + playground.getActivePlayer() + " please type \"ok\" if ready");
            String ok = s.nextLine();


        }

        if (playground.getActivePlayer() == 1) {
            System.out.println(P2WON);
            try {
                new FileManager().saveWinnerInFile("Player 2");
            } catch (CouldNotWriteException e){
                System.out.println(FILEERROR);
            }
        } else {
            System.out.println(P1WON);

            try {
                new FileManager().saveWinnerInFile("Player 1");

            } catch (CouldNotWriteException e){
                System.out.println(FILEERROR);
            }

        }

    }

    /**
     * Creates the users
     */
    public void createUser() {
        Scanner s = new Scanner(System.in);
        System.out.println(USERNAMEP1);
        String username1 = s.nextLine();
        System.out.println(USERNAMEP2);
        String username2 = s.nextLine();

        Player player1 = new Player(username1);
        Player player2 = new Player(username2);
    }

    /**
     * Prints the spaces to hide the playground
     */
    public void printSpace() {
        System.out.println(SPACE);
    }

}
