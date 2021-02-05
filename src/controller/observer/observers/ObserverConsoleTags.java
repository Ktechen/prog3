package controller.observer.observers;

import controller.crud.Create;
import controller.crud.Read;
import controller.observer.Observer;

import java.util.Map;
import java.util.stream.Collectors;

public class ObserverConsoleTags implements Observer {

    private Create observable;

    public ObserverConsoleTags(Create observable) {
        this.observable = observable;
        this.observable.join(this);
    }

    @Override
    public void update() {
        final Read read = new Read();
        Map<String, Boolean> readMap = read.getFindedTags();

        Map<String, Boolean> show = readMap.entrySet()
                .stream()
                .filter(map -> map.getValue().equals(true))
                .collect(Collectors.toMap(Map.Entry::getKey, stringBooleanEntry -> true));


        System.out.println(show);
        System.out.println(show.size());
    }
}
