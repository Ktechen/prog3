package observer.observers;

import cli.commands.options.CommandAdd;
import observer.Observer;

public class ObserverConsoleTags implements Observer {

    private CommandAdd observable;

    public ObserverConsoleTags(CommandAdd observable) {
        this.observable = observable;
        this.observable.join(this);
    }

    @Override
    public void update() {
        //TODO für Beleg muss das fertig sein
    }
}
