package controller.handleInput.show;

import controller.crud.Read;
import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.commands.show.CommandShowEventFilter;
import controller.event.events.commands.show.CommandShowEventUsedTags;
import controller.event.events.commands.show.CommandShowEventUsername;
import controller.handleInput.CommandController;
import controller.handleInput.InputConverter;
import modell.mediaDB.MediaContent;

import java.util.HashMap;
import java.util.List;

final class ShowController implements CommandController {

    private CommandShowEventFilter commandShowEventFilter;
    private CommandShowEventUsedTags commandShowEventUsedTags;
    private CommandShowEventUsername commandShowEventUsername;
    private StringBuffer stringBuffer;

    public ShowController() {
        this.config();
    }

    public final StringBuffer showFilter(String filterby) {
        this.stringBuffer = new StringBuffer();
        this.commandShowEventFilter.eventFilter(filterby);
        return stringBuffer;
    }

    public final StringBuffer showUsername(String name) {
        this.stringBuffer = new StringBuffer();
        this.commandShowEventUsername.eventUsername(name);
        return stringBuffer;
    }

    public final StringBuffer showUsedTags() {
        this.stringBuffer = new StringBuffer();
        this.commandShowEventUsedTags.eventusedTags();
        return stringBuffer;
    }

    @Override
    public void config() {
        EventHandler<EventListener> elShowAllEventHandler = new EventHandler<>();
        elShowAllEventHandler.add(event -> {
            final Read read = new Read();

            List<MediaContent> videos = read.getFullListOrFilterbyTyp(event.getText());

            for (MediaContent video : videos) {
                stringBuffer.append(video.toString()).append("\n");
            }
        });

        EventHandler<EventListener> elShowUsedTagsEventHandler = new EventHandler<>();
        elShowUsedTagsEventHandler.add(event -> {
            final Read read = new Read();

            HashMap<String, Boolean> map = read.getFindedTags();
            stringBuffer.append(map.keySet()).append(" | ").append(map.values());
        });

        EventHandler<EventListener> elShowUsernamePerIndexValueEventHandler = new EventHandler<>();
        elShowUsernamePerIndexValueEventHandler.add(event -> {
            final Read read = new Read();
            HashMap<String, Integer> map = read.listAllUsernamePerIndexValue(event.getText());
            stringBuffer.append(map.keySet()).append(" | ").append(map.values());
        });

        this.commandShowEventFilter = new CommandShowEventFilter(new InputConverter(), elShowAllEventHandler);
        this.commandShowEventUsedTags = new CommandShowEventUsedTags(new InputConverter(), elShowUsedTagsEventHandler);
        this.commandShowEventUsername = new CommandShowEventUsername(new InputConverter(), elShowUsernamePerIndexValueEventHandler);
    }
}
