package AutoComplete;
import java.util.Comparator;

/**
 * @author ericfouh
 */
public interface ITerm
        extends Comparable<ITerm> {

    /**
     * Compares the two terms in descending order by weight.
     *
     * @return comparator Object
     */
    public static Comparator<ITerm> byReverseWeightOrder() {
        return Comparator.comparingLong(ITerm::getWeight).reversed();
    }

    /**
     * Returns a substring of the given string with the specified length.
     * If the given string is null, null is returned.
     * If the specified length is greater than or equal to the length of the string,
     * the original string is returned.
     * If the specified length is less than or equal to 0, an empty string is returned.
     *
     * @param str    the string to extract a substring from
     * @param length the length of the substring to extract
     * @return the substring of the given string with the specified length
     */
    static String getSubstring(String str, int length) {
        // Check if the input string is null, return null if true
        if (str == null) {
            return null;
        }
        // Get the length of the input string
        int strLength = str.length();
        if (length >= strLength) {
            return str;
        }
        // If the specified length is less than or equal to 0, return an empty string
        if (length <= 0) {
            return "";
        }
        // Return the substring from the start of the string to the specified length
        return str.substring(0, length);
    }

    /**
     * Compares the two terms in lexicographic order but using only the first r
     * characters of each query.
     *
     * @param r
     * @return comparator Object
     */
    public static Comparator<ITerm> byPrefixOrder(int r) {
        if (r < 0) {
            throw new IllegalArgumentException("r is invalid");
        }
        return (term1, term2) -> {
            String subString1 = getSubstring(term1.getTerm(), r);
            String subString2 = getSubstring(term2.getTerm(), r);
            return subString1.compareTo(subString2);
        };
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(ITerm that);


    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString();

    // Required getters.
    public long getWeight();

    public String getTerm();

    // Required setters (mostly for autograding purposes)
    public void setWeight(long weight);

    public String setTerm(String term);

}
