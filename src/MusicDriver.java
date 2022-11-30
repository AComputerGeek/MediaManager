import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.UnknownServiceException;
import java.util.*;

/**
 * This is MusicDriver class. It's our driver.
 *
 * @author  Amir Armion
 * @version V.01
 */
public class MusicDriver
{
    private MusicLibrary    library;

    public static final int SKU;
    public static final int TITLE;
    public static final int ARTIST;
    public static final int YEAR;

    public static final int FILE_NAME;
    public static final int FILE_RESOLUTION;

    public static final int NUMBER_TRACKS;
    public static final int WEIGHT;
    public static final int SIZE;

    public static final int DISPLAY_AUDIO;
    public static final int DISPLAY_COMPACT_DISK;
    public static final int DISPLAY_VINYL_RECORD;
    public static final int DISPLAY_SORT;
    public static final int DISPLAY_SEARCH;

    public static final int SORT_SKU;
    public static final int SORT_TYPE;
    public static final int SORT_ARTIST;
    public static final int SORT_YEAR;

    public static final int SEARCH_TITLE;
    public static final int SEARCH_ARTIST;
    public static final int SEARCH_YEAR;
    public static final int SEARCH_TYPE;

    public static final int MAIN_MENU;
    public static final int EXIT;

    public static final int PREFIX;
    public static final int NONE;

    static
    {
        SKU                  = 0;
        TITLE                = 1;
        ARTIST               = 2;
        YEAR                 = 3;

        FILE_NAME            = 4;
        FILE_RESOLUTION      = 5;

        NUMBER_TRACKS        = 4;
        WEIGHT               = 5;
        SIZE                 = 6;

        DISPLAY_AUDIO        = 1;
        DISPLAY_COMPACT_DISK = 2;
        DISPLAY_VINYL_RECORD = 3;
        DISPLAY_SORT         = 4;
        DISPLAY_SEARCH       = 5;

        SORT_SKU             = 1;
        SORT_TYPE            = 2;
        SORT_ARTIST          = 3;
        SORT_YEAR            = 4;

        SEARCH_TITLE         = 1;
        SEARCH_ARTIST        = 2;
        SEARCH_YEAR          = 3;
        SEARCH_TYPE          = 4;

        MAIN_MENU            = 5;
        EXIT                 = 6;

        PREFIX               = 2;
        NONE                 = 0;
    }

    /**
     * @param args unused.
     * @throws FileNotFoundException if file didn't find.
     */
    public static void main(final String[] args) throws FileNotFoundException
    {
        MusicDriver md = new MusicDriver();

        try
        {
            md.init();
            md.displayMenu();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("\n>> Error! " + e.getMessage());
        }
    }

    /**
     * @throws FileNotFoundException if file didn't find.
     */
    public void init() throws FileNotFoundException
    {
        ArrayList<MusicMedia> medias;
        medias = new ArrayList<>();

        Reader  reader  = new FileReader("music_data.txt");
        Scanner scanner = new Scanner(reader);

        while(scanner.hasNextLine())
        {
            String line   = scanner.nextLine();
            String[] data = line.split("\\|");

            String sku    = data[SKU];
            String title  = data[TITLE];
            String artist = data[ARTIST];
            int    year   = Integer.parseInt(data[YEAR]);

            String prefix = sku.substring(NONE, PREFIX);

            if(prefix.equalsIgnoreCase("af")) // AudioFile
            {
                String fileName       = data[FILE_NAME];
                int    fileResolution = Integer.parseInt(data[FILE_RESOLUTION]);

                MusicMedia media = new AudioFile(sku, title, artist, year, fileName, fileResolution);
                medias.add(media);
            }
            else if(prefix.equalsIgnoreCase("cd")) // CompactDisk
            {
                int numberOfTracks = Integer.parseInt(data[NUMBER_TRACKS]);

                MusicMedia media = new CompactDisk(sku, title, artist, year, numberOfTracks);
                medias.add(media);
            }
            else if(prefix.equalsIgnoreCase("vr")) // VinylRecord
            {
                int numberOfTracks = Integer.parseInt(data[NUMBER_TRACKS]);
                int weightInGrams  = Integer.parseInt(data[WEIGHT]);
                int sizeInInches   = Integer.parseInt(data[SIZE]);

                MusicMedia media = new VinylRecord(sku, title, artist, year, numberOfTracks, sizeInInches, weightInGrams);
                medias.add(media);
            }
        }

        MusicLibrary ml = new MusicLibrary();

        for(MusicMedia media: medias)
        {
            ml.addMusic(media);
        }

        System.out.println("\n>> All Music Media: \n");
        ml.displayLibrary();

        library = ml;
    }

