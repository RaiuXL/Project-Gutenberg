package program;
import io.FileIO;
import stats.BookAnalytics;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * This class represents the console program that
 * will allow users to open and analyze the contents
 * of e-books found with Project Gutenberg.
 */
public class PGConsoleProgram {
    static Scanner scanner = new Scanner(System.in);
    static FileIO fileIO = new FileIO();

    public static void main(String[] args) {

        //Main
        projectGutenberg();

    }

    public static void projectGutenberg() {
        // Menu
        System.out.println(dialog());

        int userChoice = scanner.nextInt();

        switch (userChoice) {
            case 1:
                openFile();
                break;
            case 2:
                printFile();
                break;
            case 3:
                quitProgram();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public static void openFile() {

        String userEBook = getUserBook();

        String userEBookFilePath = "files" + File.separator + "e-books" + File.separator + userEBook;
        String userSummariesFilePath = "files" + File.separator + "summaries" + File.separator + userEBook;

        // Create an ArrayList<> to hold user's chosen e-book contents
        ArrayList<String> userChoiceEBookContent = fileIO.readFile(userEBookFilePath);

        // BookAnalytic object based on ArrayList<String> userChoiceEBookContent
        BookAnalytics userEBookAnalytics = new BookAnalytics(userChoiceEBookContent);

        // Write in String userSummariesFilePath
        ArrayList<String> contentToWrite = new ArrayList<>();
        contentToWrite.add("Analytics for " + userEBook);
        contentToWrite.add("------------------------------------------\n");
        contentToWrite.add("Word Count: " + userEBookAnalytics.wordCount());
        contentToWrite.add("Letter Count: " + userEBookAnalytics.letterCount());
        contentToWrite.add("Line Count: " + userEBookAnalytics.lineCount());

        // Call fileIO.writeFile to save the analytics data to another file
        fileIO.writeFile(userSummariesFilePath, contentToWrite);
    }

    public static void printFile() {

        String userEBook = getUserBook();

        String userEBookFilePath = "files" + File.separator + "e-books" + File.separator + userEBook;

        // Create an ArrayList<> to hold user's chosen e-book contents
        ArrayList<String> userChoiceEBookContent = fileIO.readFile(userEBookFilePath);

        int counter = 1;
        for (String line : userChoiceEBookContent) {
            System.out.println(counter + ": " + line);
            counter++;
        }
    }

    public static String getUserBook(){

        // Creating Objects to use
        String directoryPath = "files/e-books"; // Creating for a file path
        File directory = new File(directoryPath); // Create for for-loop to use
        File[] files = directory.listFiles(); // creates a files array filled with e-books for each index
        Map<Integer, String> bookMap = new HashMap<>(); // To have the user choose a number and have it be retrieved from the hashmap, stores int/String

        // If the file directory from String directoryPath exists and is a directory
        if (directory.exists() && directory.isDirectory()) {

            int count = 0; // Helps tie the numbering system the user sees in console match with bookMap

            // This prints out each .txt numbered and initialized HashMap with int/files (Key/Value)
            for (File file : files) {  // for each file in File[] files
                if (file.isFile()) {
                    bookMap.put(count, file.getName()); // assign elements in HashMap (int, string)
                    System.out.println(count + ": " + file.getName()); // print out a numbered list of the file names
                    count++;
                }
            }

            // Get user input
            System.out.println("Enter a book number:");
            int userChoice2 = scanner.nextInt();

            // User choice is then matched with the corresponding e-book from HashMap
            String userEBook = bookMap.get(userChoice2);

            return userEBook;

        } else {
            return "Cannot locate directory-";
        }
    }

    public static void quitProgram() {
        System.out.println("Exiting the program. Goodbye!");

        System.exit(0);
    }

    public static String dialog() {
        String statement = "\nWelcome to the Project Gutenberg Program\n------------------------------------------\nProject Gutenberg provides free e-books\nfor books that have moved into the public\ndomain. You can read more about the project\nhere: https://www.qutenberg.org/\n------------------------------------------" + "\n1. Open a file\n2. Print file\n3. Exit";

        return statement;
    }

    } // End of Class



