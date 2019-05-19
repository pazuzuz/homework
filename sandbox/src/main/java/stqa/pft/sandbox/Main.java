package stqa.pft.sandbox;

import figures.Point;
import utils.UtilCalculations;

public class Main {
    public static void main(String[] args){
        First.printHelloWorld();

        Point p1 = new Point(3, 4);
        Point p2 = new Point(0, 0);
        System.out.println("Distance between points:  " + p1.distance(0, 0));
        System.out.println("Distance between points:  " + UtilCalculations.distance(p1, p2));
        System.out.println("Distance between points:  " + p1.distance(p2));
    }
}
