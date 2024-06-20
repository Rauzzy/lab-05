/**
 * Exception thrown for errors related to DiaryEntry validation.
 * @author Marius Ruzzel Guerra
 */
public class DiaryEntryException extends Exception
{
    /**
     * Constructs a new DiaryEntryException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public DiaryEntryException(final String message)
    {
        super(message);
    }
}
