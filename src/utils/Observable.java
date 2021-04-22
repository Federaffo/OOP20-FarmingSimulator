package utils;

public interface Observable<X> {

    /**
     * @param notify
     */
    void notifyObserver(X notify);

    /**
     * @param obs is a new Observer to be added
     */
    void addObserver(Observer<X> obs);

}
