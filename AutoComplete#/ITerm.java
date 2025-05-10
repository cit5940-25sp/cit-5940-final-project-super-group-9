/**
 * Defines an interface for a term with a weight and a string representation.
 * This interface can be implemented by classes that represent terms in an autocomplete system,
 * where each term has an associated weight that can be used for sorting or ranking.
 */
package AutoComplete;

/**
 * The ITerm interface provides methods to retrieve the weight and the term string.
 */
public interface ITerm {

    /**
     * Retrieves the weight associated with the term.
     * The weight can be used to rank terms, for example, in an autocomplete list.
     *
     * @return The weight of the term as a long value.
     */
    public long getWeight();

    /**
     * Retrieves the string representation of the term.
     *
     * @return The term as a string.
     */
    public String getTerm();
}
