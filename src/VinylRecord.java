/**
 * This is VinylRecord class. It's a child of MusicMedia class.
 *
 * @author  Amir Armion
 * @version V.01
 */
public class VinylRecord extends MusicMedia
{
    private int numberOfTracks;
    private int sizeInInches;
    private int weightInGrams;

    public static final int FIRST_VALID_SIZE_INCHES;               // is 7
    public static final int SECOND_VALID_SIZE_INCHES;              // is 10
    public static final int THIRD_VALID_SIZE_INCHES;               // is 12

    public static final int VALID_WEIGHT_FOR_SEVEN_INCHES;         // is 40

    public static final int VALID_WEIGHT_FOR_TEN_INCHES;           // is 100

    public static final int FIRST_VALID_WEIGHT_FOR_TWELVE_INCHES;  // is 140
    public static final int SECOND_VALID_WEIGHT_FOR_TWELVE_INCHES; // is 180
    public static final int THIRD_VALID_WEIGHT_FOR_TWELVE_INCHES;  // is 200

    public static final int STANDARD_WEIGHT = 140;
    public static final int DEFAULT_SIZE    = 12;

    public static final int NONE            = 0;

    {
        numberOfTracks = NONE;
        weightInGrams  = STANDARD_WEIGHT;
        sizeInInches   = DEFAULT_SIZE;
    }

    static
    {
        FIRST_VALID_SIZE_INCHES               = 7;
        SECOND_VALID_SIZE_INCHES              = 10;
        THIRD_VALID_SIZE_INCHES               = 12;

        VALID_WEIGHT_FOR_SEVEN_INCHES         = 40;  // for 7"

        VALID_WEIGHT_FOR_TEN_INCHES           = 100; // for 10"

        FIRST_VALID_WEIGHT_FOR_TWELVE_INCHES  = 140; // for 12"
        SECOND_VALID_WEIGHT_FOR_TWELVE_INCHES = 180; // for 12"
        THIRD_VALID_WEIGHT_FOR_TWELVE_INCHES  = 200; // for 12"
    }

    public VinylRecord()
    {

    }

    /**
     * @param sku is the Stock Keeping Unit (an identification code) for this music media.
     * @param title is the title for this music media.
     * @param artist is the artist name for this music media.
     * @param year is the year this music media produced.
     * @param numberOfTracks is the number of tracks.
     */
    public VinylRecord(final String sku, final String title, final String artist, final int year,
                       final int numberOfTracks)
    {
        this(sku, title, artist, year, numberOfTracks, DEFAULT_SIZE, STANDARD_WEIGHT);
    }

    /**
     * @param sku is the Stock Keeping Unit (an identification code) for this music media.
     * @param title is the title for this music media.
     * @param artist is the artist name for this music media.
     * @param year is the year this music media produced.
     * @param numberOfTracks is the number of tracks.
     * @param sizeInInches is the size in inches.
     * @param weightInGrams is the weight in grams.
     */
    public VinylRecord(final String sku, final String title, final String artist, final int year,
                       final int numberOfTracks, final int sizeInInches, final int weightInGrams)
    {
        super(sku, title, artist, year);

        validateNumberOfTracks(numberOfTracks);
        validateSizeInInches(sizeInInches);
        validateWeightInGrams(weightInGrams);
        validateSizeAndWeight(sizeInInches,weightInGrams);

        this.numberOfTracks = numberOfTracks;
        this.sizeInInches   = sizeInInches;
        this.weightInGrams  = weightInGrams;
    }

    /**
     * @return the number of tracks.
     */
    public int getNumberOfTracks()
    {
        return numberOfTracks;
    }

    /**
     * @return the size in inches.
     */
    public int getSizeInInches()
    {
        return sizeInInches;
    }

    /**
     * @return the weight in grams.
     */
    public int getWeightInGrams()
    {
        return weightInGrams;
    }

    /**
     * @param numberOfTracks is the number of tracks.
     */
    public void setNumberOfTracks(final int numberOfTracks)
    {
        validateNumberOfTracks(numberOfTracks);

        this.numberOfTracks = numberOfTracks;
    }

    /**
     * @param sizeInInches is the size in inches.
     */
    public void setSizeInInches(final int sizeInInches)
    {
        validateSizeInInches(sizeInInches);

        this.sizeInInches = sizeInInches;
    }

    /**
     * @param weightInGrams is the weight in grams.
     */
    public void setWeightInGrams(final int weightInGrams)
    {
        validateWeightInGrams(weightInGrams);
        validateSizeAndWeight(sizeInInches, weightInGrams);

        this.weightInGrams = weightInGrams;
    }

