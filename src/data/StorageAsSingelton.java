package data;

public final class StorageAsSingelton{

    private static Storage instance;

    private StorageAsSingelton() {
    }

    /**
     * Singelton option
     * https://de.wikibooks.org/wiki/Muster:_Java:_Singleton
     * https://stackoverflow.com/questions/1300655/whats-alternative-to-singleton
     * @return memory
     */
    public static synchronized Storage getInstance(){
        if(StorageAsSingelton.instance == null){
            StorageAsSingelton.instance = new Storage();
        }
        return StorageAsSingelton.instance;
    }
    
}
