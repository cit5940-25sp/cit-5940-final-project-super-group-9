import java.io.IOException;

/**
 * GameControl class extends the base Control class, acting as a controller in the MVC architecture.
 * It is responsible for handling user input using the SimpleIO class.
 */
public class GameControl extends Control{

    public GameControl(){
    }

    /**
     * Initiates the input handling process.
     * Calls the runInput method of the SimpleIO instance with the model retrieved from the base Control class.
     */
    public void runInput(){
        // Invoke the runInput method of SimpleIO with the model
        SimpleIO simpleIO = new SimpleIO();
        simpleIO.runInput(getModel());
        // press any key to continue...
        /* 
        System.out.println("Press any key to continue...");
        try {
            int inputByte = System.in.read();
            //System.out.println(inputByte);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        */
        
    }
}
