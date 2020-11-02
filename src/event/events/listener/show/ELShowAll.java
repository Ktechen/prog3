package event.events.listener.show;

import crud.Read;
import event.Event;
import event.EventListener;
import mediaDB.Video;

import java.util.List;

public class ELShowAll implements EventListener {
    @Override
    public void onInputEvent(Event event) {
        Read read = new Read();
        List<Video> list = read.getFullListOrFilterbyTyp(event.getText());
        System.out.println(list.toString());
    }
}
