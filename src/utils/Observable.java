package utils;

public interface Observable<X> {

    /**
     * @param notify
     */
    void notifyObserver(X notify);

    /**
     * @param obs
     */
    void addObserver(Observer obs);

}
