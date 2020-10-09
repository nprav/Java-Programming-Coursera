package StringsFirstAssigment;
/* Strings First Assignments
Part 2: Finding a Gene - Using a Simplified Algorithm Reorganized
 */

public class Part2 {
    public String findSimpleGene(String dna, String startCodon,
                                 String stopCodon){
        // Modify start and stop codon to match the case of dna
        if (dna.equals(dna.toLowerCase())){
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        if (dna.equals(dna.toUpperCase())){
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        }
        // Method to find a gene sequence in a string
        // if it exists.
        int startIndex = dna.indexOf(startCodon);
        // Return "" if no start codon
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
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
        // Define start and stop Codons
        String startCodon = "ATG";
        String stopCodon = "TAA";

        // DNA with no ATG
        String dna = "ATACGGAGATACTAA";
        String gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("DNA Sequence: " + dna);
        System.out.println("Gene: " + gene);

        // DNA with no TAA
        dna = "ATACGGATGATAC";
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("DNA Sequence: " + dna);
        System.out.println("Gene: " + gene);

        // DNA with no ATG or TAA, lower case
        dna = "ATACGGAGATACTACA".toLowerCase();
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("DNA Sequence: " + dna);
        System.out.println("Gene: " + gene);

        // DNA with ATG, TAA and substring length a multiple of 3
        dna = "ATACGGATGATACGCTAAGGATC";
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("DNA Sequence: " + dna);
        System.out.println("Gene: " + gene);

        // DNA with lower case ATG, TAA and substring length a multiple of 3
        dna = "ATACGGATGATACGCTAAGGATC".toLowerCase();
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("DNA Sequence: " + dna);
        System.out.println("Gene: " + gene);

        // DNA with ATG, TAA and substring length not a multiple of 3
        dna = "ATACGGATGACTACGCTAAGGATC";
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("DNA Sequence: " + dna);
        System.out.println("Gene: " + gene);
    }
    public static void main(String[] args) {
        Part2 part = new Part2();
        part.testSimpleGene();
    }
}
