/*
Week 4 MiniProject: US Baby births and names
Main class methods
 */

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyBirths {
    public void totalBirths(FileResource fr){
        // Method to get calculate summary statistics from a
        // particular files birth data.

        // Get parser from CSV file with no header line
        CSVParser parser = fr.getCSVParser(false);

        // Initialize summary statistics
        int maleBirths = 0;
        int femaleBirths = 0;
        int maleNames = 0;
        int femaleNames = 0;

        // Loop through records
        for (CSVRecord record : parser){
            if (record.get(1).equals("F")){
                femaleBirths += Integer.parseInt(record.get(2));
                femaleNames ++;
            }
            else {
                maleBirths += Integer.parseInt(record.get(2));
                maleNames ++;
            }
        }
        // Get total statistics
        int totalBirths = maleBirths + femaleBirths;
        int totalNames = maleNames + femaleNames;

        // Output information
        System.out.println("Total Births = " + totalBirths);
        System.out.println("Total Names = " + totalNames);
        System.out.println("Male Births = " + maleBirths);
        System.out.println("Male Names = " + maleNames);
        System.out.println("Female Births = " + femaleBirths);
        System.out.println("Female Names = " + femaleNames);
    }

    public FileResource getFileResourceForYear(int year){
        // Method to provide the FileResource with name data for the
        // designated year
        String fpath = "data/us_babynames_by_year/yob" + year + ".csv";
        return new FileResource(fpath);
    }

    public int getRank(int year, String name, String gender){
        FileResource fr = getFileResourceForYear(year);
        CSVParser parser = fr.getCSVParser(false);
        int recordRank = 0;
        for (CSVRecord record : parser){
            if (record.get(1).equals(gender)){
                recordRank ++;
                if (record.get(0).equals(name)){
                    return recordRank;
                }
            }
        }
        return -1;
    }

    public String getName(int year, int rank, String gender){
        // Method to get the name at the designated year, rank, and
        // gender.
        FileResource fr = getFileResourceForYear(year);
        CSVParser parser = fr.getCSVParser(false);
        int recordRank = 0;
        for (CSVRecord record : parser){
            if (record.get(1).equals(gender)){
                recordRank ++;
                if (recordRank == rank){
                    return record.get(0);
                }
            }
        }
        return "NO NAME";
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        // Method to: Identify rank of name in year, and then find the
        // name at the corresponding rank in newYear.
        int rank = getRank(year, name, gender);
        // Name may not exist in most popular list for the year
        if (rank == -1){
            System.out.println("Rank unknown for " + name + ". Equivalent " +
                    "name cannot be found with dataset.");
        }
        else {
            String newName = getName(newYear, rank, gender);
            // Rank may not be found in dataset for newYear.
            if (newName.equals("NO NAME")){
                System.out.println("Name with equivalent popularity as " + name
                        + " in " + year + " not found in dataset for "
                        + newYear + ".");
            }
            else {
                System.out.println(name + " born in " + year + " would be "
                        + newName + " if she was born in " + newYear + ".");
            }
        }
    }

    public int getYearFromFile(File f){
        String name = f.getName();
        String year = name.substring(3, 7);
        return Integer.parseInt(year);
    }

    public int yearOfHighestRank(String name, String gender){
        // Method that identifies the year of highest rank for a
        // particular name.
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1;
        int highestYear = -1;
        for (File f : dr.selectedFiles()){
            int year = getYearFromFile(f);
            int rank = getRank(year, name, gender);
            if ((rank != -1)
                    && ((highestRank == -1) || (rank < highestRank))) {
                highestRank = rank;
                highestYear = year;
            }
        }
        return highestYear;
    }

    public double getAverageRank(String name, String gender){
        // Method to get the average rank of a name in selected files.
        DirectoryResource dr = new DirectoryResource();
        int rankSum = 0;
        int rankCount = 0;
        for (File f : dr.selectedFiles()){
            int year = getYearFromFile(f);
            int rank = getRank(year, name, gender);
            if (rank != -1) {
                rankSum += rank;
                rankCount ++;
            }
        }
        if (rankCount == 0){
            return -1.0;
        }
        else {
            return (double) rankSum / rankCount;
        }
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        // Method to calculate the number of births with names that are more
        // popular in the given year.
        int rank = getRank(year, name, gender);
        FileResource fr = getFileResourceForYear(year);
        CSVParser parser = fr.getCSVParser(false);
        int birthCount = 0;
        int currentRank = 0;

        // Loop through the csv file
        for (CSVRecord record : parser){
            if (record.get(1).equals(gender)) {
                currentRank ++;
                if ((rank == -1) || (rank > currentRank)) {
                    birthCount += Integer.parseInt(record.get(2));
                }
            }
            if ((rank != -1) && (currentRank >= rank)){
                break;
            }
        }
        return birthCount;
    }

    public static void main(String[] args) {
        // Main method for quiz answers.

        // Question 1
        BabyBirths bb = new BabyBirths();
        FileResource fr = new FileResource("data/us_babynames_by_year/yob1900.csv");
//        bb.totalBirths(fr);

        // Question 2
        fr = new FileResource("data/us_babynames_by_year/yob1905.csv");
//        bb.totalBirths(fr);

        // Question 3
        int rank = bb.getRank(1960, "Emily", "F");
//        System.out.println(rank);

        // Question 4
        rank = bb.getRank(1971, "Frank", "M");
//        System.out.println(rank);

        // Question 5
        String name = bb.getName(1980, 350, "F");
//        System.out.println(name);

        // Question 6
        name = bb.getName(1982, 450, "M");
//        System.out.println(name);

        // Question 7
//        bb.whatIsNameInYear("Susan", 1972, 2014, "F");

        // Question 8
//        bb.whatIsNameInYear("Owen", 1974, 2014, "M");

        // Question 9
//        int year = bb.yearOfHighestRank("Genevieve", "F");
//        System.out.println(year);

        // Question 10
//        int year = bb.yearOfHighestRank("Mich", "M");
//        System.out.println(year);

        // Question 11
//        double avgRank = bb.getAverageRank("Susan", "F");
//        System.out.println(avgRank);

        // Question 12
//        double avgRank = bb.getAverageRank("Robert", "M");
//        System.out.println(avgRank);

        // Question 13
        int numBirths = bb.getTotalBirthsRankedHigher(1990, "Emily", "F");
//        System.out.println(numBirths);

        // Question 14
        numBirths = bb.getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println(numBirths);

    }
}
