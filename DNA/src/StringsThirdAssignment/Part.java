/*
Strings Third Assignment
Part1: Use StorageResource to store Genes
 */

package StringsThirdAssignment;
import edu.duke.StorageResource;
import edu.duke.FileResource;

public class Part {
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

    public StorageResource getAllGenes(String dna){
        StorageResource resource = new StorageResource();
        String remain_dna = dna;
        while (true){
            String gene = findGene(remain_dna);
            if (gene.isEmpty()){
                break;
            }
            else {
                resource.add(gene);
                int startIndex = remain_dna.indexOf(gene);
                remain_dna = remain_dna.substring(startIndex + gene.length());
            }
        }
        return resource;
    }

    public void printAllGenes(String dna){
        StorageResource genes = getAllGenes(dna);
        for (String gene : genes.data()){
            System.out.println(gene);
        }
    }

    public void printSR(StorageResource sr){
        for (String s : sr.data()){
            System.out.println(s);
        }
    }

    public int getSRLongestStringLength(StorageResource sr){
        int length = 0;
        for (String s : sr.data()){
            length = Math.max(length, s.length());
        }
        return length;
    }

    public int howMany(String stringa, String stringb){
        // Method to returns an integer for the number of times stringa
        // appears in stringb. Each occurrence of stringa must not overlap
        // with string b
        int startIndex = stringb.indexOf(stringa);
        if (startIndex == -1){
            return 0;
        }
        else {
            String new_stringb = stringb.substring(startIndex + stringa.length());
            return 1 + howMany(stringa, new_stringb);
        }
    }

    public double cgRatio(String dna){
        // Method that takes dna parameter and returns
        // ratio of number of C's and G's over dna length.
        int countC = howMany("C", dna);
        int countG = howMany("G", dna);
        return (float) (countC + countG)/dna.length();
    }

    public double countCTG(String dna){
        // Method to return number of times the Codon CTG appears.
        // Wrapper for the howMany method.
        return howMany("CTG", dna);
    }

    public StorageResource filterNumCharGenes(StorageResource sr, int charLim){
        // Filters Storage resource to only keep genes with > 9chars
        StorageResource filtered = new StorageResource();
        for (String gene : sr.data()){
            if (gene.length() > charLim){
                filtered.add(gene);
            }
        }
        return filtered;
    }

    public StorageResource filterCGratioGenes(StorageResource sr){
        // Filters Storage resource to only keep genes cgRatio > 0.35
        StorageResource filtered = new StorageResource();
        for (String gene : sr.data()){
            if (cgRatio(gene) > 0.35){
                filtered.add(gene);
            }
        }
        return filtered;
    }

    public void processGenes1(StorageResource sr){
        // Method to process genes
        // Print all genes longer than 9 chars
        // Print # of strings that are longer than 9 chars
        StorageResource sr_9char = filterNumCharGenes(sr, 9);
        System.out.println("Genes > 9 characters long:");
        System.out.println("Count = " + sr_9char.size());
        printSR(sr_9char);
        System.out.println("");

        // Print Strings with CGratio > 0.35
        // Print # of strings with CG ratio > 0.35
        StorageResource sr_cgratio = filterCGratioGenes(sr);
        System.out.println("Genes with CG Ratio > 0.35:");
        System.out.println("Count = " + sr_cgratio.size());
        printSR(sr_cgratio);
        System.out.println("");

        // Print length of the longest gene
        System.out.println("Longest gene length = "
                + getSRLongestStringLength(sr));
        System.out.println("--------");
    }

    public void processGenes2(StorageResource sr){
        // Method to process genes
        // Print all genes longer than 60 chars
        // Print # of strings that are longer than 60 chars
        StorageResource sr_60char = filterNumCharGenes(sr, 60);
        System.out.println("Genes > 60 characters long:");
        System.out.println("Count = " + sr_60char.size());
        printSR(sr_60char);
        System.out.println("");

        // Print Strings with CGratio > 0.35
        // Print # of strings with CG ratio > 0.35
        StorageResource sr_cgratio = filterCGratioGenes(sr);
        System.out.println("Genes with CG Ratio > 0.35:");
        System.out.println("Count = " + sr_cgratio.size());
        printSR(sr_cgratio);
        System.out.println("");

        // Print length of the longest gene
        System.out.println("Longest gene length = "
                + getSRLongestStringLength(sr));
        System.out.println("--------");
    }

    public static void main(String[] args) {
        Part part = new Part();
//        FileResource fr = new FileResource("examples/brca1line.fa");
        FileResource fr = new FileResource("examples/GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println(dna);
        StorageResource genes = part.getAllGenes(dna);
        System.out.println("Gene Count = " + genes.size());
        part.processGenes2(genes);
        System.out.println("CTG count = " + part.countCTG(dna));
    }
}

