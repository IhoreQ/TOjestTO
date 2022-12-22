package pl.ibobek.observer;

import pl.ibobek.strategy.Event;

public interface Subscriber {
    void update(Event event);
}
