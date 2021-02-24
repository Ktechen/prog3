package controller.observer.observers;

import controller.crud.Create;
import controller.observer.Observer;
import controller.storage.Storage;

import java.math.BigDecimal;

public class ObserverConsoleSize implements Observer {

    private Create observable;

    public ObserverConsoleSize(Create observable) {
        this.observable = observable;
        this.observable.join(this);
    }


    //TODO Change to Global size
    @Override
    public void update() {
        BigDecimal number90 = Storage.getInstance().getMaxSize().multiply(BigDecimal.valueOf(0.90));
        BigDecimal number = observable.getCapacity();
        final String text = "Die Capacity von 90 % wurde überschritten";
        if (number.compareTo(number90) == 0 || number.compareTo(number90) > 0) {
            System.out.print(text);
        }
    }

}
