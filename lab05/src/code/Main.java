import java.util.Scanner;

/**
 * The Main class for the Personal Management System application.
 * This class contains the main method which is the entry point of the application.
 * The application allows users to add new diary entries, view all entries,
 * search entries by date, and exit the system.
 * @author Marius Guerra
 */
public class Main
{
    private final static String FIRST_CHOICE;
    private final static String SECOND_CHOICE;
    private final static String THIRD_CHOICE;
    private final static String FOURTH_CHOICE;

    // Static block to initialize constants
    static
    {
        FIRST_CHOICE = "1";
        SECOND_CHOICE = "2";
        THIRD_CHOICE = "3";
        FOURTH_CHOICE = "4";
    }

    /**
     * The main method of the application.
     * This method runs an infinite loop to display a menu to the user
     * and performs actions based on the user's choice.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(final String[] args)
    {
        final Diary diary;
        final Scanner userInput;
        diary = new Diary();
        userInput = new Scanner(System.in);

        while(true)
        {
            System.out.println("Personal management System");
            System.out.println(FIRST_CHOICE + ". Add New Entry");
            System.out.println(SECOND_CHOICE + ". View all Entries");
            System.out.println(THIRD_CHOICE + ". Search Entries by Date");
            System.out.println(FOURTH_CHOICE + ". Exit");
            String choice;

            System.out.print("Choose option: ");
            choice = userInput.nextLine();

            if(choice.equals(FIRST_CHOICE))
            {
                diary.addEntry();
            }
            if(choice.equals(SECOND_CHOICE))
            {
                diary.viewAllEntries();
            }
            if(choice.equals(THIRD_CHOICE))
            {
                diary.searchEntriesByDate();
            }
            if(choice.equals(FOURTH_CHOICE))
            {
                System.out.println("Exiting... Goodbye!");
                userInput.close();
                return;
            }
        }
    }
}