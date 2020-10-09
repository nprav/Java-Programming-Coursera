/*
Strings Second Assignment
Part2: HowMany - Finding Multiple Occurrences
 */

package StringsSecondAssignment;

public class Part2 {
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

    public void testHowMany(){
        // Test function for howMany
        int test = 1;

        String stringb = "ATGAACGAATTGAATC";
        String stringa = "GAA";
        int result = howMany(stringa, stringb);
        System.out.println("result = " + result);
        if (result != 3){
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        stringb = "ATAAAA";
        stringa = "AA";
        result = howMany(stringa, stringb);
        System.out.println("result = " + result);
        if (result != 2){
            System.out.println("Error in Test " + test);
        }
        test = test + 1;

        stringb = "ATAAAA";
        stringa = "CC";
        result = howMany(stringa, stringb);
        System.out.println("result = " + result);
        if (result != 0){
            System.out.println("Error in Test " + test);
        }

        System.out.println("All tests complete.");
    }

    public static void main(String[] args) {
        Part2 part = new Part2();
        part.testHowMany();
    }
}
