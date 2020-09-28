import java.awt.*;
import java.util.HashMap;

public class Playground {

    private HashMap<Point,Boat> shipsPlayer1;
    private HashMap<Point,Boat> shipsPlayer2;
    private HashMap<Point,Boat> shotsPlayer1;
    private HashMap<Point,Boat> shotsPlayer2;
    private HashMap<Point,Boat> sunkenShipsPlayer1;
    private HashMap<Point,Boat> sunkenShipsPlayer2;


    public Playground(){
    this.shipsPlayer1 = new HashMap<>();
    this.shipsPlayer2 = new HashMap<>();
    this.shotsPlayer1 = new HashMap<>();
    this.shotsPlayer2 = new HashMap<>();
    this.sunkenShipsPlayer1 = new HashMap<>();
    this.sunkenShipsPlayer2 = new HashMap<>();
    }


    public void placeShips(int id){

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

    }

}
