/*
Testing class for Exercise2
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class Test2 {
    private Exercise2 ex;

    public Test2(){
        // Test class constructor.
        ex = new Exercise2();
    }

    public void testColdestHourInFile(){
        // Method to test coldestHouseInFile
//        String filepath = "nc_weather/2012/weather-2012-01-02.csv";
        String filepath = "nc_weather/2014/weather-2014-05-01.csv";
        FileResource fr = new FileResource(filepath);
        CSVParser parser = fr.getCSVParser();
        CSVRecord result = ex.coldestHourInFile(parser);
        ex.printRecord(result);
    }

    public void testFileWithColdestTemperature(){
        String coldestFile = ex.fileWithColdestTemperature();
        System.out.println(coldestFile);
    }

    public void testAverageTemperatureInFile(){
//        String filepath = "nc_weather/2014/weather-2014-01-20.csv";
//        String filepath = "nc_weather/2014/weather-2014-06-01.csv";
        String filepath = "nc_weather/2013/weather-2013-08-10.csv";

        FileResource fr = new FileResource(filepath);
        CSVParser parser = fr.getCSVParser();
        Double result = ex.averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + result);
    }

    public void testLowestHumidityInFile(){
//        String filepath = "nc_weather/2014/weather-2014-01-20.csv";
//        String filepath = "nc_weather/2014/weather-2014-04-01.csv";
//        String filepath = "nc_weather/2014/weather-2014-06-29.csv";
        String filepath = "nc_weather/2014/weather-2014-07-22.csv";
        FileResource fr = new FileResource(filepath);
        CSVParser parser = fr.getCSVParser();
        CSVRecord result = ex.lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + result.get("Humidity")
                + " at " + result.get("DateUTC"));
    }

    public void testLowestHumidityInManyFiles(){
        CSVRecord result = ex.lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + result.get("Humidity")
                + " at " + result.get("DateUTC"));
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
//        String filepath = "nc_weather/2014/weather-2014-01-20.csv";
//        String filepath = "nc_weather/2014/weather-2014-03-30.csv";
        String filepath = "nc_weather/2013/weather-2013-09-02.csv";
        FileResource fr = new FileResource(filepath);
        CSVParser parser = fr.getCSVParser();
        Double result = ex.averageTemperatureWithHighHumidityInFile(parser, 80);
        if (ex.compareFloat(result, -9999.0, 0.1)){
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("Average temperature in file is " + result);
        }

//        filepath = "nc_weather/2014/weather-2014-03-20.csv";
//        fr = new FileResource(filepath);
//        parser = fr.getCSVParser();
//        result = ex.averageTemperatureWithHighHumidityInFile(parser, 80);
//        if (ex.compareFloat(result, -9999.0, 0.1)){
//            System.out.println("No temperatures with that humidity");
//        }
//        else {
//            System.out.println("Average temperature in file is " + result);
//        }
    }

    public static void main(String[] args) {
        Test2 test = new Test2();
//        test.testColdestHourInFile();
        test.testFileWithColdestTemperature();
//        test.testAverageTemperatureInFile();
//        test.testAverageTemperatureWithHighHumidityInFile();
//        test.testLowestHumidityInFile();
//        test.testLowestHumidityInManyFiles();
    }
}
