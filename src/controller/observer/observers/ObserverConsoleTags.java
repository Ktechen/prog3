package controller.observer.observers;

import controller.crud.Create;
import controller.crud.Read;
import controller.observer.Observer;
import modell.mediaDB.Tag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ObserverConsoleTags implements Observer {

    private Create observable;
    private boolean[] booleans;

    public ObserverConsoleTags(Create observable) {
        this.observable = observable;
        this.observable.join(this);
    }

    @Override
    public void update() {
        final Read read = new Read();
        HashMap<String, Boolean> readMap = read.getFindedTags();


        Map<String, Boolean> show = readMap.entrySet()
                .stream()
                .filter(map -> map.getValue().equals(true))
                .collect(Collectors.toMap(Map.Entry::getKey, stringBooleanEntry -> true));

        /*
        boolean life = readMap.get("Lifestyle");
        boolean news = readMap.get("News");
        boolean animal = readMap.get("Animal");
        boolean tutorial = readMap.get("Tutorial");

        booleans = new boolean[4];
        booleans[0] = life;
        booleans[1] = news;
        booleans[2] = animal;
        booleans[3] = tutorial;

        for (int i = 0; i < booleans.length; i++) {
            System.out.print(booleans[i]);
        }
        */

        //Call Tags
        System.out.println(Arrays.toString(show.entrySet().toArray()));
    }
}
