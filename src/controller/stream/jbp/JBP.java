package controller.stream.jbp;

import controller.crud.Create;
import controller.crud.Update;
import modell.bean.*;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.MediaContent;

import java.time.Duration;
import java.util.LinkedList;

public class JBP {
    public static final String PATH = "testfiles/";
    private String filename;
    private final Storage storage;

    //TODO PUBLIC 

    public JBP(String filename) {
        if (null == filename) {
            throw new NullPointerException("filename is null");
        }
        this.filename = filename;
        this.filename = PATH + this.filename;
        this.storage = Storage.getInstance();
    }

    public void save() {
        SaveJBP saveJBP = new SaveJBP(this.filename);
    }

    /**
     * Load a Data and add this Data to Storage of Media management
     */
    public void load() {
        synchronized (this.storage) {
            LoadJBP loadJBP = new LoadJBP(this.filename);
            BeanStorage o = (BeanStorage) loadJBP.getObject();

            for (int i = 0; i < o.getMedia().size(); i++) {
                if (o.getMedia().get(i) instanceof BeanItemInteractiveVideo) {
                    BeanItemInteractiveVideo e = (BeanItemInteractiveVideo) o.getMedia().get(i);
                    this.loadInteractiveVideo(e);
                    continue;
                }

                if (o.getMedia().get(i) instanceof BeanItemVideo) {
                    BeanItemVideo e = (BeanItemVideo) o.getMedia().get(i);
                    this.loadVideo(e);
                    continue;
                }

                if (o.getMedia().get(i) instanceof BeanItemAudio) {
                    BeanItemAudio e = (BeanItemAudio) o.getMedia().get(i);
                    this.loadAudio(e);
                    continue;
                }

                if (o.getMedia().get(i) instanceof BeanItemAudioVideo) {
                    BeanItemAudioVideo e = (BeanItemAudioVideo) o.getMedia().get(i);
                    this.loadAudioVideo(e);
                    continue;
                }

                if (o.getMedia().get(i) instanceof BeanItemLicensedVideo) {
                    BeanItemLicensedVideo e = (BeanItemLicensedVideo) o.getMedia().get(i);
                    this.loadLicensedVideo(e);
                    continue;
                }

                if (o.getMedia().get(i) instanceof BeanItemLicensedAudio) {
                    BeanItemLicensedAudio e = (BeanItemLicensedAudio) o.getMedia().get(i);
                    this.loadLicensedAudio(e);
                    continue;
                }

                if (o.getMedia().get(i) instanceof BeanItemLicensedAudioVideo) {
                    BeanItemLicensedAudioVideo e = (BeanItemLicensedAudioVideo) o.getMedia().get(i);
                    this.loadLicensedAudioVideo(e);
                }
            }

            /**
             * Obsolete create by Create object
             Storage.getInstance().setCountOfUse(o.getCounter());
             Storage.getInstance().setUsedTags(o.getGetUsedTags());
             */
        }
    }

    private void loadLicensedAudioVideo(BeanItemLicensedAudioVideo e) {
    }

    private void loadLicensedAudio(BeanItemLicensedAudio e) {
    }

    private void loadLicensedVideo(BeanItemLicensedVideo e) {
    }

    private void loadAudioVideo(BeanItemAudioVideo e) {
    }

    private void loadAudio(BeanItemAudio e) {
    }

    private void loadVideo(BeanItemVideo e) {
    }


    private void loadInteractiveVideo(BeanItemInteractiveVideo e) {
        final Create create = new Create();
        final Update update = new Update();

        long tempCount = e.accessCount;

        create.interactiveVideo(
                e.width, e.height,
                e.encoding, e.bitrate,
                Duration.parse(e.length), e.tags,
                new Person(e.uploader), e.type
        );

        //Get new Mediafile
        LinkedList<MediaContent> contents = (LinkedList<MediaContent>) Storage.getInstance().getMedia();
        MediaContent content = contents.getLast();

        String address = content.getAddress();

        //Update new Media with old clicks
        for (long j = 0; j < tempCount; j++) {
            update.accessCount(address);
        } 
    }

}
