package utils;

public interface Observable {

	void notifyObserver(boolean loadLastGame);
	void addObserver(Observer obs);
	
}
