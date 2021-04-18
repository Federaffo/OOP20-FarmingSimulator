package gui;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import utils.Observable;
import utils.Observer;

public class ObservableShopGUI implements Observable<Boolean>{

	private Set<Observer> obsSet  = new HashSet<>();
	
	public ObservableShopGUI(){
		
	}

	@Override
	public void addObserver(Observer obs) {
		this.obsSet.add(obs);
	}

	@Override
	public void notifyObserver(Boolean notify) {
		for(Observer o : this.obsSet) {
			o.update(notify);
		}
	}
}
