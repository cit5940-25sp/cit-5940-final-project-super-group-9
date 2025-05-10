import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class contains test cases for the Link and Links functionality.
 * It verifies the connection logic between movies and the behavior of the Links class.
 */
public class LinkTest {
    /**
     * Test the connection logic between movies using the Links class.
     * This test creates multiple Movie objects, attempts to add them to a Links object,
     * and verifies the results using various assertions.
     */
    @Test
    public void testConnection() {
        // Create a new MovieDate instance to manage movie data
        MovieDate movieData = new MovieDate();
        // Retrieve a movie by its title from the movie data
        Movie movie = movieData.getMovieByTitle("mission: impossible");
        // Retrieve another movie by its title from the movie data
        Movie movie1 = movieData.getMovieByTitle("vanilla sky");
        // Retrieve another movie by its title from the movie data
        Movie movie2 = movieData.getMovieByTitle("minority report");
        // Retrieve another movie by its title from the movie data
        Movie movie3 = movieData.getMovieByTitle("red 2");
        // Retrieve another movie by its title from the movie data
        Movie movie4 = movieData.getMovieByTitle("Eyes Wide Shut");
        // Retrieve another movie by its title from the movie data
        Movie movie5 = movieData.getMovieByTitle("rock of ages");
        // Retrieve another movie by its title from the movie data
        Movie movie6 = movieData.getMovieByTitle("galaxy quest");
        // Retrieve another movie by its title from the movie data
        Movie movie7 = movieData.getMovieByTitle("Home Fries");

        // Assert that the movie1 object is not null
        assertNotNull(movie1);
        // Create a new instance of the Links class to manage movie connections
        Links links = new Links();
        // Assert that the Links object is initially empty
        assertTrue(links.isEmpty());
        // Set the current movie in the Links object
        links.setCurrentMovie(movie);
        // Assert that adding movie1 to the Links object is successful
        assertTrue(links.addLink(movie1));
        // Assert that adding movie2 to the Links object is successful
        assertTrue(links.addLink(movie2));
        // Assert that adding movie3 to the Links object is successful
        assertTrue(links.addLink(movie3));
        // Assert that adding movie4 to the Links object fails due to no connection
        assertFalse(links.addLink(movie4)); // no connection
        // Assert that adding movie5 to the Links object fails due to same connection type
        assertFalse(links.addLink(movie5)); // same connection type
        // Assert that adding movie6 to the Links object is successful
        assertTrue(links.addLink(movie6));
        // Assert that the Links object is not full
        assertFalse(links.isFull());
        // Assert that adding movie7 to the Links object is successful
        assertTrue(links.addLink(movie7));

        // Assert that the common genre among all movies in the Links object is null
        assertEquals(links.getCommonGenre(), null);
        // Print the string representation of the Links object
        System.out.println(links.toString());
    }
}
