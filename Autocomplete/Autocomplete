package AutoComplete;
import java.util.ArrayList;
import java.util.List;

public class Autocomplete implements IAutocomplete {
    private Node root;
    private int k;

    /**
     * Initializes an empty Autocomplete object.
     */
    public Autocomplete(int k) {
        root = new Node();
        this.k = k;
    }

    /**
     * Checks if a given string consists only of lowercase letters.
     * If the string is null, it returns false.
     *
     * @param str the string to be checked
     * @return true if the string is not null and contains
     * only lowercase letters, false otherwise
     */
    private boolean isValidString(String str) {
        // Check if the string is null
        if (str == null) {
            return false;
        }
        // Iterate through each character in the string
        for (int i = 0; i < str.length(); i++) {
            // Check if the character is not a lowercase letter
            char c = str.charAt(i);
            if ( c < 0 || c > 128) {
                return false;
            }
        }
        // If all characters are lowercase letters, return true
        return true;
    }

    /**
     * Calculates the index of a given character in the references array of a Node.
     * The index is based on the position of the character in the alphabet
     *
     * @param c the character for which to calculate the index
     * @return the index of the character in the references array
     */
    private int getIndex(char c) {
        return c;
    }

    /**
     * Adds a word and its associated weight to the Trie data structure.
     * If the word is invalid (not all lowercase) or the weight is negative,
     * the method returns without adding the word.
     *
     * @param word   the word to be added to the Trie
     * @param weight the weight associated with the word
     */
    @Override
    public void addWord(String word, long weight) {
        // Convert the word to lowercase
        word = word.toLowerCase();
        // Check if the word is valid and the weight is non-negative
        if (!isValidString(word) || weight < 0) {
            return;
        }
        // Start from the root of the Trie
        Node current = root;
        // Iterate through each character in the word
        for (char c : word.toCharArray()) {
            // Get the index of the character in the references array
            int index = getIndex(c);
            // If the node at the index does not exist, create a new node
            if (current.getReferences()[index] == null) {
                current.getReferences()[index] = new Node();
            }
            // Increment the prefix count of the current node
            current.setPrefixes(current.getPrefixes() + 1);
            // Move to the next node in the Trie
            current = current.getReferences()[index];
        }
        // Set the term for the current node
        current.setTerm(new Term(word, weight));
        // Increment the prefix count of the current node
        current.setPrefixes(current.getPrefixes() + 1);
        // Increment the word count of the current node
        current.setWords(current.getWords() + 1);
    }

    /**
     * Retrieves the subtrie rooted at the node corresponding to the given prefix.
     * If the prefix is invalid (not all lowercase), returns null.
     * Traverses the Trie using the characters of the prefix.
     * If any character in the prefix is not found in the Trie, returns null.
     * Otherwise, returns the node corresponding to the last character of the prefix.
     *
     * @param prefix the prefix for which to retrieve the subtrie
     * @return the root node of the subtrie corresponding to the prefix,
     * or null if the prefix is invalid or not found
     */
    @Override
    public Node getSubTrie(String prefix) {
        // Convert the prefix to lowercase
        prefix = prefix.toLowerCase();
        // Check if the prefix is valid
        if (!isValidString(prefix)) {
            return null;
        }
        // Start from the root of the Trie
        Node current = root;
        // Iterate through each character in the prefix
        for (char c : prefix.toCharArray()) {
            // Get the index of the character in the references array
            int index = getIndex(c);
            // If the node at the index does not exist, return null
            if (current.getReferences()[index] == null) {
                return null;
            }
            // Move to the next node in the Trie
            current = current.getReferences()[index];
        }
        // Return the node corresponding to the last character of the prefix
        return current;
    }

    /**
     * Counts the number of words in the Trie that start with the given prefix.
     * This method first retrieves the subtrie corresponding to the prefix.
     * If the subtrie exists, it returns the prefix count stored at the last
     * node of the prefix.
     * If the subtrie does not exist, it returns 0.
     *
     * @param prefix the prefix for which to count the number of words starting with it
     * @return the number of words in the Trie that start with the given prefix,
     * or 0 if the prefix is not found
     */
    @Override
    public int countPrefixes(String prefix) {
        // Get the subtrie corresponding to the given prefix
        Node current = getSubTrie(prefix);
        // If the subtrie does not exist, return 0
        if (current == null) {
            return 0;
        }
        // Otherwise, return the prefix count stored at the last node of the prefix
        return current.getPrefixes();
    }

    /**
     * A helper method to recursively add all possible suggestions
     * from the given node and its descendants to the suggestions list.
     *
     * @param current     the current node in the Trie to start adding suggestions from
     * @param suggestions the list to which suggestions will be added
     */
    public boolean addSuggestionsHelper(Node current, List<ITerm> suggestions) {
        // If the current node is null, return immediately
        if (current == null) {
            return true;
        }
        // If the current node represents a word (i.e., its word count is greater than 0)
        if (current.getWords() > 0) {
            // Create a copy of the term at the current node
            Term copyTerm = new Term(current.getTerm().getTerm(),
                    current.getTerm().getWeight());
            // Add the copied term to the suggestions list
            suggestions.add(copyTerm);
            if(suggestions.size() == k){
                return false;
            }
        }
        // Get the array of child nodes of the current node
        Node[] node = current.getReferences();
        // If the array of child nodes is not null
        if (node != null) {
            // Iterate through each child node in the array
            for (int i = 0; i < node.length; i++) {
                // If the child node at the current index is not null
                if (node[i] != null) {
                    // Recursively call this method on the child node
                    if(addSuggestionsHelper(node[i], suggestions) == false){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Retrieves a list of suggestions based on the given prefix.
     * If the prefix is invalid or no subtrie is found for the prefix,
     * an empty list is returned.
     * Otherwise, it traverses the subtrie to collect all possible suggestions.
     *
     * @param prefix the prefix for which to retrieve suggestions
     * @return a list of suggestions based on the given prefix
     */
    @Override
    public List<ITerm> getSuggestions(String prefix) {
        // Initialize an empty list to store suggestions
        List<ITerm> suggestions = new ArrayList<>();
        // Convert the prefix to lowercase
        prefix = prefix.toLowerCase();
        Node current = getSubTrie(prefix);
        // Check if the prefix is invalid or no subtrie is found
        if (!isValidString(prefix) || current == null) {
            return suggestions;
        }
        // Call the helper method to add suggestions from the subtrie
        addSuggestionsHelper(current, suggestions);
        return suggestions;
    }

}
