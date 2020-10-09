package StringsFirstAssigment;
/*
Strings First Assignments
StringsFirstAssigment.Part3: Problem Solving with Strings
 */

public class Part3 {
    public Boolean twoOccurrences(String stringa, String stringb){
        int index1 = stringb.indexOf(stringa);
        if (index1 == -1){
            return false;
        }
        int index2 = stringb.indexOf(stringa,
                index1+stringa.length());
        if (index2 == -1){
            return false;
        }
        return true;
    }
    public String lastPart(String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        if (startIndex == -1){
            return stringb;
        }
        else{
            return stringb.substring(startIndex + stringa.length());
        }
    }
    public void testing(){
        String stringa = null;
        String stringb = null;
        Boolean result = null;
        String result2 = null;

        stringa = "by";
        stringb = "A story by Abby Long";
        result = twoOccurrences(stringa, stringb);
        System.out.println("String a: " + stringa);
        System.out.println("String b: " + stringb);
        System.out.println("Results = " + result);
        System.out.println();

        stringa = "a";
        stringb = "banana";
        result = twoOccurrences(stringa, stringb);
        System.out.println("String a: " + stringa);
        System.out.println("String b: " + stringb);
        System.out.println("Results = " + result);
        System.out.println();

        stringa = "A";
        stringb = "banana";
        result = twoOccurrences(stringa, stringb);
        System.out.println("String a: " + stringa);
        System.out.println("String b: " + stringb);
        System.out.println("Results = " + result);
        System.out.println();

        stringa = "atg";
        stringb = "ctgtatgta";
        result = twoOccurrences(stringa, stringb);
        System.out.println("String a: " + stringa);
        System.out.println("String b: " + stringb);
        System.out.println("Results = " + result);
        System.out.println();

        stringa = "an";
        stringb = "banana";
        result2 = lastPart(stringa, stringb);
        System.out.println("String a: " + stringa);
        System.out.println("String b: " + stringb);
        System.out.println("Results = " + result2);
        System.out.println();

        stringa = "an";
        stringb = "banAna";
        result2 = lastPart(stringa, stringb);
        System.out.println("String a: " + stringa);
        System.out.println("String b: " + stringb);
        System.out.println("Results = " + result2);
        System.out.println();

        stringa = "zoo";
        stringb = "forest";
        result2 = lastPart(stringa, stringb);
        System.out.println("String a: " + stringa);
        System.out.println("String b: " + stringb);
        System.out.println("Results = " + result2);
        System.out.println();
    }
    public static void main(String[] args) {
        Part3 part = new Part3();
        part.testing();
    }
}
