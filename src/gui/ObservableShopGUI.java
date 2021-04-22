package gui;

import java.util.HashSet;
import java.util.Set;

import utils.Observable;
import utils.Observer;

public class ObservableShopGUI<Boolean> implements Observable<Boolean> {

    private Set<Observer<Boolean>> obsSet = new HashSet<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(final Observer obs) {
        this.obsSet.add(obs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObserver(final Boolean notify) {
        for (Observer<Boolean> o : this.obsSet) {
            o.update(notify);
        }
    }
}
