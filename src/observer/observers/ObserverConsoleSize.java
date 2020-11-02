package observer.observers;

import cli.commands.options.CommandAdd;
import observer.Capacity;
import observer.Observer;

import java.math.BigDecimal;

public class ObserverConsoleSize implements Observer {

    private CommandAdd observable;

    public ObserverConsoleSize(CommandAdd observable) {
        this.observable = observable;
        this.observable.join(this);
    }

    @Override
    public void update() {
        BigDecimal number = observable.getCapacity();
        Capacity capacity = new Capacity(BigDecimal.valueOf(90), number);
        if (capacity.cautionOfOverLoad()) {
            System.out.println("Die Capacity von " + capacity.getProcent() + " % wurde überschritten");
        }
    }

}
