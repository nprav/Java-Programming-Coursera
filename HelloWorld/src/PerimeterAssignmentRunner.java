package src;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Start with numPts = 0
        int numPts = 0;
        for (Point pt : s.getPoints()){
            numPts = numPts + 1;
        }
        return numPts;
    }

    public double getAverageLength(Shape s) {
        double totalPerim = getPerimeter(s);
        int numPts = getNumPoints(s);
        return totalPerim/numPts;
    }

    public double getLargestSide(Shape s) {
        // Start with a max. side length of 0
        double maxSide = 0;
        // Initialize reference point with the last point
        Point prevPt = s.getLastPoint();
        // for each point in the shape
        for (Point currPt : s.getPoints()){
            // calculate the side length
            double sideLength = prevPt.distance(currPt);
            // update maxSide if the side length is greater
            if (sideLength > maxSide){
                maxSide = sideLength;
            }
            // update the reference point
            prevPt = currPt;
        }
        return maxSide;
    }

    public double getLargestX(Shape s) {
        // Initialize reference point with the last point
        Point prevPt = s.getLastPoint();
        // initialize maxX with x of last point
        double maxX = prevPt.getX();
        // for each point in s
        for (Point currPt : s.getPoints()){
            // get the X of the point
            double currX = currPt.getX();
            // update maxX if the X is greater
            if (currX > maxX){
                maxX = currX;
            }
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Initialize max Perimeter
        double maxPerim = 0;
        // Setup directory resource
        DirectoryResource dr = new DirectoryResource();
        // For each file in directory resource
        for (File f : dr.selectedFiles()) {
            // Convert to FileResource and then Shape
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            // Get perimeter of shape and compare to maxPerim
            double currPerim = getPerimeter(s);
            if (currPerim > maxPerim){
                // Update maxPerim if currPerim is larger
                maxPerim = currPerim;
            }
        }
        return maxPerim;
    }

    public File getFileWithLargestPerimeter() {
        // Initialize max Perimeter
        double maxPerim = 0;
        File maxFile = null;
        // Setup directory resource
        DirectoryResource dr = new DirectoryResource();
        // For each file in directory resource
        for (File f : dr.selectedFiles()) {
            // Convert to FileResource and then Shape
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            // Get perimeter of shape and compare to maxPerim
            double currPerim = getPerimeter(s);
            if (currPerim > maxPerim){
                // Update maxPerim and maxFile if currPerim is larger
                maxPerim = currPerim;
                maxFile = f;
            }
        }
        return maxFile;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPts = getNumPoints(s);
        System.out.println("numPoints = " + numPts);
        double avgLength = getAverageLength(s);
        System.out.println("avgLength = " + avgLength);
        double maxSide = getLargestSide(s);
        System.out.println("LargestSide = " + maxSide);
        double maxX = getLargestX(s);
        System.out.println("Max X = " + maxX);
    }

    public void testPerimeterMultipleFiles() {
        double maxPerim = getLargestPerimeterMultipleFiles();
        System.out.println(
                "Max Perimeter from selected files = " + maxPerim
        );
    }

    public void testFileWithLargestPerimeter() {
        File maxFile = getFileWithLargestPerimeter();
        System.out.println(maxFile.getName());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
//         pr.testPerimeter();
//         pr.printFileNames();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
