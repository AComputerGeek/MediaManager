/**
 * This is AudioFile class. It's a child of MusicMedia class.
 *
 * @author  Amir Armion
 * @version V.01
 */
public class AudioFile extends MusicMedia implements FileManager
{
    private String fileName;
    private int    fileResolution;

    public static final int NONE = 0;

    {
        fileName       = "Untitled";
        fileResolution = NONE;
    }

    public AudioFile()
    {

    }

    /**
     * @param sku is the Stock Keeping Unit (an identification code) for this music media.
     * @param title is the title for this music media.
     * @param artist is the artist name for this music media.
     * @param year is the year this music media produced.
     * @param fileName is the file name for this Audio file.
     * @param fileResolution is the file resolution for this audio file.
     */
    public AudioFile(final String sku, final String title, final String artist, final int year, final String fileName,
                     final int fileResolution)
    {
        super(sku, title, artist, year);

        validateFileName(fileName);
        validateFileResolution(fileResolution);

        this.fileName       = fileName;
        this.fileResolution = fileResolution;
    }

    /**
     * @param sku is the Stock Keeping Unit (an identification code) for this music media.
     * @param title is the title for this music media.
     * @param artist is the artist name for this music media.
     * @param year is the year this music media produced.
     * @param fileName is the file name for this Audio file.
     */
    public AudioFile(final String sku, final String title, final String artist, final int year, final String fileName)
    {
        this(sku, title, artist, year, fileName, NONE);
    }

    /**
     * @return the file name.
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * @return the file resolution.
     */
    public int getFileResolution()
    {
        return fileResolution;
    }

    /**
     * @param fileName is the file name.
     */
    public void setFileName(final String fileName)
    {
        validateFileName(fileName);

        this.fileName = fileName;
    }

    /**
     * @param fileResolution is the file resolution.
     */
    public void setFileResolution(final int fileResolution)
    {
        validateFileResolution(fileResolution);

        this.fileResolution = fileResolution;
    }

    /**
     * @param fileName is the file name.
     */
    private static void validateFileName(final String fileName)
    {
        if(fileName == null || fileName.isBlank())
        {
            throw new IllegalArgumentException("File Name cannot be NULL or EMPTY!");
        }
    }

    /**
     * @param fileResolution is the file resolution.
     */
    private static void validateFileResolution(final int fileResolution)
    {
        if(fileResolution < NONE)
        {
            throw new IllegalArgumentException("File Resolution cannot be less than " + NONE + "!");
        }
    }

    /**
     * @param path is the file path.
     */
    private static void validatePath(final String path)
    {
        if(path == null || path.isBlank())
        {
            throw new IllegalArgumentException("Path cannot be NULL or EMPTY!");
        }
    }

    @Override
    public void play()
    {
        System.out.println("\n>> The Audio File \"" + getTitle() + "\" is being played...");
    }

    /**
     * @param path is the file path.
     * @param fileName is the file name.
     */
    @Override
    public void save(final String path, final String fileName)
    {
        validatePath(path);
        validateFileName(fileName);

        System.out.println("\n>> \"" + fileName + "\" is saved!");
    }

    /**
     * @param path is the file path.
     * @param fileName is the file name.
     */
    @Override
    public void delete(final String path, final String fileName)
    {
        validatePath(path);
        validateFileName(fileName);

        System.out.println("\n>> \"" + fileName + "\" is deleted!");
    }

    /**
     * @return the object's data.
     */
    @Override
    public String toString()
    {
        return "AudioFile {"        +
                "fileName='"        + fileName         + '\'' +
                ", fileResolution=" + fileResolution   +
                ", toString()="     + super.toString() +
                '}';
    }
}
