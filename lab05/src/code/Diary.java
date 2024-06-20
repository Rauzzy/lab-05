import java.io.*;
import java.util.Scanner;

/**
 * The Diary class manages diary entries stored in a text file.
 */
public class Diary
{
    // Constants for file name, split limit, array indices, and expected length of entries
    private final static String DIARY_FILE;   // File where diary entries are stored
    private final static int SPLIT_LIMIT;     // Limit for splitting date and content
    private final static int FIRST_ARRAY_INDEX;  // Index for date in split parts
    private final static int SECOND_ARRAY_INDEX; // Index for content in split parts
    private final static int EXPECTED_LENGTH;    // Expected length of split parts

    // Static initialization block to initialize constants
    static
    {
        DIARY_FILE = "diary.txt";   // File name where diary entries are stored
        SPLIT_LIMIT = 2;            // Split limit for splitting date and content
        FIRST_ARRAY_INDEX = 0;      // Index of date in split parts array
        SECOND_ARRAY_INDEX = 1;     // Index of content in split parts array
        EXPECTED_LENGTH = 2;        // Expected length of split parts array
    }

    /**
     * Adds a new diary entry to the diary file.
     */
    public void addEntry()
    {
        final Scanner keyboardScanner;   // Scanner to read from keyboard
        FileWriter fileWriter = null;    // FileWriter to write to file
        final String content;            // Content of the diary entry
        final String date;               // Date of the diary entry

        keyboardScanner = new Scanner(System.in);

        // Prompt user for date and content
        System.out.println("Enter date (YYYY-MM-DD): ");
        date = keyboardScanner.nextLine();
        System.out.println("Enter content: ");
        content = keyboardScanner.nextLine();

        try
        {
            // Create a new DiaryEntry object with provided date and content
            final DiaryEntry diaryEntry;
            diaryEntry = new DiaryEntry(date, content);

            // Open diary file in append mode
            fileWriter = new FileWriter(DIARY_FILE, true);

            // Write date and content to file, separated by '|'
            fileWriter.write(diaryEntry.getDate() + "|" + diaryEntry.getContent());
            fileWriter.write("\n");
        }
        catch(DiaryEntryException | IOException e)
        {
            // Handle exceptions related to DiaryEntry or IO
            System.err.println("Error creating DiaryEntry: " + e);
            e.getStackTrace();
        }
        finally
        {
            try
            {
                // Close file writer in finally block to ensure it's always closed
                if(fileWriter != null)
                {
                    fileWriter.close();
                }
            }
            catch(IOException e)
            {
                // Handle IO exception while closing the file
                System.out.println("Error closing file: " + e);
            }
        }
    }

    /**
     * Displays all diary entries stored in the diary file.
     */
    public void viewAllEntries()
    {
        final Reader reader;        // Reader to read from file
        final Scanner scanner;      // Scanner to parse file content

        try
        {
            // Open diary file for reading
            reader = new FileReader(DIARY_FILE);
            scanner = new Scanner(reader);

            // Read each line from the file and display date and content
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] parts;

                // Split line into date and content using '|'
                parts = line.split("\\|", SPLIT_LIMIT);

                // Check if split produced expected parts
                if (parts.length == EXPECTED_LENGTH)
                {
                    System.out.println("Date: " + parts[FIRST_ARRAY_INDEX]);
                    System.out.println("Content: " + parts[SECOND_ARRAY_INDEX]);
                    System.out.println();
                }
            }
        }
        catch(FileNotFoundException e)
        {
            // Handle file not found exception
            System.out.println("Error reading the file: " + e);
            e.getStackTrace();
        }
    }

    /**
     * Searches for diary entries by a specific date.
     */
    public void searchEntriesByDate()
    {
        final String date;           // Date to search for
        final Scanner keyboardScanner;   // Scanner to read from keyboard
        final Scanner readerScanner; // Scanner to read from file
        final Reader reader;         // Reader to read from file

        keyboardScanner = new Scanner(System.in);

        // Prompt user for date to search
        System.out.println("Enter date to search (YYYY-MM-DD): ");
        date = keyboardScanner.nextLine();

        try
        {
            // Open diary file for reading
            reader = new FileReader(DIARY_FILE);
            readerScanner = new Scanner(reader);
            boolean found;
            found = false;

            // Read each line from the file and check for matching date
            while(readerScanner.hasNextLine())
            {
                String line = readerScanner.nextLine();
                String[] parts = line.split("\\|", SPLIT_LIMIT);

                // Check if split produced expected parts and if date matches
                if (parts.length == EXPECTED_LENGTH && parts[FIRST_ARRAY_INDEX].equals(date))
                {
                    System.out.println("Date: " + parts[FIRST_ARRAY_INDEX]);
                    System.out.println("Content: " + parts[SECOND_ARRAY_INDEX]);
                    System.out.println();
                    found = true;
                }
            }
            // If no entries were found for the given date
            if(!found)
            {
                System.out.println("No entries found for date: " + date);
            }
        }
        catch(FileNotFoundException e)
        {
            // Handle file not found exception
            System.out.println("Error opening the file: " + e);
            e.getStackTrace();
        }
    }
}
