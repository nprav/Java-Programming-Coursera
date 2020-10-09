/*
Strings Second Assignments
StringsSecondAssigment.Part1: Finding Many Genes
 */

package StringsSecondAssignment;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        // Method to find the index of a stopCodon in a dna string.
        // Return index = dna.length() if the stopCodon is not found.
        // stopCodon is only valid if resulting gene length is a multiple of 3.
        // startIndex represents first occurrence of ATG.
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1){
            if ((currIndex - startIndex)%3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }

    public void testFindStopCodon(){
        // Test function for findStopCodon.
        int test = 1;

        // DNA with no stopCodon found
        String dna = "ATACGGAGATACTAA";
        String stopCodon = "TGA";
        int result = findStopCodon(dna, 0, stopCodon);
        if (result!= dna.length()) System.out.println("Error in Test " + test);
        test = test + 1;

        dna = "ATACGGAGATACTAA";
        stopCodon = "TAA";
        result = findStopCodon(dna, 0, stopCodon);
        if (result!= 12) System.out.println("Error in Test " + test);
        test = test + 1;

        dna = "ATACGGAGATACTAA";
        stopCodon = "TAA";
        result = findStopCodon(dna, 1, stopCodon);
        if (result!= dna.length()) System.out.println("Error in Test " + test);
//        test = test + 1;

        System.out.println("All tests complete.");
    }

    public String findGene(String dna){
        // Method to find valid sequences of genes.
        // Valid stop codons = TAA, TGA, TAG
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int temp = Math.min(taaIndex, tgaIndex);
        int stopIndex = Math.min(tagIndex, temp);
        if (stopIndex == dna.length()) {
            return "";
        }
        else {
            return dna.substring(startIndex, stopIndex+3);
        }
    }
    public void testFindGene(){
        // Test function for findGene
        int test = 1;

        // DNA with no ATG
        String dna = "ATACGGAGATACTAA";
        String result = findGene(dna);
        System.out.println("Test " + test + "\n" + dna + "\n" + result);
        if (!result.equals("")){
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        // DNA with ATG and one valid stop Codon
        dna = "ATATGCGGAGATGACAA";
        result = findGene(dna);
        System.out.println("Test " + test + "\n" + dna + "\n" + result);
        if (!result.equals("ATGCGGAGATGA")) {
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        // DNA with ATG and multiple valid stop Codons
        dna = "ATATGCGGAGATGATAATAG";
        result = findGene(dna);
        System.out.println("Test " + test + "\n" + dna + "\n" + result);
        if (!result.equals("ATGCGGAGATGA")) {
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        // DNA with ATG and no valid stop Codons
        dna = "ATATGCGGAGAATGATAATAGCAA";
        result = findGene(dna);
        System.out.println("Test " + test + "\n" + dna + "\n" + result);
        if (!result.equals("")){
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        // DNA with ATG and no valid stop Codons
        dna = "ATATGATGCGGAGAATGATAATAGCAA";
        result = findGene(dna);
        System.out.println("Test " + test + "\n" + dna + "\n" + result);
        if (!result.equals("")){
            System.out.println("Error in Test " + test);
        }

        System.out.println("All tests complete.");
    }

    public void printAllGenes(String dna){
        String remain_dna = dna;
        while (true){
            String gene = findGene(remain_dna);
            if (gene.isEmpty()){
                break;
            }
            else {
                System.out.println(gene);
                int startIndex = remain_dna.indexOf(gene);
                remain_dna = remain_dna.substring(startIndex + gene.length());
            }
        }
    }

    public static void main(String[] args) {
        Part1 part = new Part1();
        part.testFindStopCodon();
//        part.testFindGene();
//        String dna = "AATGATGCGGAGATGATGGATGAATTAGAGCAA";
//        System.out.println(dna);
//        part.printAllGenes(dna);
    }
}
