package modell.bean;

import modell.data.content.*;
import modell.data.storage.Storage;
import modell.mediaDB.Audio;
import modell.mediaDB.Uploader;

import java.io.Serializable;
import java.util.*;

public class BeanStorage implements Serializable {

    private Storage storage;
    private List<BeanItem> media;
    private Set<BeanItemPerson> uploaders;
    private HashMap<String, Boolean> getUsedTags;
    private HashMap<String, Long> counter;

    /**
     * Created Bean Storage from Storage Logic
     */
    public BeanStorage() {
        this.storage = Storage.getInstance();
        this.getUsedTags = this.storage.getUsedTags();
        this.counter = this.storage.getCountOfUse();
        this.media = new ArrayList<>();
        this.uploaders = new HashSet<>();
    }

    /**
     * Getter and Setter Required for Bean conversion
     */

    public List<BeanItem> getMedia() {
        return media;
    }

    public void setMedia(List<BeanItem> media) {
        this.media = media;
    }

    public Set<BeanItemPerson> getUploaders() {
        return uploaders;
    }

    public void setUploaders(Set<BeanItemPerson> uploaders) {
        this.uploaders = uploaders;
    }

    public HashMap<String, Boolean> getGetUsedTags() {
        return getUsedTags;
    }

    public void setGetUsedTags(HashMap<String, Boolean> getUsedTags) {
        this.getUsedTags = getUsedTags;
    }

    public HashMap<String, Long> getCounter() {
        return counter;
    }

    public void setCounter(HashMap<String, Long> counter) {
        this.counter = counter;
    }

    public void addToUploaderList() {
        synchronized (this.storage) {
            Iterator<Uploader> it = this.storage.getPerson().iterator();
            while (it.hasNext()) {
                BeanItemPerson beanItemPerson = new BeanItemPerson();
                beanItemPerson.setName(it.next().getName());
                this.uploaders.add(beanItemPerson);
            }
        }
    }

    public void addToMediaList() {
        synchronized (this.storage) {
            for (int i = 0; i < this.storage.getMedia().size(); i++) {

                String value = this.storage.getMedia().get(i).getClass().getSimpleName();
                Object o = this.storage.getMedia().get(i);

                switch (value) {
                    case "Video":
                        this.addVideo((Video) o);
                        continue;

                    case "Audio":
                        this.addAudio((Audio) o);
                        continue;

                    case "AudioVideo":
                        this.addAudioVideo((AudioVideo) o);
                        continue;

                    case "LicensedAudioVideo":
                        this.addLicensedAudioVideo((LicensedAudioVideo) o);
                        continue;

                    case "LicensedAudio":
                        this.addLicensedAudio((LicensedAudio) o);
                        continue;

                    case "LicensedVideo":
                        this.addLicensedVideo((LicensedVideo) o);
                        continue;

                    case "InteractiveVideo":
                        this.addInteractiveVideo((InteractiveVideo) o);
                        continue;
                    default:
                }
            }
        }
    }

    private void addVideo(Video o) {
        BeanItemVideo bean = new BeanItemVideo();
        bean.address = o.getAddress();
        bean.bitrate = o.getBitrate();
        bean.accessCount = o.getAccessCount();
        bean.date = o.getUploadDate();
        bean.length = o.getLength().toString();
        bean.encoding = o.getEncoding();
        bean.height = o.getHeight();
        bean.width = o.getWidth();
        bean.size = o.getSize();
        bean.tags = o.getTags();
        bean.uploader = o.getUploader().toString();

        this.media.add(bean);
    }

    private void addLicensedAudioVideo(LicensedAudioVideo o) {
        BeanItemLicensedAudioVideo bean = new BeanItemLicensedAudioVideo();
        bean.address = o.getAddress();
        bean.bitrate = o.getBitrate();
        bean.accessCount = o.getAccessCount();
        bean.date = o.getUploadDate();
        bean.length = o.getLength().toString();
        bean.encoding = o.getEncoding();
        bean.height = o.getHeight();
        bean.width = o.getWidth();
        bean.holder = o.getHolder();
        bean.uploader = o.getUploader().getName();
        bean.tags = o.getTags();
        bean.samplingRate = o.getSamplingRate();

        this.media.add(bean);
    }

    private void addLicensedAudio(LicensedAudio o) {
        BeanItemLicensedAudio bean = new BeanItemLicensedAudio();
        bean.address = o.getAddress();
        bean.bitrate = o.getBitrate();
        bean.accessCount = o.getAccessCount();
        bean.date = o.getUploadDate();
        bean.length = o.getLength().toString();
        bean.encoding = o.getEncoding();
        bean.holder = o.getHolder();
        bean.size = o.getSize();
        bean.samplingRate = o.getSamplingRate();
        bean.tags = o.getTags();
        bean.uploader = o.getUploader().getName();

        this.media.add(bean);
    }

    private void addLicensedVideo(LicensedVideo o) {
        BeanItemLicensedVideo bean = new BeanItemLicensedVideo();
        bean.uploader = o.getUploader().getName();
        bean.tags = o.getTags();
        bean.address = o.getAddress();
        bean.bitrate = o.getBitrate();
        bean.accessCount = o.getAccessCount();
        bean.date = o.getUploadDate();
        bean.holder = o.getHolder();
        bean.encoding = o.getEncoding();
        bean.height = o.getHeight();
        bean.width = o.getWidth();
        bean.size = o.getSize();

        this.media.add(bean);
    }

    private void addInteractiveVideo(InteractiveVideo o) {
        BeanItemInteractiveVideo bean = new BeanItemInteractiveVideo();
        bean.address = (o.getAddress());
        bean.bitrate = (o.getBitrate());
        bean.accessCount = (o.getAccessCount());
        bean.uploaderDate = (o.getUploadDate());
        bean.length = (o.getLength().toString());
        bean.encoding = (o.getEncoding());
        bean.tags = (o.getTags());
        bean.height = (o.getHeight());
        bean.width = (o.getWidth());
        bean.uploader = (o.getUploader().getName());
        bean.type = (o.getType());

        this.media.add(bean);
    }

    private void addAudioVideo(AudioVideo o) {
        BeanItemAudioVideo bean = new BeanItemAudioVideo();
        bean.address = o.getAddress();
        bean.bitrate = o.getBitrate();
        bean.accessCount = o.getAccessCount();
        bean.date = o.getUploadDate();
        bean.length = o.getLength().toString();
        bean.encoding = o.getEncoding();
        bean.tags = o.getTags();
        bean.height = o.getHeight();
        bean.width = o.getWidth();
        bean.samplingRate = o.getSamplingRate();
        bean.uploader = o.getUploader().toString();
        bean.size = o.getSize();

        this.media.add(bean);
    }

    private void addAudio(Audio o) {
        BeanItemAudio bean = new BeanItemAudio();
        bean.address = o.getAddress();
        bean.bitrate = o.getBitrate();
        bean.accessCount = o.getAccessCount();
        bean.date = o.getUploadDate();
        bean.length = o.getLength().toString();
        bean.encoding = o.getEncoding();
        bean.size = o.getSize();
        bean.tags = o.getTags();
        bean.uploader = o.getUploader().getName();
        bean.samplingRate = o.getSamplingRate();

        this.media.add(bean);
    }

}
