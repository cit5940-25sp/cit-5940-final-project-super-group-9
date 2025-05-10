import org.junit.Test;


import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testGameWinner() {
        GameModel model = new GameModel();
        model.initialData();
        Player player1 = new Player("Player1");
        model.addPlayer(player1);
        model.nextRound();
        model.inputMovie("red 2");
        model.inputMovie("Eyes Wide Shut");
        model.inputMovie("minority report");
        model.inputMovie("Edge of Tomorrow");

        model.inputMovie("The Bourne Identity");
        model.inputMovie("The Bourne ultimatum");
        model.inputMovie("Home Fries");


        assertTrue(player1.isWinner());
        model.gameOver();
    }
    /**
     * Test the {@code run} functionality of the game.
     * This method sets up the MVC (Model-View-Controller) components of the game,
     * initializes the game data, adds players, progresses to the next round,
     * and tests the movie input functionality of the model.
     */
    @Test
    public void testRun() {
        // Create a new instance of GameView, responsible for displaying the game interface
        GameView view = new GameView();
        // Create a new instance of GameModel, responsible for managing game data
        GameModel model = new GameModel();
        // Create a new instance of GameControl, responsible for handling user input and game logic
        GameControl control = new GameControl();
        // Register the view as an observer of the model so it can be notified of data changes
        model.addObserver(view);
        // Set the model for the controller, allowing it to interact with the game data
        control.setModel(model);
        // Set the model for the view, enabling it to display the game data
        view.setModel(model);
        // Start the game view, which may initialize the UI or display the starting screen
        view.start();
        // Initialize the game data in the model, such as setting up initial game state
        model.initialData();
        // Create a new player object with the name "Player1"
        Player player1 = new Player("Player1");
        // Create a new player object with the name "Player2"
        Player player2 = new Player("Player2");
        // Add Player1 to the game model
        model.addPlayer(player1);
        // Add Player2 to the game model
        model.addPlayer(player2);
        // Progress the game to the next round
        model.nextRound();
        // Attempt to input the movie "vanilla sky" into the model
        boolean r = model.inputMovie("vanilla sky");
        // Assert that the movie input was successful
        assertTrue(r);
        // Attempt to input the same movie "vanilla sky" again
        r = model.inputMovie("vanilla sky");
        // Assert that the duplicate movie input was not successful
        assertFalse(r);
        // Attempt to input a non - existent movie "nop123"
        r = model.inputMovie("nop123");
        // Assert that the input of a non - existent movie was not successful
        assertFalse(r);
    }

}