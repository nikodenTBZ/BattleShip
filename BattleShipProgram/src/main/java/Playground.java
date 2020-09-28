import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Playground {

    private HashMap<Point,Boat> shipsPlayer1;
    private HashMap<Point,Boat> shipsPlayer2;
    private HashMap<Point,Boat> shotsPlayer1;
    private HashMap<Point,Boat> shotsPlayer2;
    private HashMap<Point,Boat> sunkenShipsPlayer1;
    private HashMap<Point,Boat> sunkenShipsPlayer2;
    public Map<Character, Integer> letterMap = new HashMap<>();


    public Playground(){
    this.shipsPlayer1 = new HashMap<>();
    this.shipsPlayer2 = new HashMap<>();
    this.shotsPlayer1 = new HashMap<>();
    this.shotsPlayer2 = new HashMap<>();
    this.sunkenShipsPlayer1 = new HashMap<>();
    this.sunkenShipsPlayer2 = new HashMap<>();

    }

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


    public void placeShips(int id){
    ArrayList<Point> pointArrayList = getCorrectCoordinatesAndPoints("Please type in the location of the 5 boat: ");

    for (Point p : pointArrayList){
        System.out.println(p.toString());
    }

    }

    private ArrayList<Point> getCorrectCoordinatesAndPoints(String message){
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
                p1 = new Point(letterMap.get(locationAndDestination[0].charAt(0)),Integer.parseInt(locationAndDestination[0].substring(1,2)) );
                p2 = new Point(letterMap.get(locationAndDestination[1].charAt(0)), Integer.parseInt(locationAndDestination[1].substring(1,2)));

                //End the game if the user entered resign
            } else if (input.equals("resign")){
                isRegexValid = true;
            }
        } while (!isRegexValid);

        pointArrayList.add(p1);
        pointArrayList.add(p2);


        return pointArrayList;
    }

    private ArrayList<Point> getPointsBetween(Point p1, Point p2, int shipLenght){



        return null;
    }

    public boolean shoot(Point p){


        return false;
    }


    private boolean canShoot(Point p){


        return false;
    }

    private boolean isShipAround(Point p1, Point p2){

        return false;
    }

    public void printBoard(){
        System.out.println("\t├───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┤");
        for(int i = 0; i < 11; i++) {
            System.out.println("\t├───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┤");
        }
        System.out.println("\t├───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┤");
        System.out.println("\t\tA\t\tB\t\tC\t\tD\t\tE\t\tF\t\tG\t\tH\t\tI\t\tJ");
    }

    public void printLogic(){
        int iii = 0;

            System.out.println("║");
            if (iii < 7) {

            }
        }
    }

