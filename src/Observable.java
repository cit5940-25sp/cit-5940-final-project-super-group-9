package src;

/**
 * src.Observable.java
 * It's for src.Observer-src.Observable Design Pattern
 */
public interface Observable {
    // add observer
    void addObserver(Observer observer);
    // delete observer
    //void remove(src.Observer observer);
    // notify observer
    void notifyObservers();
    // do operation
    //void operation();

}
