/*
Strings Third Assignment
Test class
 */

package StringsThirdAssignment;

import edu.duke.StorageResource;

public class Test {
    private Part part;

    public Test(){
        part = new Part();
    }

    public void testFindStopCodon(){
        // Test function for findStopCodon.
        int test = 1;

        // DNA with no stopCodon found
        String dna = "ATACGGAGATACTAA";
        String stopCodon = "TGA";
        int result = part.findStopCodon(dna, 0, stopCodon);
        if (result!= dna.length()) System.out.println("Error in Test " + test);
        test = test + 1;

        dna = "ATACGGAGATACTAA";
        stopCodon = "TAA";
        result = part.findStopCodon(dna, 0, stopCodon);
        if (result!= 12) System.out.println("Error in Test " + test);
        test = test + 1;

        dna = "ATACGGAGATACTAA";
        stopCodon = "TAA";
        result = part.findStopCodon(dna, 1, stopCodon);
        if (result!= dna.length()) System.out.println("Error in Test " + test);
//        test = test + 1;

        System.out.println("StopCodon tests complete.");
    }

    public void testFindGene(){
        // Test function for findGene
        int test = 1;

        // DNA with no ATG
        String dna = "ATACGGAGATACTAA";
        String result = part.findGene(dna);
        System.out.println("Test " + test + "\n" + dna + "\n" + result);
        if (!result.equals("")){
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        // DNA with ATG and one valid stop Codon
        dna = "ATATGCGGAGATGACAA";
        result = part.findGene(dna);
        System.out.println("Test " + test + "\n" + dna + "\n" + result);
        if (!result.equals("ATGCGGAGATGA")) {
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        // DNA with ATG and multiple valid stop Codons
        dna = "ATATGCGGAGATGATAATAG";
        result = part.findGene(dna);
        System.out.println("Test " + test + "\n" + dna + "\n" + result);
        if (!result.equals("ATGCGGAGATGA")) {
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        // DNA with ATG and no valid stop Codons
        dna = "ATATGCGGAGAATGATAATAGCAA";
        result = part.findGene(dna);
        System.out.println("Test " + test + "\n" + dna + "\n" + result);
        if (!result.equals("")){
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        // DNA with ATG and no valid stop Codons
        dna = "ATATGATGCGGAGAATGATAATAGCAA";
        result = part.findGene(dna);
        System.out.println("Test " + test + "\n" + dna + "\n" + result);
        if (!result.equals("")){
            System.out.println("Error in Test " + test);
        }

        System.out.println("FindGene tests complete.");
    }

    public void testGetAllGenes(){
        // Test function for getAllGenes
        int test = 1;

        String dna = "AATGATGCGGAGATGATGGATGAATTAGAGCAA";
        StorageResource result = part.getAllGenes(dna);
        part.printAllGenes(dna);
        if (!result.contains("ATGATGCGGAGATGA") || !result.contains("ATGAATTAG")){
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        dna = "ATGTAAGATGCCCTAGT";
        result = part.getAllGenes(dna);
        part.printAllGenes(dna);
        if (!result.contains("ATGTAA") || !result.contains("ATGCCCTAG")){
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        dna = "ATGTAAGATGCCCTAGTATGAAATAACCG";
        result = part.getAllGenes(dna);
        part.printAllGenes(dna);
        if (
                !result.contains("ATGTAA")
                || !result.contains("ATGCCCTAG")
                || !result.contains("ATGAAATAA")
        ){
            System.out.println("Error in Test " + test);
        }

        System.out.println("getAllGenes tests completed.");
    }

    public void testCGRatio(){
        // Test function for cgRatio
        int test = 1;

        String dna = "CCCGGG";
        double result = part.cgRatio(dna);
        System.out.println(result);
        if (Math.abs(result - 1) > 0.0001){
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        dna = "AAAACG";
        result = part.cgRatio(dna);
        System.out.println(result);
        if (Math.abs(result - (float) 2/6) > 0.0001){
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        dna = "AAAATTTTTTTTTTTTTCG";
        result = part.cgRatio(dna);
        System.out.println(result);
        if (Math.abs(result - (float) 2/dna.length()) > 0.0001){
            System.out.println("Error in Test " + test);
        }

        System.out.println("cgRatio tests completed.");
    }

    public void testProcessGenes1(){
        // Test function for processGenes
        int test = 1;

        String dna = "AATGATGCGGAGATGATGGATGAATTAGAGCAA";
        StorageResource result = part.getAllGenes(dna);
        System.out.println("Test " + test);
        part.processGenes1(result);
        test = test + 1;

        dna = "ATGTAAGATGCCCTAGT";
        result = part.getAllGenes(dna);
        System.out.println("Test " + test);
        part.processGenes1(result);
        test = test + 1;

        dna = "ATGTAAGATGTTTAAATAGTATGAAATAACCG";
        result = part.getAllGenes(dna);
        System.out.println("Test " + test);
        part.processGenes1(result);
        test = test + 1;

        dna = "ATGCCATCCTAAGATGTTTAAATAGTATGAAATAACCG";
        result = part.getAllGenes(dna);
        System.out.println("Test " + test);
        part.processGenes1(result);
        test = test + 1;

        dna = "ATGCCATCCTAAGATGTTTAAATAGTATGAAATAACCGATGCCCCCATGA";
        result = part.getAllGenes(dna);
        System.out.println("Test " + test);
        part.processGenes1(result);

        System.out.println("processGenes tests completed.");
    }

    public static void main(String[] args) {
        Test test = new Test();
//        test.testFindStopCodon();
//        test.testFindGene();
        test.testGetAllGenes();
//        test.testCGRatio();
        test.testProcessGenes1();
    }
}
