
public class GameModel extends Model implements Observable {
    private GameView views;
    private GameStatus systemData;
    private MovieDate movieData;
    public GameModel(){

    }

    public void notifyCustomers(){
        Manager manager = systemData.getManager();
        manager.notifyObservers();
    }

    @Override
    public void add(Observer observer) {
        views.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : views) {
            observer.update();
        }
    }

}
