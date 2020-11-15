package data;

public class StorageAsSingelton {

    private volatile static Storage instance;

    private StorageAsSingelton() {
    }

    /**
     * Singelton option
     * https://de.wikibooks.org/wiki/Muster:_Java:_Singleton
     * https://stackoverflow.com/questions/1300655/whats-alternative-to-singleton
     *
     * @return memory
     */
    public synchronized static Storage getInstance() {
        if (null == StorageAsSingelton.instance) {
            StorageAsSingelton.instance = new Storage();
        }
        return StorageAsSingelton.instance;
    }

}
