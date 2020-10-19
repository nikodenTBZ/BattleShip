import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
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
    }

    public void placeShips(int id) {
        ArrayList<Point> pointArrayList = getCorrectCoordinatesAndPoints("Please type in the location of the 5 boat: ");

        for (Point p : pointArrayList) {
            System.out.println(p.toString());
        }

    }

    private ArrayList<Point> getCorrectCoordinatesAndPoints(String message) {
        boolean isRegexValid = false;
        Scanner s = new Scanner(System.in);
        ArrayList<Point> pointArrayList = new ArrayList<>();
        String input = "";
        Point p1 = null;
        Point p2 = null;

        do {
            System.out.println(message);
            input = s.nextLine();

            if (input.matches("[a-hA-H][1-8]\\s[a-hA-H][1-8]")) {
                isRegexValid = true;
                input = input.toUpperCase();
                String[] locationAndDestination = input.split(" ");

                //Create two points with the entered Coordinates, converts the letters to numbers
                p1 = new Point(letterMap.get(locationAndDestination[0].charAt(0)), Integer.parseInt(locationAndDestination[0].substring(1, 2)));
                p2 = new Point(letterMap.get(locationAndDestination[1].charAt(0)), Integer.parseInt(locationAndDestination[1].substring(1, 2)));

                //End the game if the user entered resign
            } else if (input.equals("resign")) {
                isRegexValid = true;
            }
        } while (!isRegexValid);

        pointArrayList.add(p1);
        pointArrayList.add(p2);


        return pointArrayList;
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
            if (shotsPlayer1)
        } else if (activePlayer == 2){

        }

        return false;
    }

    private boolean isShipAround(Point p1, Point p2, HashMap<Point, Boat> ships) {

        //Checks if the ship is placed horizontal
        if (p1.getX() == p2.getX()){


        } else {
            int xStart;
            int xEnd;
            int yStart;
            int yEnd;

            if (p1.getX() < p2.getX()){
                xStart = (int) p1.getX();
                xEnd = (int) p2.getX();
            } else{
                xEnd = (int) p1.getX();
                xStart = (int) p2.getX();
            }

            if (p1.getY() < p2.getY()){
                yStart = (int) p1.getY();
                yEnd = (int) p2.getY();
            } else{
                yEnd = (int) p1.getY();
                yStart = (int) p2.getY();
            }

            for (int y = yStart; y < yEnd; y++){
                for (int x = xStart; x < xEnd; x++){
                    if (ships.containsKey(new Point(x,y))){
                        return true;
                    }
                }
            }

        }
        return false;
    }


    public void printBoard() {
        int number = 1;
        char[][] board = new char[10][10];
        System.out.println(Config.letters);
        System.out.println(Config.tab+ Config.line);
        for (int y = 0; y < 10; y++, number++) {
            System.out.print(number + Config.tab + Config.border);
            for (int x = 0; x < 9; x++) {
                System.out.print(Config.tab + board[y][x] + Config.tab + Config.border);
            }
            System.out.print(Config.tab + Config.tab + Config.border +"\n");
            System.out.println(Config.tab + Config.line);
        }
    }
}