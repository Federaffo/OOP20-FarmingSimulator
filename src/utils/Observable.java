package utils;

public interface Observable <X> {

	void notifyObserver(X notify);
	void addObserver(Observer obs);
	
}
