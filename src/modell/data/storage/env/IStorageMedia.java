package modell.data.storage.env;

import modell.mediaDB.MediaContent;
import modell.mediaDB.Uploadable;

import java.util.Collection;
import java.util.List;

public interface IStorageMedia<T extends Uploadable & MediaContent>  {

    /**
     * Add file to media
     *
     * @param t = uploadable or MediaContent
     * @return if false not addable | if true add to collection
     */
    boolean addMedia(T t);

    /**
     * Remove Media
     *
     * @param o = file
     * @return if false not removable | if true remove from collection
     */
    boolean removeMedia(Object o);

    /**
     * Remove Files of Media Collection
     *
     * @param t
     * @return if false not removable | if true remove from collection
     */
    boolean removeAllMedia(Collection<T> t);

    /**
     * Get Media List
     *
     * @return media
     */
    List<T> getMedia();

}
