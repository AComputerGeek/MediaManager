/**
 * This is CompactDisk class. It's a child of MusicMedia class.
 *
 * @author  Amir Armion
 * @version V.01
 */
public class CompactDisk extends MusicMedia
{
    private int numberOfTracks;

    public static final int NONE = 0;

    {
        numberOfTracks = NONE;
    }

    public CompactDisk()
    {

    }

    /**
     * @param sku is the Stock Keeping Unit (an identification code) for this music media.
     * @param title is the title for this music media.
     * @param artist is the artist name for this music media.
     * @param year is the year this music media produced.
     * @param numberOfTracks is the number of tracks on a CD.
     */
    public CompactDisk(final String sku, final String title, final String artist, final int year,
                       final int numberOfTracks)
    {
        super(sku, title, artist, year);

        validateNumberOfTracks(numberOfTracks);

        this.numberOfTracks = numberOfTracks;
    }

    /**
     * @return the number of tracks on a CD.
     */
    public int getNumberOfTracks()
    {
        return numberOfTracks;
    }

    /**
     * @param numberOfTracks is the number of tracks on a CD.
     */
    public void setNumberOfTracks(final int numberOfTracks)
    {
        validateNumberOfTracks(numberOfTracks);

        this.numberOfTracks = numberOfTracks;
    }

    /**
     * @param numberOfTracks is the number of tracks on a CD.
     */
    private static void validateNumberOfTracks(final int numberOfTracks)
    {
        if(numberOfTracks < NONE)
        {
            throw new IllegalArgumentException("Number of tracks cannot be less than " + NONE + "!");
        }
    }

    @Override
    public void play()
    {
        System.out.println("\n>> The CD Song \"" + getTitle() + "\" is being played...");

    }

    /**
     * @return the object's data.
     */
    @Override
    public String toString()
    {
        return "CompactDisk {"    +
                "numberOfTracks=" + numberOfTracks   +
                ", toString()="   + super.toString() +
                '}';
    }
}
