/**
 * Observable.java
 * It's for Observer-Observable Design Pattern
 */
public interface Observable {
    // add observer
    void add(Observer observer);
    // delete observer
    //void remove(Observer observer);
    // notify observer
    void notifyObservers();
    // do operation
    //void operation();

}
