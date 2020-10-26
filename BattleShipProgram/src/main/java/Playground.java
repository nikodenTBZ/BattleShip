import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Playground {

    public Map<Character, Integer> letterMap = new HashMap<>();
    private HashMap<Point, Boat> shipsPlayer1;
    private HashMap<Point, Boat> shipsPlayer2;
    private HashMap<Point, Shot> shotsPlayer1;
    private HashMap<Point, Shot> shotsPlayer2;
    private HashMap<Point, Hit> sunkenShipsPlayer1;
    private HashMap<Point, Hit> sunkenShipsPlayer2;
    private int activePlayer;
    private HashMap<Integer, Integer> availableShipsPlayer1;
    private HashMap<Integer, Integer> availableShipsPlayer2;


    {
        letterMap.put('A', 1);
        letterMap.put('B', 2);
        letterMap.put('C', 3);
        letterMap.put('D', 4);
        letterMap.put('E', 5);
        letterMap.put('F', 6);
        letterMap.put('G', 7);
        letterMap.put('H', 8);
        letterMap.put('I', 9);
        letterMap.put('J', 10);
    }

    public Playground() {
        this.shipsPlayer1 = new HashMap<>();
        this.shipsPlayer2 = new HashMap<>();
        this.shotsPlayer1 = new HashMap<>();
        this.shotsPlayer2 = new HashMap<>();
        this.sunkenShipsPlayer1 = new HashMap<>();
        this.sunkenShipsPlayer2 = new HashMap<>();
        this.activePlayer = 1;
        this.availableShipsPlayer1 = new HashMap<>();
        fillAvailableMaps();
        this.availableShipsPlayer2 = new HashMap<>(availableShipsPlayer1);

    }
    public void fillAvailableMaps(){
        availableShipsPlayer1.put(5,1);
        availableShipsPlayer1.put(4,2);
        availableShipsPlayer1.put(3,3);
        availableShipsPlayer1.put(2,4);
    }

    public void placeShips() {



    }

    /**
     * Used to get two points from the User
     * @return returns a ArrayList containing two Points
     */
    private ArrayList<Point> getCorrectCoordinatesAndPoints() {
        boolean isRegexValid = false;
        Scanner s = new Scanner(System.in);
        ArrayList<Point> pointArrayList = new ArrayList<>();
        String input = "";
        Point p1 = null;
        Point p2 = null;

        do {
            System.out.println("Player " + activePlayer + ": Please type in the start and end of the ship eg.[A1 A2]");
            input = s.nextLine();

            if (input.matches("[a-jA-J][1-10]\\s[a-jA-J][1-10]")) {
                isRegexValid = true;
                input = input.toUpperCase();
                String[] locationAndDestination = input.split(" ");

                //Create two points with the entered Coordinates, converts the letters to numbers
                p1 = new Point(letterMap.get(locationAndDestination[0].charAt(0)),
                        Integer.parseInt(locationAndDestination[0].substring(1, 2)));
                p2 = new Point(letterMap.get(locationAndDestination[1].charAt(0)),
                        Integer.parseInt(locationAndDestination[1].substring(1, 2)));

                //End the game if the user entered resign
            } else if (input.equals("resign")) {
                isRegexValid = true;
            }
        } while (!isRegexValid);

        pointArrayList.add(p1);
        pointArrayList.add(p2);

        return pointArrayList;
    }

    private Point getCorrectCoordinateAndPoint(String message) {
        boolean isRegexValid = false;
        Scanner s = new Scanner(System.in);
        ArrayList<Point> pointArrayList = new ArrayList<>();
        String input = "";
        Point p1 = null;

        do {
            System.out.println(message);
            input = s.nextLine();

            if (input.matches("[a-jA-J][1-10]")) {
                isRegexValid = true;
                input = input.toUpperCase();
                String[] locationAndDestination = input.split(" ");

                //Create one points with the entered Coordinate, converts the letter to numbers
                p1 = new Point(letterMap.get(locationAndDestination[0].charAt(0)),
                        Integer.parseInt(locationAndDestination[0].substring(1, 2)));

                //End the game if the user entered resign
            } else if (input.equals("resign")) {
                isRegexValid = true;
            }
        } while (!isRegexValid);

        return p1;
    }


    /**
     * Checks if the shoot is valid and then applies it
     * @param p
     * @return
     */
    public boolean shoot(Point p) {

        return false;
    }

    /**
     * Validates if the shoot is valid
     * @param p
     * @return
     */
    private boolean canShoot(Point p) {

        if (activePlayer == 1){
            if (shotsPlayer1.containsKey(p)){
                return false;
            } else {
                shotsPlayer1.put(p,new Shot());
                return true;
            }
        } else if (activePlayer == 2){
            if (shotsPlayer2.containsKey(p)){
                return false;
            } else {
                shotsPlayer2.put(p,new Shot());
                return true;
            }
        }

        return false;
    }

    private boolean canPlace(Point p1, Point p2){

        if (!isShipAround(p1,p2)){

        }



        return false;
    }


    /**
     * Checks if a ship is around the two given points
     * @param p1
     * @param p2
     * @return
     */
    private boolean isShipAround(Point p1, Point p2) {
        HashMap<Point,Boat> ships = activePlayer == 1 ? shipsPlayer1 : shipsPlayer2;

        //Checks if the ship is placed horizontal
        if (p1.getX() == p2.getX()) {


        } else {
            int xStart;
            int xEnd;
            int yStart;
            int yEnd;

            if (p1.getX() < p2.getX()) {
                xStart = (int) p1.getX();
                xEnd = (int) p2.getX();
            } else {
                xEnd = (int) p1.getX();
                xStart = (int) p2.getX();
            }

            if (p1.getY() < p2.getY()) {
                yStart = (int) p1.getY();
                yEnd = (int) p2.getY();
            } else {
                yEnd = (int) p1.getY();
                yStart = (int) p2.getY();
            }

            for (int y = yStart; y < yEnd; y++) {
                for (int x = xStart; x < xEnd; x++) {
                    if (ships.containsKey(new Point(x, y))) {
                        return true;
                    }
                }
            }

        }
        return false;
    }


    /**
     * Manages if the board of the opponent should also be printed out
     * @param printOpponent
     */
    public void printManager(boolean printOpponent){
        if (printOpponent){
            printBoard(true);
        }
        printBoard(false);
    }

    /**
     * Print Board Method to Print the Board in the Console.
     * Replace the Empty Elements with the needed Symbols
     */
    private void printBoard(boolean printOpponent) {
        int number = 1;
        char[][] board = new char[10][10];

        if (activePlayer == 1){
            if (!printOpponent){
                board = fillCharArray(board, shipsPlayer1, shotsPlayer2, sunkenShipsPlayer1);
            }else {
                board = fillCharArray(board, new HashMap<Point, Boat>(), shotsPlayer1, sunkenShipsPlayer2);
            }

        } else {
            if (!printOpponent){
                board = fillCharArray(board, shipsPlayer2, shotsPlayer1, sunkenShipsPlayer1);
            }else {
                board = fillCharArray(board, new HashMap<Point, Boat>(), shotsPlayer1, sunkenShipsPlayer1);
            }
        }


        System.out.println(Config.letters);
        System.out.println(Config.tab + Config.line);
        for (int y = 0; y < 10; y++, number++) {
            System.out.print(number + Config.tab + Config.border);
            for (int x = 0; x < 9; x++) {
                System.out.print(Config.tab + board[y][x] + Config.tab + Config.border);
            }
            System.out.print(Config.tab + Config.tab + Config.border + "\n");
            System.out.println(Config.tab + Config.line);
        }
    }

    /**
     * Fill in the Ships, sunkenShips and shots into the char Array, that gets returned
     * @param board
     * @param shipsPlayer2
     * @param shotsPlayer1
     * @param sunkenShipsPlayer2
     * @return
     */
    private char[][] fillCharArray(char[][] board, HashMap<Point, Boat> shipsPlayer2, HashMap<Point, Shot> shotsPlayer1, HashMap<Point, Hit> sunkenShipsPlayer2) {
        for (Point p : shipsPlayer2.keySet()){
            board[(int) p.getX()][(int) p.getY()] = shipsPlayer2.get(p).getAppearance();
        }

        for (Point p : shotsPlayer1.keySet()){
            board[(int) p.getX()][(int) p.getY()] = shotsPlayer1.get(p).getAppearance();
        }

        for (Point p : sunkenShipsPlayer2.keySet()){
            board[(int) p.getX()][(int) p.getY()] = sunkenShipsPlayer2.get(p).getAppearance();
        }
        return board;
    }
}