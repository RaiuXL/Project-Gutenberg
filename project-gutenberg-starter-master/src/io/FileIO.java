package io;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides basic file io capabilities to read and
 * write from files in the project.
 */
public class FileIO
{
    /**
     * Accepts a file path and reads the contents from
     * the file line-by-line into an ArrayList. Each
     * element in the list will correspond to a line in
     * the file.
     *
     * @param path the path to the text file
     * @return an ArrayList of strings with the contents
     *         of the input file
     */
    public  ArrayList<String> readFile(String path)
    {
        ArrayList<String> pathContent = new ArrayList<>();
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                pathContent.add(scanner.nextLine());
            }
            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("Reading file: "+path);
        return pathContent;
    }

    /**
     * Accepts a file path and Arraylist of strings and
     * writes the strings to the file. Any previous contents
     * in the file should be overwritten.
     *
     * @param path the path to the text file
     * @param lines the ArrayList of strings to write to the
     *              input file
     */
    public void writeFile(String path, ArrayList<String> lines)
    {
        try(PrintWriter writer = new PrintWriter(new FileWriter(path, false))){
            for (String line : lines){
                writer.println(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Writing analytics in file: "+path);

    }
}













