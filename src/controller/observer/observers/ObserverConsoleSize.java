package controller.observer.observers;

import controller.crud.Create;
import controller.observer.Capacity;
import controller.observer.Observer;

import java.math.BigDecimal;

public class ObserverConsoleSize implements Observer {

    private Create observable;
    private String errAlert;

    public ObserverConsoleSize(Create observable) {
        this.observable = observable;
        this.observable.join(this);
    }

    @Override
    public void update() {
        BigDecimal number = observable.getCapacity();
        final Capacity capacity = new Capacity(BigDecimal.valueOf(90), number);
        final String text = "Die Capacity von " + capacity.getProcent() + " % wurde überschritten";
        this.errAlert = text;
        if (capacity.cautionOfOverLoad()) {
            System.out.print(text);
        }
    }

    public String getErrAlert() {
        return errAlert;
    }
}
