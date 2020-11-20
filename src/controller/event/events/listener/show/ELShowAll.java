package controller.event.events.listener.show;

import controller.crud.Read;
import controller.event.Event;
import controller.event.EventListener;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Video;

import java.util.List;

public class ELShowAll implements EventListener {
    @Override
    public void onInputEvent(Event event) {
        Read read = new Read();

        List<Video> videos = read.getFullListOrFilterbyTyp(event.getText());

        for (Uploadable video : videos) {
            System.out.println(video.toString());
        }
    }
}
