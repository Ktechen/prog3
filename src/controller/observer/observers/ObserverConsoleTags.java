package controller.observer.observers;

import controller.crud.Create;
import controller.observer.Observer;

public class ObserverConsoleTags implements Observer {

    private Create observable;

    public ObserverConsoleTags(Create observable) {
        this.observable = observable;
        this.observable.join(this);
    }

    @Override
    public void update() {
        //TODO für Beleg muss das fertig sein
    }
}
