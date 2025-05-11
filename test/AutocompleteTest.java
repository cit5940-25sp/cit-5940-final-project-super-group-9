
import AutoComplete.Autocomplete;
import AutoComplete.Node;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;

/**
 * This class contains JUnit test cases for the {@link Autocomplete} class.
 * It verifies the functionality of adding words to the autocomplete trie and
 * building the trie to generate suggestions.
 */
public class AutocompleteTest {

    /**
     * Test the {@code addWord} method of the {@link Autocomplete} class.
     * This method adds words to the autocomplete trie and verifies that the words
     * are correctly inserted and the prefix count is accurate.
     */
    @Test
    public void testAddWord() {
        // Create a new Autocomplete instance with a maximum capacity of 5
        Autocomplete autocomplete = new Autocomplete(5);

        // Define the first word and its weight
        String word = "123";
        long weight = 123;
        // Add the first word to the autocomplete trie
        autocomplete.addWord(word, weight);
        // Get the subtrie for the first word
        Node node = autocomplete.getSubTrie(word);
        // Verify that the subtrie for the first word is not null
        assertNotNull(node);

        // Define the second word and its weight
        word = "abc";
        weight = 123;
        // Add the second word to the autocomplete trie
        autocomplete.addWord(word, weight);
        // Get the subtrie for the second word
        node = autocomplete.getSubTrie(word);
        // Verify that the subtrie for the second word is not null
        assertNotNull(node);
        // Verify that the weight of the term in the node matches the expected weight
        assertEquals(weight, node.getTerm().getWeight());

        // Add additional words starting with "abc"
        autocomplete.addWord("abcd", 101);
        autocomplete.addWord("abcde", 101);

        // Count the number of words with the prefix "abc"
        int count = autocomplete.countPrefixes(word);
        // Verify that the count of words with the prefix "abc" is 3
        assertEquals(3, count);

        // Count the number of words with the prefix "abcde"
        count = autocomplete.countPrefixes("abcde");
        // Verify that the count of words with the prefix "abcde" is 1
        assertEquals(1, count);
    }

    /**
     * Test the {@code buildTrie} and {@code getSuggestions} methods of the {@link GameModel} class.
     * This method initializes the game model and verifies that the suggestions generated
     * for a given prefix are correct in terms of size and content.
     * 
     * Note: The use of GameModel here seems out of place as the class is named AutocompleteTest.
     * It might be a mistake or indicate a need for refactoring.
     */
    @Test
    public void testBuildTrieAndSuggestions() {
        // Create a new instance of GameModel
        GameModel model = new GameModel();
        // Initialize the data in the game model
        model.initialData();
        // Get suggestions for the prefix "a"
        List<String> list = model.getSuggestions("a");
        // Verify that the number of suggestions is 20
        assertEquals(20, list.size());
        // Verify that the first suggestion is "a beautiful mind"
        assertEquals("a beautiful mind", list.get(0));
    }
}