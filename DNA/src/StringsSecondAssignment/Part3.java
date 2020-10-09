/*
Strings Second Assignment
Part3: How Many Genes
*/

package StringsSecondAssignment;

public class Part3 {
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

    public int countGenes(String dna){
        // Method to count the number of valid genes
        // (as defined in the findGene method).
        String gene = findGene(dna);
        if (gene.isEmpty()){
            return 0;
        }
        else {
            int startIndex = dna.indexOf(gene);
            dna = dna.substring(startIndex + gene.length());
            return 1 + countGenes(dna);
        }
    }

    public void testCountGenes(){
        // Test function for findGene
        int test = 1;

        String dna = "AATGATGCGGAGATGATGGATGAATTAGAGCAA";
        int result = countGenes(dna);
        System.out.println("result = " + result);
        if (result != 2){
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        dna = "ATGTAAGATGCCCTAGT";
        result = countGenes(dna);
        System.out.println("result = " + result);
        if (result != 2){
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        dna = "ATGTAAGATGCCCTAGTATGAAATAACCG";
        result = countGenes(dna);
        System.out.println("result = " + result);
        if (result != 3){
            System.out.println("Error in Test " + test);
        }

        System.out.println("All tests completed.");
    }

    public static void main(String[] args) {
        Part3 part = new Part3();
        part.testCountGenes();
    }
}
