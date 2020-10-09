package StringsFirstAssigment;/* Strings First Assignments
Part 1: Finding a Gene - Using a Simplified Algorithm
 */

public class Part1 {
    public String findSimpleGene(String dna){
        // Method to find a gene sequence in a string
        // if it exists.
        int startIndex = dna.indexOf("ATG");
        // Return "" if no start codon
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        // Return "" if no stop codon
        if (stopIndex == -1) {
            return "";
        }
        // If sequence is a multiple of 3, return sequence
        // otherwise return nothing since there is no valid
        // gene.
        if ((stopIndex - startIndex)%3==0){
            return dna.substring(startIndex, stopIndex+3);
        }
        else {
            return "";
        }
    }
    public void testSimpleGene(){
        // DNA with no ATG
        String dna = "ATACGGAGATACTAA";
        String gene = findSimpleGene(dna);
        System.out.println("DNA Sequence: " + dna);
        System.out.println("Gene: " + gene);

        // DNA with no TAA
        dna = "ATACGGATGATAC";
        gene = findSimpleGene(dna);
        System.out.println("DNA Sequence: " + dna);
        System.out.println("Gene: " + gene);

        // DNA with no ATG or TAA
        dna = "ATACGGAGATACTACA";
        gene = findSimpleGene(dna);
        System.out.println("DNA Sequence: " + dna);
        System.out.println("Gene: " + gene);

        // DNA with ATG, TAA and substring length a multiple of 3
        dna = "ATACGGATGATACGCTAAGGATC";
        gene = findSimpleGene(dna);
        System.out.println("DNA Sequence: " + dna);
        System.out.println("Gene: " + gene);

        // DNA with ATG, TAA and substring length not a multiple of 3
        dna = "ATACGGATGACTACGCTAAGGATC";
        gene = findSimpleGene(dna);
        System.out.println("DNA Sequence: " + dna);
        System.out.println("Gene: " + gene);
    }
    public static void main(String[] args) {
        Part1 part = new Part1();
        part.testSimpleGene();
    }
}
