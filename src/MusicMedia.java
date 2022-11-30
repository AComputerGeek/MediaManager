/**
 * This is MusicMedia class.
 *
 * @author  Amir Armion
 * @version V.01
 */
public abstract class MusicMedia
{
    private String sku;
    private String title;
    private String artist;
    private int    year;

    public static final int CURRENT_YEAR;
    public static final int FIRST_YEAR;

    public static final int NONE = 0;

    {
        sku    = "Untitled";
        title  = "Untitled";
        artist = "Anonymous";
        year   = NONE;
    }

    static
    {
        CURRENT_YEAR = 2021;
        FIRST_YEAR   = 1860;
    }

    public MusicMedia()
    {

    }

    /**
     * @param sku is the Stock Keeping Unit (an identification code) for this music media.
     * @param title is the title for this music media.
     * @param artist is the artist name for this music media.
     */
    public MusicMedia(final String sku, final String title, final String artist)
    {
        this(sku, title, artist, CURRENT_YEAR);
    }

    /**
     * @param sku is the Stock Keeping Unit (an identification code) for this music media.
     * @param title is the title for this music media.
     * @param artist is the artist name for this music media.
     * @param year is the year this music media produced.
     */
    public MusicMedia(final String sku, final String title, final String artist, final int year)
    {
        validateSku(sku);
        validateTitle(title);
        validateArtist(artist);
        validateYear(year);

        this.sku    = sku;
        this.title  = title;
        this.artist = artist;
        this.year   = year;
    }

    /**
     * @return the sku (Stock Keeping Unit).
     */
    public String getSku()
    {
        return sku;
    }

    /**
     * @return the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @return the artist name.
     */
    public String getArtist()
    {
        return artist;
    }

    /**
     * @return the year this music media produced.
     */
    public int getYear()
    {
        return year;
    }

    /**
     * @param sku is the Stock Keeping Unit (an identification code).
     */
    public void setSku(final String sku)
    {
        validateSku(sku);

        this.sku = sku;
    }

    /**
     * @param title is the title.
     */
    public void setTitle(final String title)
    {
        validateTitle(title);

        this.title = title;
    }

    /**
     * @param artist is the artist name.
     */
    public void setArtist(final String artist)
    {
        validateArtist(artist);

        this.artist = artist;
    }

    /**
     * @param year is the year this music media produced.
     */
    public void setYear(final int year)
    {
        validateYear(year);

        this.year = year;
    }

    /**
     * @param sku is the Stock Keeping Unit (an identification code).
     */
    private static void validateSku(final String sku)
    {
        if(sku == null || sku.isBlank())
        {
            throw new IllegalArgumentException("SKU cannot be NULL or EMPTY!");
        }
    }

    /**
     * @param title is the title.
     */
    private static void validateTitle(final String title)
    {
        if(title == null || title.isBlank())
        {
            throw new IllegalArgumentException("Title cannot be NULL or EMPTY!");
        }
    }

    /**
     * @param artist is the artist name.
     */
    private static void validateArtist(final String artist)
    {
        if(artist == null || artist.isBlank())
        {
            throw new IllegalArgumentException("Artist cannot be NULL or EMPTY!");
        }
    }

    /**
     * @param year is the year this music media produced.
     */
    private static void validateYear(final int year)
    {
        if(year < FIRST_YEAR || year > CURRENT_YEAR)
        {
            throw new IllegalArgumentException("Year cannot be less than " + FIRST_YEAR + " or greater than " +
                                                CURRENT_YEAR + "!");
        }
    }

    public abstract void play();

    /**
     * @return the object's data.
     */
    @Override
    public String toString()
    {
        return "MusicMedia {" +
                "sku='"       + sku    + '\'' +
                ", title='"   + title  + '\'' +
                ", artist='"  + artist + '\'' +
                ", year="     + year   +
                '}';
    }
}
