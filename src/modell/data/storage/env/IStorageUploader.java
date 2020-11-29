package modell.data.storage.env;

import modell.mediaDB.Uploader;

import java.util.Set;

public interface IStorageUploader {

    /**
     * Add Upload
     *
     * @param uploader = user
     * @return if false not addable | if true add to collection
     */
    boolean addPerson(Uploader uploader);

    /**
     * Remove Uploader
     *
     * @param uploader
     * @return if false not removable | if true remove from collection
     */
    boolean remove(Uploader uploader);


    /**
     * get Uploader
     *
     * @return Set of Uploader
     */
    Set<Uploader> getPerson();

}
