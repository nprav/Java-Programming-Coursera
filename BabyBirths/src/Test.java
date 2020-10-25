/*
Week 4 MiniProject: US Baby births and names
Test class methods
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class Test {
    private BabyBirths bb;

    public Test(){
        bb = new BabyBirths();
    }

    public void testTotalBirths(){
        // Method to test the totalBirths method
        String fpath = "data/us_babynames_test/yob2012short.csv";
        FileResource fr = new FileResource(fpath);
        bb.totalBirths(fr);

        fpath = "data/us_babynames_by_year/yob1880.csv";
        fr = new FileResource(fpath);
        bb.totalBirths(fr);
    }

    public void testGetRank(){
        // Method to test getRank method
        int numTest = 1;
        int result = bb.getRank(1880, "Emma", "F");
        System.out.println(result);
        if (result != 3) {
            System.out.println("Error in test " + numTest);
        }

        numTest ++;
        result = bb.getRank(1939, "Wilhelm", "M");
        System.out.println(result);
        if (result != 2444) {
            System.out.println("Error in test " + numTest);
        }

        numTest ++;
        result = bb.getRank(2000, "Praveer", "M");
        System.out.println(result);
        if (result != -1) {
            System.out.println("Error in test " + numTest);
        }

        System.out.println("All tests on getRank complete.");
    }

    public void testGetName(){
        // Method to test getName method
        int numTest = 1;
        String result = bb.getName(1880, 3, "F");
        System.out.println(result);
        if (!result.equals("Emma")) {
            System.out.println("Error in test " + numTest);
        }

        numTest ++;
        result = bb.getName(1939, 2444, "M");
        System.out.println(result);
        if (!result.equals("Wilhelm")) {
            System.out.println("Error in test " + numTest);
        }

        numTest ++;
        result = bb.getName(2000, 70000, "F");
        System.out.println(result);
        if (!result.equals("NO NAME")) {
            System.out.println("Error in test " + numTest);
        }

        System.out.println("All tests on getName complete.");
    }

    public void testWhatIsNameInYear(){
        // Method to test WhatIsNameInYear method.
        bb.whatIsNameInYear("Isabella", 2012, 2014, "F");
        bb.whatIsNameInYear("Praveer", 1991, 2014, "M");
        bb.whatIsNameInYear("Wilhelm", 2014, 1880, "M");
    }

    public void testYearOfHighestRank(){
        // Method to test yearOfHighestRank method.
        String testName = "Mason";
        int result = bb.yearOfHighestRank(testName, "M");
        System.out.println(result);
        int rank = bb.getRank(result, testName, "M");
        System.out.println("Rank = " + rank);
    }

    public void testGetAverageRank(){
        // Method to test the getAverageRank method.
        String testName = "Mason";
        String gender = "M";
        double result = bb.getAverageRank(testName, gender);
        System.out.println(result);

        testName = "Jacob";
        result = bb.getAverageRank(testName, gender);
        System.out.println(result);

        testName = "Praveer";
        result = bb.getAverageRank(testName, gender);
        System.out.println(result);
    }

    public void testGetTotalBirthsRankedHigher(){
        // Method to test getTotalBirthsRankedHigher method.
        int numTest = 1;
        int result = bb.getTotalBirthsRankedHigher(2012, "Emma", "F");
        System.out.println(result);
        if (result != 22267) {
            System.out.println("Error in test " + numTest);
        }

        numTest ++;
        result = bb.getTotalBirthsRankedHigher(1991, "Matthew", "M");
        System.out.println(result);
        if (result != 107880) {
            System.out.println("Error in test " + numTest);
        }

        numTest ++;
        result = bb.getTotalBirthsRankedHigher(1880, "Praveer", "M");
        System.out.println(result);
        if (result != 110491) {
            System.out.println("Error in test " + numTest);
        }

        System.out.println("All tests on getTotalBirthsRankedHigher complete.");
    }

    public static void main(String[] args) {
        Test t = new Test();
//        t.testTotalBirths();
//        t.testGetRank();
//        t.testGetName();
//        t.testWhatIsNameInYear();
//        t.testYearOfHighestRank();
//        t.testGetAverageRank();
        t.testGetTotalBirthsRankedHigher();
    }
}
