package enumerated;


/**
 * An enumerated type that represents all possible ESRB ratings for games:
 *   http://www.esrb.org/ratings/ratings_guide.aspx
 *
 * @author Robert C. Duvall
 */
public enum ESRBRating {
    // these are the ONLY instances that can ever exist
    // NOTE: these are actually anonymous subclasses!
    EARLY_CHILDHOOD("EC", "Early Childhood") {
        @Override
        public boolean isAppropriate (int age) {
            return 0 <= age && age < 5;
        }
    },
    EVERYONE("E", "Everyone") {
        @Override
        public boolean isAppropriate (int age) {
            return true;
        }
    },
    EVERYONE_10_PLUS("E10+", "Everyone 10+") {
        @Override
        public boolean isAppropriate (int age) {
            return age >= 10;
        }
    },
    TEEN("T", "Teen") {
        @Override
        public boolean isAppropriate (int age) {
            return age >= 13;
        }
    },
    MATURE("M", "Mature") {
        @Override
        public boolean isAppropriate (int age) {
            return age >= 17;
        }
    },
    ADULTS_ONLY("AO", "Adults Only") {
        @Override
        public boolean isAppropriate (int age) {
            return age >= 18;
        }
    },
    RATING_PENDING("RP", "Rating Pending") {
        @Override
        public boolean isAppropriate (int age) {
            return age >= 21;
        }
    };

    // immutable instance variables
    private final String myCode;
    private final String myDescription;


    // constructor cannot be called, but private just to emphasize that
    private ESRBRating (String code, String description) {
        myCode = code;
        myDescription = description;
    }

    /**
     * Returns true if this game is appropriate for the given age of the user
     */
    public abstract boolean isAppropriate (int age);

    // NOTE: provides getters, but not setters
    /**
     * Returns ESRB code for this rating
     */
    public String getCode () {
        return myCode;
    }

    /**
     * Returns description for this rating
     */
    public String getDescription () {
        return myDescription;
    }

    /**
     * Returns appropriate rating object for given code string.
     */
    // NOTE: standard (as of Java 9) static factory method to return instance corresponding to given code
    //       but cannot use Java's built-in method valueOf() because cannot have '+' in variable name
    public static ESRBRating of (String code) {
        for (ESRBRating r : ESRBRating.values()) {
            if (r.myCode.equals(code)) {
                return r;
            }
        }
        throw new IllegalArgumentException(String.format("ERROR: %s is not a valid rating code.", code));
    }
}
