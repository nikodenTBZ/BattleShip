import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static Tools.Constants.*;

public class Playground extends Game {

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
        this.availableShipsPlayer2 = new HashMap<>(availableShipsPlayer1);
        fillAvailableMaps();

    }

    private void fillAvailableMaps() {
        availableShipsPlayer1.put(5, 1);
        availableShipsPlayer1.put(4, 2);
        availableShipsPlayer1.put(3, 3);
        availableShipsPlayer1.put(2, 4);

        availableShipsPlayer2.put(5, 1);
        availableShipsPlayer2.put(4, 2);
        availableShipsPlayer2.put(3, 3);
        availableShipsPlayer2.put(2, 4);
    }

    public void placeShips() {

        HashMap<Integer, Integer> availableShipsPlayerX;
        HashMap<Point, Boat> shipsPlayerX;
        int shipLengt = 5;
        if (activePlayer == 1) {
            availableShipsPlayerX = availableShipsPlayer1;
            shipsPlayerX = shipsPlayer1;
        } else {
            availableShipsPlayerX = availableShipsPlayer2;
            shipsPlayerX = shipsPlayer2;
        }

        while (shipLengt > 1) {
            while (availableShipsPlayerX.get(shipLengt) >= 1) {
                int counterAvaibleShips = availableShipsPlayerX.get(shipLengt);
                System.out.println(TYPEINCOORDINATES + shipLengt);
                ArrayList<Point> coordinateList = getCorrectCoordinatesAndPoints();
                coordinateList.get(0).setLocation(coordinateList.get(0).getX()-1, coordinateList.get(0).getY()-1);
                coordinateList.get(1).setLocation(coordinateList.get(1).getX()-1, coordinateList.get(1).getY()-1);



                if (canPlace(new Point(coordinateList.get(0).x, coordinateList.get(0).y), new Point(coordinateList.get(1).x, coordinateList.get(1).y))) {
                    if (getShipLenght(coordinateList.get(0), coordinateList.get(1)) == shipLengt - 1) {

                        int End = 0;
                        int Start = 0;

                        if (coordinateList.get(0).x != coordinateList.get(1).x) {
                            if (coordinateList.get(0).x < coordinateList.get(1).x) {
                                Start = coordinateList.get(0).x;
                                End = coordinateList.get(1).x;
                            } else {
                                Start = coordinateList.get(1).x;
                                End = coordinateList.get(0).x;
                            }
                            while (Start <= End) {
                                shipsPlayerX.put(new Point(Start, coordinateList.get(0).y), new Boat());
                                Start++;
                            }
                        } else if (coordinateList.get(0).y != coordinateList.get(1).y) {
                            if (coordinateList.get(0).y < coordinateList.get(1).y) {
                                Start = coordinateList.get(0).y;
                                End = coordinateList.get(1).y;
                            } else {
                                Start = coordinateList.get(1).y;
                                End = coordinateList.get(0).y;
                            }
                            while (Start <= End) {
                                shipsPlayerX.put(new Point(coordinateList.get(0).x, Start), new Boat());
                                Start++;
                            }
                        }

                        availableShipsPlayerX.put(shipLengt, --counterAvaibleShips);
                        printManager(false);
                    } else {
                        System.out.println(TYPEINCORRECTCOORDINATES);
                    }
                }
            }
            shipLengt--;
        }
    }

    private double getShipLenght(Point p1, Point p2) {

        double distance = p1.distance(p2);
        return distance;
    }

    private boolean canPlace(Point p1, Point p2) {

        boolean canPlace = !isShipAround(p1, p2);
        return canPlace;
    }

    /**
     * Used to get two points from the User
     *
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
            System.out.println(PLAYER + activePlayer + STARTENDSHIP);
            input = s.nextLine();

            if (input.matches("[a-jA-J][0-9]+\\s[a-jA-J][0-9]+")) {
                input = input.toUpperCase();
                String[] locationAndDestination = input.split(" ");

                //Create two points with the entered Coordinates, converts the LETTERS to numbers
                //Check if the Y number is 1 or 2 digits
                if (locationAndDestination[0].length() == 2){
                    p1 = new Point(letterMap.get(locationAndDestination[0].charAt(0)),
                            Integer.parseInt(locationAndDestination[0].substring(1, 2)));
                } else {
                    p1 = new Point(letterMap.get(locationAndDestination[0].charAt(0)),
                            Integer.parseInt(locationAndDestination[0].substring(1, 3)));
                }

                //Check if the Y number is 1 or 2 digits
                if (locationAndDestination[1].length() == 2){
                    p2 = new Point(letterMap.get(locationAndDestination[1].charAt(0)),
                            Integer.parseInt(locationAndDestination[1].substring(1, 2)));
                } else {
                    p2 = new Point(letterMap.get(locationAndDestination[1].charAt(0)),
                            Integer.parseInt(locationAndDestination[1].substring(1, 3)));
                }

                if (p1.getY() <= 10 && p1.getX() <= 10 && p2.getY() <= 10 && p2.getX() <= 10) {
                    isRegexValid = true;
                }

                //End the game if the user entered resign
            } else if (input.equals(RESIGN)) {
                isRegexValid = true;
            }
        } while (!isRegexValid);

        pointArrayList.add(p1);
        pointArrayList.add(p2);

        return pointArrayList;
    }

    private Point getCorrectCoordinateAndPoint() {
        boolean isRegexValid = false;
        Scanner s = new Scanner(System.in);
        ArrayList<Point> pointArrayList = new ArrayList<>();
        String input = "";
        Point p1 = null;

        do {
            System.out.println(PLAYER + activePlayer + TYPEINSHOT);
            input = s.nextLine();

            if (input.matches("[a-jA-J][0-9]+")) {
                input = input.toUpperCase();
                String[] locationAndDestination = input.split(" ");

                //Create one points with the entered Coordinate, converts the letter to numbers
                p1 = new Point(letterMap.get(locationAndDestination[0].charAt(0)),
                        Integer.parseInt(locationAndDestination[0].substring(1, 2)));

                if (p1.getY() <= 10 && p1.getX() <= 10) {
                    isRegexValid = true;
                }
                //End the game if the user entered resign
            } else if (input.equals(RESIGN)) {
                isRegexValid = true;
            }
        } while (!isRegexValid);

        return p1;
    }


    /**
     * Checks if the shoot is valid and then applies it
     *
     * @return a boolean if the shot was successfully
     */
    public boolean shoot() {
        Point p;
        do {
            p = getCorrectCoordinateAndPoint();
            p.setLocation(p.getX()-1,p.getY()-1);
        } while (!canShoot(p));

            if (activePlayer == 1) {
                shotsPlayer1.put(p, new Shot());
            } else {
                shotsPlayer2.put(p, new Shot());
            }

            return checkIfHit(p);

    }

    /**
     * Checks if on the position of the shot is a Ship, if yes, add it to sunkenShips
     * @param p
     * @return
     */
    private boolean checkIfHit(Point p) {

        if (activePlayer == 1) {
            if (shipsPlayer2.containsKey(p)) {
                sunkenShipsPlayer2.put(p, new Hit());
                shipsPlayer2.remove(p);
                return true;
            }
        } else {
            if (shipsPlayer1.containsKey(p)) {
                sunkenShipsPlayer1.put(p, new Hit());
                shipsPlayer1.remove(p);
                return true;
            }
        }
        return false;
    }


    /**
     * Validates if the shoot is valid
     *
     * @param p
     * @return
     */
    private boolean canShoot(Point p) {

        if (activePlayer == 1) {
            if (shotsPlayer1.containsKey(p)) {
                return false;
            } else {
                shotsPlayer1.put(p, new Shot());
                return true;
            }
        } else if (activePlayer == 2) {
            if (shotsPlayer2.containsKey(p)) {
                return false;
            } else {
                shotsPlayer2.put(p, new Shot());
                return true;
            }
        }

        return false;
    }


    /**
     * Checks if a ship is around the two given points
     *
     * @param p1
     * @param p2
     * @return
     */
    private boolean isShipAround(Point p1, Point p2) {
        HashMap<Point, Boat> ships = activePlayer == 1 ? shipsPlayer1 : shipsPlayer2;

        //Checks if the ship is placed horizontal

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

        //Lower the number by 1
        xStart = xStart != 0 ? --xStart : xStart;
        yStart = yStart != 0 ? --yStart : yStart;
        xEnd = xEnd != 9 ? ++xEnd : xEnd;
        yEnd = yEnd != 9 ? ++yEnd : yEnd;

        for (int x = xStart; x <= xEnd; x++) {
            for (int y = yStart; y <= yEnd; y++) {
                if (ships.containsKey(new Point(x, y))) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Manages if the board of the opponent should also be printed out
     *
     * @param printOpponent
     */
    public void printManager(boolean printOpponent) {
        if (printOpponent) {
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

        if (activePlayer == 1) {
            if (!printOpponent) {
                board = fillCharArray(board, shipsPlayer1, shotsPlayer2, sunkenShipsPlayer1);
            } else {
                board = fillCharArray(board, new HashMap<Point, Boat>(), shotsPlayer1, sunkenShipsPlayer2);
            }

        } else {
            if (!printOpponent) {
                board = fillCharArray(board, shipsPlayer2, shotsPlayer1, sunkenShipsPlayer2);
            } else {
                board = fillCharArray(board, new HashMap<Point, Boat>(), shotsPlayer2, sunkenShipsPlayer1);
            }
        }


        System.out.println(LETTERS);
        System.out.println(TAB + LINE);
        for (int x = 0; x < 10; x++, number++) {
            System.out.print(number + TAB + BORDER);
            for (int y = 0; y < 9; y++) {
                System.out.print(TAB + board[y][x] + TAB + BORDER);
            }
            System.out.print(TAB + TAB + BORDER + "\n");
            System.out.println(TAB + LINE);
        }
    }

    /**
     * Fill in the Ships, sunkenShips and shots into the char Array, that gets returned
     *
     * @param board
     * @param shipsPlayer2
     * @param shotsPlayer1
     * @param sunkenShipsPlayer2
     * @return
     */
    private char[][] fillCharArray(char[][] board, HashMap<Point, Boat> shipsPlayer2, HashMap<Point, Shot> shotsPlayer1, HashMap<Point, Hit> sunkenShipsPlayer2) {
        for (Point p : shipsPlayer2.keySet()) {
            board[(int) p.getX()][(int) p.getY()] = shipsPlayer2.get(p).getAppearance();
        }

        for (Point p : shotsPlayer1.keySet()) {
            board[(int) p.getX()][(int) p.getY()] = shotsPlayer1.get(p).getAppearance();
        }

        for (Point p : sunkenShipsPlayer2.keySet()) {
            board[(int) p.getX()][(int) p.getY()] = sunkenShipsPlayer2.get(p).getAppearance();
        }
        return board;
    }

//TODO
    /**
     * Checks if a player has won
     * @return
     */
    public boolean hasWon() {
        HashMap<Point,Boat> ships = activePlayer == 1 ? shipsPlayer2 : shipsPlayer1;
        HashMap<Point,Hit> sunkenShips = activePlayer == 1 ? sunkenShipsPlayer2 : sunkenShipsPlayer1;

        for (Point p : ships.keySet()) {
            if (!sunkenShips.containsKey(p)) {
                return false;
            }
        }

        return true;
    }

    public int getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(int activePlayer) {
        this.activePlayer = activePlayer;
    }
}