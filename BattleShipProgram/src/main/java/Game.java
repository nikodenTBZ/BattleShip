public class Game {

    public void playGame(){
        Playground playground = new Playground();
        createUser();
        playground.printManager(false);
        playground.placeShips();
        playground.setActivePlayer(2);
        playground.printManager(false);
        playground.placeShips();
        playground.setActivePlayer(1);

        while (!playground.hasWon()){
        playground.shoot();
        playground.printManager(true);

        if (playground.getActivePlayer() == 1){
            playground.setActivePlayer(2);
        } else {
            playground.setActivePlayer(1);
        }
        }

    }

    public void createUser(){
    Player player1 = new Player("Nikolai");
    Player player2 = new Player("Dennis");
    }

}
