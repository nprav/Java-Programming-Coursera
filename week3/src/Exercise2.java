/*
Programming Exercise: Parsing Weather Data
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class Exercise2 {

    // Temperature related Methods
    public CSVRecord coldestHourInFile(CSVParser parser){
        // Method to get the coldest row from a
        // csv file with hourly temperatures.
        CSVRecord coldestRecord = null;
        for (CSVRecord record : parser) {
            Double temp = Double.parseDouble(record.get("TemperatureF"));
            if (!compareFloat(temp, -9999.0, 0.01)) {
                coldestRecord = getColdestRecord(coldestRecord, record);
            }
        }
        return coldestRecord;
    }

    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        File coldestFile = null;
        CSVRecord coldestRecord = null;

        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            if (coldestFile == null){
                coldestFile = f;
                coldestRecord = coldestHourInFile(parser);
            }
            else {
                CSVRecord coldestRecordInDay = coldestHourInFile(parser);
                coldestRecord = getColdestRecord(coldestRecord, coldestRecordInDay);
                if (coldestRecord == coldestRecordInDay){
                    coldestFile = f;
                }
            }
        }
        System.out.println("Coldest day was in file " + coldestFile.getName());
        System.out.println("Coldest temperature on that day was "
                + coldestRecord.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        printFileTemperatures(coldestFile);
        return coldestFile.getName();
    }

    public CSVRecord getColdestRecord(CSVRecord record1, CSVRecord record2){
        // Compare two rows and pick the row with the lower temperature
        // If one of the rows is null, return the other record. If both are
        // null, return null. Assumes that neither record has an invalid
        // temperature of -9999.

        if (record1 == null){
            return record2;
        }
        else if (record2 == null){
            return record1;
        }
        else {
            CSVRecord coldestRecord = record1;
            double temp1 = Double.parseDouble(record1.get("TemperatureF"));
            double temp2 = Double.parseDouble(record2.get("TemperatureF"));
            if (temp2 < temp1) {
                coldestRecord = record2;
            }
            return coldestRecord;
        }
    }

    public void printFileTemperatures(File f){
        // Method to print the temperatures from a particular file
        // given the filepath string.
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            System.out.println(record.get("DateUTC") + ": "
                    + record.get("TemperatureF"));
        }
    }

    public Double averageTemperatureInFile(CSVParser parser){
        // Method that returns the average temperature in a particular file.
        double sum = 0.;
        int count = 0;
        for (CSVRecord record : parser){
            double temp = Double.parseDouble(record.get("TemperatureF"));
            if (!compareFloat(temp, -9999.0, 0.1)){
                sum += temp;
                count ++;
            }
        }
        if (count == 0){
            return -9999.0;
        }
        else{
            return sum/count;
        }
    }

    public Double averageTemperatureWithHighHumidityInFile(
            CSVParser parser, int value){
        double sum = 0.;
        int count = 0;
        for (CSVRecord record : parser){
            double temp = Double.parseDouble(record.get("TemperatureF"));
            double humidity = Double.parseDouble(record.get("Humidity"));
            if (!compareFloat(temp, -9999.0, 0.1)
                    && (humidity >= value)){
                sum += temp;
                count ++;
            }
        }
        if (count == 0){
            return -9999.0;
        }
        else{
            return sum/count;
        }
    }

    // Humidity related Methods
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        // Method to get the lowest hourly humidity record in a file.
        // If two hours have the same humidity only the earlier hour is
        // returned.

        CSVRecord lowestHRecord = null;
        for (CSVRecord record : parser) {
            if (!record.get("Humidity").equals("N/A")){
                lowestHRecord = getLowestHumidityRecord(lowestHRecord, record);
            }
        }
        return lowestHRecord;
    }

    public CSVRecord getLowestHumidityRecord(CSVRecord record1, CSVRecord record2){
        // Compare two rows and pick the row with the lower humidity.
        // If one of the rows is null, return the other record. If both are
        // null, return null. Assumes that neither record has an invalid
        // humidity of "N/A".

        if (record1 == null){
            return record2;
        }
        else if (record2 == null){
            return record1;
        }
        else {
            CSVRecord lowestHRecord = record1;
            double hum1 = Double.parseDouble(record1.get("Humidity"));
            double hum2 = Double.parseDouble(record2.get("Humidity"));
            if (hum2 < hum1) {
                lowestHRecord = record2;
            }
            return lowestHRecord;
        }
    }

    public CSVRecord lowestHumidityInManyFiles(){
        // Method that returns the CSVRecord with the lowest
        // humidity over multiple selected files.
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHRecord = null;

        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            if (lowestHRecord == null){
                lowestHRecord = lowestHumidityInFile(parser);
            }
            else {
                CSVRecord lowestHRecordInDay = lowestHumidityInFile(parser);
                lowestHRecord = getLowestHumidityRecord(lowestHRecord, lowestHRecordInDay);
            }
        }
        return lowestHRecord;
    }

    // Utility Methods
    public void printRecord(CSVRecord record){
        // Method to print everything in record.
        System.out.println(record.toMap());
    }

    public Boolean compareFloat(double a, double b, double threshold){
        // Method to compare the values of two floats.
        return (Math.abs(a - b) < threshold);
    }
}