    /**
     * @param numberOfTracks is the number of tracks.
     */
    private static void validateNumberOfTracks(final int numberOfTracks)
    {
        if(numberOfTracks < NONE)
        {
            throw new IllegalArgumentException("Number of tracks cannot be less than " + NONE);
        }
    }

    /**
     * @param sizeInInches is the size in inches.
     */
    private static void validateSizeInInches(final int sizeInInches)
    {
        if(sizeInInches != FIRST_VALID_SIZE_INCHES  &&
           sizeInInches != SECOND_VALID_SIZE_INCHES &&
           sizeInInches != THIRD_VALID_SIZE_INCHES)
        {
            throw new IllegalArgumentException("Size in inches must be either "     +
                                                FIRST_VALID_SIZE_INCHES  + "\", "   +
                                                SECOND_VALID_SIZE_INCHES + "\" or " +
                                                THIRD_VALID_SIZE_INCHES  + "\"!");
        }
    }

    /**
     * @param weightInGrams is the weight in grams.
     */
    private static void validateWeightInGrams(final int weightInGrams)
    {
        if(weightInGrams != VALID_WEIGHT_FOR_SEVEN_INCHES         &&
           weightInGrams != VALID_WEIGHT_FOR_TEN_INCHES           &&
           weightInGrams != FIRST_VALID_WEIGHT_FOR_TWELVE_INCHES  &&
           weightInGrams != SECOND_VALID_WEIGHT_FOR_TWELVE_INCHES &&
           weightInGrams != THIRD_VALID_WEIGHT_FOR_TWELVE_INCHES)
        {
            throw new IllegalArgumentException("Valid weight must be either " +
                                                VALID_WEIGHT_FOR_SEVEN_INCHES + "g, " +
                                                VALID_WEIGHT_FOR_TEN_INCHES           + "g, "    +
                                                FIRST_VALID_WEIGHT_FOR_TWELVE_INCHES  + "g, "    +
                                                SECOND_VALID_WEIGHT_FOR_TWELVE_INCHES + "g, or " +
                                                THIRD_VALID_WEIGHT_FOR_TWELVE_INCHES  + "g");
        }
    }

    /**
     * @param sizeInInches is the size in inches.
     * @param weightInGrams is the weight in grams.
     */
    private static void validateSizeAndWeight(final int sizeInInches, final int weightInGrams)
    {
        if(sizeInInches == FIRST_VALID_SIZE_INCHES) // size is 7"
        {
            if(weightInGrams != VALID_WEIGHT_FOR_SEVEN_INCHES) // weight is not 40g
            {
                throw new IllegalArgumentException("For size " + FIRST_VALID_SIZE_INCHES + "\", valid weight must be " +
                                                    VALID_WEIGHT_FOR_SEVEN_INCHES + "g");
            }
        }
        else if(sizeInInches == SECOND_VALID_SIZE_INCHES) // size is 10"
        {
            if(weightInGrams != VALID_WEIGHT_FOR_TEN_INCHES) // weight is not 100g
            {
                throw new IllegalArgumentException("For size " + SECOND_VALID_SIZE_INCHES + "\", valid weight must be " +
                                                    VALID_WEIGHT_FOR_TEN_INCHES + "g");
            }
        }
        else if(sizeInInches == THIRD_VALID_SIZE_INCHES) // size is 12"
        {
            if((weightInGrams != FIRST_VALID_WEIGHT_FOR_TWELVE_INCHES)  &&
               (weightInGrams != SECOND_VALID_WEIGHT_FOR_TWELVE_INCHES) &&
               (weightInGrams != THIRD_VALID_WEIGHT_FOR_TWELVE_INCHES)) // weight is not 140g, 100g, or 200g
            {
                throw new IllegalArgumentException("For size " + THIRD_VALID_SIZE_INCHES  + "\", valid weight must be either " +
                                                    FIRST_VALID_WEIGHT_FOR_TWELVE_INCHES  + "g, "    +
                                                    SECOND_VALID_WEIGHT_FOR_TWELVE_INCHES + "g, or " +
                                                    THIRD_VALID_WEIGHT_FOR_TWELVE_INCHES  + "g");
            }
        }
    }

    @Override
    public void play()
    {
        System.out.println("\n>> The Record \"" + getTitle() + "\" is being played...");
    }

    /**
     * @return the object's data.
     */
    @Override
    public String toString()
    {
        return "VinylRecord {"     +
                "numberOfTracks="  + numberOfTracks   +
                ", sizeInInches="  + sizeInInches     +
                ", weightInGrams=" + weightInGrams    +
                ", toString()="    + super.toString() +
                '}';
    }
}