    /**
     * This is display method which is displaying the menu.
     */
    public void displayMenu()
    {
        int     userInput;
        String  userIn;
        String  prefix;
        boolean keepContinueMainMenu   = true;
        boolean keepContinueSortMenu   = true;
        boolean keepContinueSearchMenu = true;

        System.out.println(">> Welcome to our Music Media search <<");

        while(keepContinueMainMenu)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n>> Please choose one of following options: \n1. Display Audio Files"    +
                                                                            "\n2. Display Compact Discs"  +
                                                                            "\n3. Display Vinyl Records"  +
                                                                            "\n4. Sort Options"           +
                                                                            "\n5. Search Options"         +
                                                                            "\n6. Exit");
            if(scanner.hasNextInt())
            {
                userInput = scanner.nextInt();

                if(userInput == DISPLAY_AUDIO) // 1. Display Audio Files
                {
                    prefix = "af";
                    library.displayChoice(prefix);
                }
                else if(userInput == DISPLAY_COMPACT_DISK) // 2. Display Compact Discs
                {
                    prefix = "cd";
                    library.displayChoice(prefix);
                }
                else if(userInput == DISPLAY_VINYL_RECORD) // 3. Display Vinyl Records
                {
                    prefix = "vr";
                    library.displayChoice(prefix);
                }
                else if(userInput == DISPLAY_SORT) // 4. Sort Options
                {
                    while(keepContinueSortMenu)
                    {
                        Scanner scannerSort = new Scanner(System.in);
                        System.out.println("\n>> Sort Menu: \n1. By SKU"    +
                                                           "\n2. By Type"   +
                                                           "\n3. By Artist" +
                                                           "\n4. By Year"   +
                                                           "\n5. Main Menu");
                        if(scannerSort.hasNextInt())
                        {
                            userInput = scannerSort.nextInt();

                            if(userInput == SORT_SKU) // 1. Sort by SKU
                            {
                                library.displaySorted(SORT_SKU);
                            }
                            else if(userInput == SORT_TYPE) // 2. Sort by Type
                            {
                                library.displaySorted(SORT_TYPE);
                            }
                            else if(userInput == SORT_ARTIST) // 3. Sort by Artist
                            {
                                library.displaySorted(SORT_ARTIST);
                            }
                            else if(userInput == SORT_YEAR) // 4. Sort by Year
                            {
                                library.displaySorted(SORT_YEAR);
                            }
                            else if(userInput == MAIN_MENU) // 5. Main Menu
                            {
                                break;
                            }
                            else // Wrong input
                            {
                                System.out.println("\n>> Sorry! Wrong number! Please try again...");
                            }
                        }
                        else // Wrong type of input (like String or double etc.)
                        {
                            System.out.println("\n>> Sorry! You should enter a number from the sort menu. Please try again...");
                        }
                    }
                }
                else if(userInput == DISPLAY_SEARCH) // 5. Search Options
                {
                    while(keepContinueSearchMenu)
                    {
                        Scanner scannerSearch = new Scanner(System.in);
                        System.out.println("\n>> Search Menu: \n1. Search Title"  +
                                                             "\n2. Search Artist" +
                                                             "\n3. Search Year"   +
                                                             "\n4. Search Type"   +
                                                             "\n5. Main Menu");
                        if(scannerSearch.hasNextInt())
                        {
                            userInput = scannerSearch.nextInt();

                            if(userInput == SEARCH_TITLE) // 1. Search Title
                            {
                                Scanner scan = new Scanner(System.in);
                                System.out.println("\n>> Please enter the title: ");
                                userIn = scan.nextLine();

                                library.displaySearch(SEARCH_TITLE, userIn);
                            }
                            else if(userInput == SEARCH_ARTIST) // 2. Search Artist
                            {
                                Scanner scan = new Scanner(System.in);
                                System.out.println("\n>> Please enter the Artist: ");
                                userIn = scan.nextLine();

                                library.displaySearch(SEARCH_ARTIST, userIn);
                            }
                            else if(userInput == SEARCH_YEAR) // 3. Search Year
                            {
                                Scanner scan = new Scanner(System.in);
                                System.out.println("\n>> Please enter the Year: ");
                                userIn = scan.nextLine();

                                library.displaySearch(SEARCH_YEAR, userIn);
                            }
                            else if(userInput == SEARCH_TYPE) // 4. Search Type
                            {
                                Scanner scan = new Scanner(System.in);
                                System.out.println("\n>> Please enter the Type: ");
                                userIn = scan.nextLine();

                                library.displaySearch(SEARCH_TYPE, userIn);
                            }
                            else if(userInput == MAIN_MENU) // 5. Main Menu
                            {
                                break;
                            }
                            else // Wrong input
                            {
                                System.out.println("\n>> Sorry! Wrong number! Please try again...");
                            }
                        }
                        else // Wrong type of input (like String or double etc.)
                        {
                            System.out.println("\n>> Sorry! You should enter a number from the sort menu. Please try again...");
                        }
                    }
                }
                else if(userInput == EXIT) // 6. Exit
                {
                    System.out.println("\nGoodbye for now! :)");

                    break;
                }
                else // Wrong input
                {
                    System.out.println("\n>> Sorry! Wrong number! Please try again...");
                }
            }
            else // Wrong type of input (like String or double etc.)
            {
                System.out.println("\n>> Sorry! You should enter a number from the menu's options. Please try again...");
            }
        }
    }
}
