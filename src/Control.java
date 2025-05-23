/**
 * The Control class acts as a controller in the MVC (Model-View-Controller) architecture.
 * It manages the interaction between the view and the model, providing methods to set and get the model.
 */
public class Control {
    // Reference to the Model object. This controller uses the model to access application data and logic.
    private Model Model;

    /**
     * Default constructor for the Control class.
     * Initializes the model reference to null.
     */
    public Control(){
        Model = null;
    }

    /**
     * Sets the model associated with this controller.
     * 
     * @param model The Model object to be associated with this controller.
     */
    public void setModel(Model model) {
        this.Model = model;
    }

    /**
     * Retrieves the model associated with this controller.
     * 
     * @return The Model object currently associated with this controller.
     */
    public Model getModel() {
        return Model; 
    }
}
