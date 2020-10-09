package StringsFirstAssigment;/*
Strings First Assignment
StringsFirstAssigment.Part4: Finding Web Links
 */

import edu.duke.URLResource;

public class Part4 {
    public String findMiddleString(String line, String start,
                                 String stop){
        // Method to strings between specific strings
        int startIndex = line.indexOf(start);
        // Return "" if no start pattern
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = line.indexOf(stop, startIndex + start.length());
        // Return "" if no stop pattern
        if (stopIndex == -1) {
            return "";
        }
        return line.substring(startIndex + start.length(), stopIndex);
    }
    public void printWebPageLines(String page){
        // Initial method to test out URLResource class
        // Method to print out entire webpage
        URLResource resource = new URLResource(page);
        for (String line : resource.lines()){
            System.out.println(line);
        }
    }
    public void printWebPageWords(String page){
        // Initial method to test out URLResource class
        // Method to print out entire webpage
        URLResource resource = new URLResource(page);
        for (String word : resource.words()){
            System.out.println(word);
        }
    }
    public void printAllYouTubeLinks(String page){
        // Method to print all Youtube hyperlinks
        URLResource resource = new URLResource(page);
        String link_start = "href=\"";
        String link_stop = "\"";
        for (String line : resource.lines()){
            String link = findMiddleString(line, link_start, link_stop);
            if ((!link.equals("")) && isYoutubeLink(link)) {
                System.out.println(link);
            }
        }
    }
    public Boolean isYoutubeLink(String link){
        // Method to check if a link is a valid youtube link
        return link.toLowerCase().contains("youtube.com");
    }
    public static void main(String[] args) {
        String page = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        Part4 part = new Part4();
//        part.printWebPageLines(page);
//        part.printWebPageWords(page);
        part.printAllYouTubeLinks(page);
    }
}
