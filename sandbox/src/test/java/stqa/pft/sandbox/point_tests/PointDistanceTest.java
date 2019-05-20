package stqa.pft.sandbox.point_tests;

import figures.Point;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.UtilCalculations;

public class PointDistanceTest {
    @DataProvider
    public Object[][] testingData(){
        return new Object[][]{
                            {new Point(0, 0), new Point(3, 4), 5.0},
                            {new Point(3, 4), new Point(6,8), 5.0},
                            {new Point(6, 8), new Point(0,0), 10.0},
                            {new Point(-6, -8), new Point(0,0), 10.0},
        };
    }

    @Test(description = "tests for points distance.", dataProvider = "testingData")
    public void checkDistanceBetweenToPoints(Point p1, Point p2, double distance){
        Assert.assertEquals(p1.distance(p2), distance, "Distance is incorrect.");
    }
}
