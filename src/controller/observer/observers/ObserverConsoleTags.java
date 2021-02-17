package controller.observer.observers;

import controller.crud.Create;
import controller.observer.Observer;
import modell.mediaDB.Tag;

import java.util.Arrays;
import java.util.HashMap;

public class ObserverConsoleTags implements Observer {

    private Create observable;
    private boolean[] init;

    public ObserverConsoleTags(Create observable) {
        this.observable = observable;
        this.observable.join(this);

        this.init = new boolean[Tag.values().length];
        for (int i = 0; i < Tag.values().length; i++) {
            this.init[i] = false;
        }
    }

    @Override
    public void update() {
        HashMap<String, Boolean> readMap = this.observable.getTags();
        Object[] elem = readMap.values().toArray();

        boolean[] temp = new boolean[Tag.values().length];
        for (int i = 0; i < elem.length; i++) {
            temp[i] = Boolean.parseBoolean(elem[i].toString());
        }

        //Check if not equals
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != this.init[i]) {
                System.out.println("Änderungen an den vorhandenen Tags informieren. Show: ");
                System.out.println(Arrays.toString(readMap.entrySet().toArray()));
                break;
            }
        }

        this.init = temp;
    }

}
