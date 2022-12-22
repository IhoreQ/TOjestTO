package pl.ibobek.iterator;

import pl.ibobek.observer.FireStation;

import java.util.ArrayList;
import java.util.List;

public class FireStationsIterator implements Iterator {

    private FireStationsCollection fireStations;
    private List<FireStation> cache = null;
    private int currentPosition = -1;

    public FireStationsIterator(FireStationsCollection fireStations) {
        this.fireStations = fireStations;
    }

    private void lazyLoad() {
        if (cache == null) {
            cache = fireStations.getFireStations();
            currentPosition = -1;
        }
    }

    @Override
    public FireStation getNext() {
        if (hasMore()) {
            currentPosition++;
            return cache.get(currentPosition);
        } else
            return null;
    }

    @Override
    public boolean hasMore() {
        lazyLoad();
        return currentPosition < (cache.size() - 1);
    }
}
