package AutoComplete;
import java.util.Comparator;

/**
 * @author ericfouh
 */
public interface ITerm
        extends Comparable<ITerm> {



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