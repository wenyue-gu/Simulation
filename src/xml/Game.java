package xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Simple immutable value object representing simulation data.
 *
 * @author Michelle Tai
 * @author Robert C. Duvall
 */
public class Game {
    // name in data file that will indicate it represents data for this type of object
    public static final String DATA_TYPE = "Simulation";
    // field names expected to appear in data file holding values for this object
    // NOTE: simple way to create an immutable list
    public static final List<String> DATA_FIELDS = List.of(
        "title",
        "author",
        "width",
        "height",
        "initialConfig"
    );

    // specific data values for this instance
    private String myTitle;
    private String myAuthor;
    private int myWidth;
    private int myHeight;
    private String myInitialConfig;
//    myRating;
//    private int myYear;
    // NOTE: keep just as an example for converting toString(), otherwise not used
    private Map<String, String> myDataValues;


    /**
     * Create game data from given data.
     */
    public Game (String title, String author, int width, int height, String initialConfig) {
        myTitle = title;
        myAuthor = author;
        myWidth = width;
        myHeight = height;
        myInitialConfig = initialConfig;
        // NOTE: this is useful so our code does not fail due to a NullPointerException
        myDataValues = new HashMap<>();
    }

    /**
     * Create game data from data structure of Strings.
     *
     * @param dataValues map of field names to their values
     */
    public Game (Map<String, String> dataValues) {
        this(dataValues.get(DATA_FIELDS.get(0)),
            dataValues.get(DATA_FIELDS.get(1)),
            Integer.parseInt(dataValues.get(DATA_FIELDS.get(2))),
            Integer.parseInt(dataValues.get(DATA_FIELDS.get(3))),
            dataValues.get(DATA_FIELDS.get(4)));
        myDataValues = dataValues;
    }

    // NOTE: provides getters, but not setters
    /**
     * Returns title of this simulation.
     */
    public String getTitle () {
        return myTitle;
    }

    /**
     * Returns author of this file.
     */
    public String getAuthor() {
        return myAuthor;
    }

    /**
     * Returns the number of cells in a single row of the simulation grid.
     */
    public int getWidth() {
        return myWidth;
    }

    /**
     * Returns the number of cells in a single columns of the simulation grid.
     */
    public int getHeight() {
        return myHeight;
    }

    /**
     * Returns the intial configurations
     */
    public String getInitialConfig() {
        return myInitialConfig;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append(DATA_TYPE + " = [\n");
        for (Map.Entry<String, String> e : myDataValues.entrySet()) {
            result.append("  ").append(e.getKey()).append(" = '").append(e.getValue()).append("',\n");
        }
        result.append("]\n");
        return result.toString();
    }
}
