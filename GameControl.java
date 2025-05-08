

public class GameControl extends Control{
    private SimpleIO simpleIO;
    public GameControl(){
        simpleIO = new SimpleIO();
    }
    public void runInput(){
        GameModel gameModel = (GameModel) getModel();
        while (true) {
            String title = simpleIO.getInput(getModel());

            if (title != null && !gameModel.getMovies().contains(title)){
                continue;
            }else{
                gameModel.inputMovie(title);
                break;
            }
        }
    }
}