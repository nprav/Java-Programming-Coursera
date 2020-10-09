/*
Debugging Practice Quiz 1
 */

public class Debugging {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if ((index == -1) || (index+4 > input.length())) {
                break;
            }
//            System.out.println(index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
//            System.out.println(index);
        }
    }
    public void test() {
//        findAbc("abcd");
//        findAbc("abcdabc");
//        findAbc("yabcyabc");
//        findAbc("abcbbbabcdddabc");
//        findAbc("aaaaabcd");
        findAbc("abcabcabcabca");
    }

    public static void main(String[] args) {
        Debugging debug = new Debugging();
        debug.test();
    }
}
