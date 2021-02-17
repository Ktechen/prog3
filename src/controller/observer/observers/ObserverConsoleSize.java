package controller.observer.observers;

import controller.crud.Create;
import controller.observer.Capacity;
import controller.observer.Observer;

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
        BigDecimal number = observable.getCapacity();
        Capacity capacity = new Capacity(BigDecimal.valueOf(90), number);
        final String text = "Die Capacity von 90 % wurde überschritten";
        boolean check = capacity.cautionOfOverLoad();
        if (check) {
            System.out.print(text);
        }
    }

}
