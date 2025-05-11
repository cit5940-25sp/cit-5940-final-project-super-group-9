package AutoComplete;

/**
 * Represents a node in a data structure, likely used for autocomplete functionality.
 * Each node contains a term, word count, prefix count, and references to other nodes.
 */
public class Node {

    // The term associated with this node
    private Term term;
    // The number of words ending at this node
    private int words;
    // The number of prefixes passing through this node
    private int prefixes;
    // An array of references to other nodes, with a size of 128
    private Node[] references;

    /**
     * Initialize a Node with an empty string and 0 weight.
     * This constructor is useful for writing tests as it provides a default state.
     */
    public Node() {
        // Create a new Term with an empty string and weight of 0
        this.term = new Term("", 0);
        // Initialize the word count to 0
        this.words = 0;
        // Initialize the prefix count to 0
        this.prefixes = 0;
        // Create an array of Node references with a size of 128
        this.references = new Node[128];
    }

    /**
     * Retrieves the term associated with this node.
     *
     * @return The term as a Term object.
     */
    public Term getTerm() {
        return term;
    }

    /**
     * Sets the term associated with this node.
     *
     * @param term The new term to be set.
     */
    public void setTerm(Term term) {
        this.term = term;
    }

    /**
     * Retrieves the number of words ending at this node.
     *
     * @return The number of words as an integer.
     */
    public int getWords() {
        return words;
    }

    /**
     * Sets the number of words ending at this node.
     *
     * @param words The new number of words to be set.
     */
    public void setWords(int words) {
        this.words = words;
    }

    public int getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(int prefixes) {
        this.prefixes = prefixes;
    }

    public Node[] getReferences() {
        return references;
    }

    public void setReferences(Node[] references) {
        this.references = references;
    }
}
