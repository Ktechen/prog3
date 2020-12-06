package controller.event.events.listener.show;

import controller.crud.Read;
import controller.event.Event;
import controller.event.EventListener;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Uploadable;

import java.util.List;

public class ELShowAll implements EventListener {
    @Override
    public void onInputEvent(Event event) {
        Read read = new Read();

        List<MediaContent> videos = read.getFullListOrFilterbyTyp(event.getText());

        for (MediaContent video : videos) {
            System.out.println(video.toString());
        }
    }
}
