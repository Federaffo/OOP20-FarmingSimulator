package utils;

public interface Observer<X> {

    /**
     * @param notify that force to update observers
     */
    void update(X notify);
}
