/**
 * Represents a diary entry with a date and content.
 */
public class DiaryEntry
{
    private final static String ILLEGAL_WORD;  // Word not allowed in diary content
    private final static String DATE_REGEX;    // Regular expression for date format

    static
    {
        ILLEGAL_WORD = "bcit";      // Example of an illegal word in content
        DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";  // Regular expression for YYYY-MM-DD date format
    }

    private final String date;   // Date of the diary entry (formatted as YYYY-MM-DD)
    private final String content;  // Content of the diary entry

    /**
     * Constructs a new DiaryEntry object with given date and content.
     * Validates the provided date and content to ensure they meet criteria.
     *
     * @param date The date of the diary entry in YYYY-MM-DD format.
     * @param content The content of the diary entry.
     * @throws DiaryEntryException If date is null or not in the correct format,
     *                             or if content is null, empty, or contains an illegal word.
     */
    public DiaryEntry(final String date, final String content) throws DiaryEntryException
    {
        validateDiaryEntry(date, content);
        this.date = date;
        this.content = content;
    }

    /**
     * Validates the diary entry's date and content.
     *
     * @param date The date to validate.
     * @param content The content to validate.
     * @throws DiaryEntryException If date is null or not in the correct format,
     *                             or if content is null, empty, or contains an illegal word.
     */
    private static void validateDiaryEntry(final String date, final String content)
            throws DiaryEntryException
    {
        if (date == null || !date.matches(DATE_REGEX))
        {
            throw new DiaryEntryException("Invalid date format: " + date);
        }
        if (content == null || content.isBlank() || content.toLowerCase().contains(ILLEGAL_WORD))
        {
            throw new DiaryEntryException("Invalid content: " + content);
        }
    }

    /**
     * Returns the date of the diary entry.
     *
     * @return The date of the diary entry.
     */
    public String getDate()
    {
        return date;
    }

    /**
     * Returns the content of the diary entry.
     *
     * @return The content of the diary entry.
     */
    public String getContent()
    {
        return content;
    }
}
