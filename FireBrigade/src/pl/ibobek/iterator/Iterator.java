package pl.ibobek.iterator;

import pl.ibobek.observer.FireStation;

public interface Iterator {
    FireStation getNext();
    boolean hasMore();
}
