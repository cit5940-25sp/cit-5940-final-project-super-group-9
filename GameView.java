public class GameView extends View implements Observer{

    public GameView(){
        ;
    }
    public void start(){
        System.out.println("Welcome to CineGame!");
        System.out.println("Loading data...");
        showMessage();
    }
    public void showMessage(){
        GameModel model = (GameModel) getModel();
        System.out.println(model.getGameStatusString());
    }
    @Override
    public void update(){
        showMessage();
    }
}
1
