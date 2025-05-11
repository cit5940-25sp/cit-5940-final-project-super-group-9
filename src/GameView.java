/**
 * Represents the view component for the game in an MVC (Model-View-Controller) architecture.
 * This class extends the {@code View} class and implements the {@code Observer} interface,
 * allowing it to receive updates from the model.
 */
public class GameView extends View implements Observer{
    
    /**
     * Default constructor for the GameView class.
     * Currently, it does not perform any specific initialization tasks.
     */
    public GameView(){
        ;
    }

    /**
     * Starts the game view by displaying a welcome message and a loading indicator.
     * Then it calls the {@code showMessage} method to display the game status.
     */
    public void start(){
        System.out.println("Welcome to CineGame!");
        System.out.println("Loading data...");
        showMessage();
    }

    /**
     * Retrieves the {@code GameModel} instance from the superclass and displays the game status.
     * It casts the model obtained from {@code getModel()} to {@code GameModel}
     * and prints the game status string.
     */
    public void showMessage(){
        GameModel model = (GameModel) getModel();
        System.out.println(model.getGameStatusString());
    }

    /**
     * Implements the {@code update} method from the {@code Observer} interface.
     * This method is called when the observed model notifies its observers of a change.
     * It simply calls the {@code showMessage} method to update the displayed game status.
     */
    @Override
    public void update(){
        showMessage();
    }
}