/*
Programming exercise 1: Parsing Export Data
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class Exercise1 {
    public void test(){
        FileResource fr = new FileResource("exports/exportdata.csv");

        CSVParser parser = fr.getCSVParser();
        String result = countryInfo(parser, "Nauru");
        System.out.println(result);

        parser = fr.getCSVParser();
//        listExportersTwoProducts(
//                parser, "gold", "diamonds");
        listExportersTwoProducts(
                parser, "cotton", "flowers");

        parser = fr.getCSVParser();
        int num = numberOfExporters(parser, "cocoa");
        System.out.println(num);

        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }

    public String countryInfo(CSVParser parser, String country){
        // Method to return a string of information about a country.
        // If no information is found, returns NOT FOUND.
        String result = "NOT FOUND";
        for (CSVRecord record : parser){
            if (country.equals(record.get("Country"))){
                result = country + ": " + record.get("Exports")
                        + ": " + record.get("Value (dollars)");
            }
        }
        return result;
    }

    public void listExportersTwoProducts(
            CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser){
            if (record.get("Exports").contains(exportItem)){
                count = count + 1;
            }
        }
        return count;
    }

    public void bigExporters(CSVParser parser, String amount){
        // Method to print countries with export value length greater than
        // the length of amount.
        for (CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if (amount.length() < value.length()){
                System.out.println(record.get("Country") + " " + value);
            }
        }
    }

    public static void main(String[] args) {
        Exercise1 ex = new Exercise1();
        ex.test();
    }
}
