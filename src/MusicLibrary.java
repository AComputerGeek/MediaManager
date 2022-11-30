import java.util.*;
import java.util.stream.Collectors;

/**
 * This is MusicLibrary class.
 *
 * @author  Amir Armion
 * @version V.01
 */
public class MusicLibrary
{
    private Map<String, MusicMedia> library;

    public static final int SORT_SKU;
    public static final int SORT_TYPE;
    public static final int SORT_ARTIST;
    public static final int SORT_YEAR;

    public static final int SEARCH_TITLE;
    public static final int SEARCH_ARTIST;
    public static final int SEARCH_YEAR;
    public static final int SEARCH_TYPE;

    public static final int PREFIX;
    public static final int NONE;

    static
    {
        SORT_SKU      = 1;
        SORT_TYPE     = 2;
        SORT_ARTIST   = 3;
        SORT_YEAR     = 4;

        SEARCH_TITLE  = 1;
        SEARCH_ARTIST = 2;
        SEARCH_YEAR   = 3;
        SEARCH_TYPE   = 4;

        PREFIX        = 2;
        NONE          = 0;
    }

    public MusicLibrary()
    {
        library = new HashMap<>();
    }

    /**
     * @param selection is a collection of MusicMedia.
     */
    public void addMusic(MusicMedia selection)
    {
        library.put(selection.getSku(), selection);
    }

    /**
     * This method displays all of music media from the library collection.
     */
    public void displayLibrary()
    {
        Set<String> keys;
        keys = library.keySet();

        for(String key : keys)
        {
            System.out.println(library.get(key));
            System.out.println();
        }

        System.out.println("---------------------------------------\n");
    }

    /**
     * @param prefix indicates this music media is AudioFile (af) or CompactDisk (cd) or VinylRecord (vr).
     */
    public void displayChoice(final String prefix)
    {
        Set<String> keys;
        keys = library.keySet();

        for(String key : keys)
        {
            String sku = key;
            String pre = sku.substring(NONE, PREFIX);

            if(pre.equalsIgnoreCase(prefix))
            {
                System.out.println(library.get(key));
                System.out.println();
            }
        }
    }

    /**
     * @param num is the number which is indicates sorting should be based on SKU, Type, Artist, or Year.
     */
    public void displaySorted(final int num)
    {
        Collection<MusicMedia> valuesCollection = library.values();
        List<MusicMedia>       valuesList       = new ArrayList<>(valuesCollection);

        if(num == SORT_SKU) // Sort by SKU
        {
            valuesList.stream()
                    .sorted(Comparator.comparing(MusicMedia::getSku))
                    .forEach(p -> System.out.println("\n" + p));
        }
        else if(num == SORT_TYPE) // Sort by Type
        {
            valuesList.stream()
                    .sorted(Comparator.comparing(p -> p.getSku().substring(0,2)))
                    .forEach(p -> System.out.println("\n" + p));
        }
        else if(num == SORT_ARTIST) // Sort by Artist
        {
            valuesList.stream()
                    .sorted(Comparator.comparing(MusicMedia::getArtist))
                    .forEach(p -> System.out.println("\n" + p));
        }
        else if(num == SORT_YEAR) // Sort by Year
        {
            valuesList.stream()
                    .sorted(Comparator.comparing(MusicMedia::getYear))
                    .forEach(p -> System.out.println("\n" + p));

        }
    }

    /**
     * @param num is the number which is indicates searching should be based on Title, Artist, Year, or Type.
     * @param userIn is a string we want to search based on.
     */
    public void displaySearch(final int num, final String userIn)
    {
        Collection<MusicMedia> valuesCollection = library.values();
        List<MusicMedia>       valuesList       = new ArrayList<>(valuesCollection);

        if(num == SEARCH_TITLE) // Search by Title
        {
            List<MusicMedia> medias = valuesList.stream()
                    .filter(p -> p.getTitle().equalsIgnoreCase(userIn.trim()))
                    .collect(Collectors.toList());

            if(!medias.isEmpty())
            {
                medias.forEach(p -> System.out.println("\n" + p));
            }
            else
            {
                System.out.println("\n>> Sorry! The title \"" + userIn.trim() + "\" is not in the music library!");
            }
        }
        else if(num == SEARCH_ARTIST) // Search by Artist
        {
            List<MusicMedia> medias = valuesList.stream()
                    .filter(p -> p.getArtist().equalsIgnoreCase(userIn.trim()))
                    .collect(Collectors.toList());

            if(!medias.isEmpty())
            {
                medias.forEach(p -> System.out.println("\n" + p));
            }
            else
            {
                System.out.println("\n>> Sorry! The Artist \"" + userIn.trim() + "\" is not in the music library!");
            }
        }
        else if(num == SEARCH_YEAR) // Search by Year
        {
            List<MusicMedia> medias = valuesList.stream()
                    .filter(p -> String.valueOf(p.getYear()).equals(userIn.trim()))
                    .collect(Collectors.toList());

            if(!medias.isEmpty())
            {
                medias.forEach(p -> System.out.println("\n" + p));
            }
            else if(!userIn.matches("[0-9]+"))
            {
                System.out.println("\n>> Ops! This \"" + userIn.trim() + "\" is not numeric! Please try again...");
            }
            else
            {
                System.out.println("\n>> Sorry! The Year \"" + userIn.trim() + "\" is not in the music library!");
            }
        }
        else if(num == SEARCH_TYPE) //Search by Type
        {
            if(userIn.trim().equalsIgnoreCase("af") ||
               userIn.trim().equalsIgnoreCase("cd") ||
               userIn.trim().equalsIgnoreCase("vr"))
            {
                List<MusicMedia> medias = valuesList.stream()
                        .filter(p -> p.getSku().substring(NONE, PREFIX).equalsIgnoreCase(userIn.trim()))
                        .collect(Collectors.toList());

                if(!medias.isEmpty())
                {
                    medias.forEach(p -> System.out.println("\n" + p));
                }
                else
                {
                    System.out.println("\n>> Sorry! The type \"" + userIn.trim() + "\" is not in the music library!");
                }
            }
            else
            {
                System.out.println("\n>> Sorry! The Type \"" + userIn.trim() + "\" is not a valid type! Type should be \"af\", \"cd\", or \"vr\". Please try again...");
            }
        }
    }
}
