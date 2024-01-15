package stats;
import java.util.ArrayList;

/**
 * Stores the lines of a book in an ArrayList
 * and calculates simple analytics on the text
 */
public class BookAnalytics
{
    private ArrayList<String> bookLines;

    public BookAnalytics(ArrayList<String> bookLines)
    {
        this.bookLines = bookLines;
    }

    public int wordCount()
    {
        int counter = 0;
        for (String line : bookLines) {
            String[] words = line.split("\\s+");
            int wordCount = words.length;
            counter+=wordCount;
        }
        return counter;
    }

    public int letterCount()
    {
        int counter = 0;
        for(String line : bookLines) {
            int characterCount = line.length();
            counter+=characterCount;
        }
        return counter;
    }

    public int lineCount()
    {
        int counter = 0;
        for (String line : bookLines) {
            counter++;
        }
        return counter;
    }
}
