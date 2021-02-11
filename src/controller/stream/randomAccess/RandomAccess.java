package controller.stream.randomAccess;

import controller.handleInput.InputConverter;
import modell.data.storage.Storage;
import modell.mediaDB.*;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * https://www.tutorialspoint.com/java/io/randomaccessfile_readline.htm
 * https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
 */
public class RandomAccess {

    public static final String PATH = "testfiles/";
    public static final String FILE = PATH + "RandomAccess.txt";

    private HashMap<Long, String> listOfAddresses;
    private long seekNumber = -1;
    private Object o;
    private RandomAccessHandle randomAccessHandle;

    public RandomAccess() {
        this.randomAccessHandle = new RandomAccessHandle();
        this.listOfAddresses = new HashMap<>();
        this.prepareMap();
    }

    private void prepareMap() {

        if (this.listOfAddresses.size() == 0) {
            this.seekNumber = 0;
        }

        //Set next pointer number
        this.seekNumber = this.listOfAddresses.size();
    }

    public HashMap<Long, String> getListOfAddresses() {
        return new HashMap<>(this.listOfAddresses);
    }

    public long getSeekNumber() {
        return seekNumber;
    }

    public Object getO() {
        return o;
    }

    /**
     * Save a Media Object per Address
     *
     * @param address
     */
    public void save(String address) throws NullPointerException, IllegalArgumentException {
        synchronized (Storage.class) {
            this.saveImpl(address);
        }
    }


    /**
     * Load Randomly per Address
     *
     * @param address
     * @throws IllegalAccessException   = address not found
     * @throws NullPointerException     = address == null
     * @throws IllegalArgumentException = Address type is incorrect
     */
    public void load(String address) throws IllegalAccessException, NullPointerException, IllegalArgumentException {
        synchronized (Storage.class) {
            this.loadImpl(address);
            boolean check = this.addToMedia();
        }
    }

    private boolean addToMedia() {
        Storage storage = Storage.getInstance();

        if (o instanceof LicensedAudioVideo) {
            storage.addMedia((LicensedAudioVideo) o);
            return true;
        }

        if (o instanceof InteractiveVideo) {
            storage.addMedia((InteractiveVideo) o);
            return true;
        }

        if (o instanceof AudioVideo) {
            storage.addMedia((AudioVideo) o);
            return true;
        }

        if (o instanceof LicensedVideo) {
            storage.addMedia((LicensedVideo) o);
            return true;
        }

        if (o instanceof LicensedAudio) {
            storage.addMedia((LicensedAudio) o);
            return true;
        }

        if (o instanceof Video) {
            storage.addMedia((Video) o);
            return true;
        }

        if (o instanceof Audio) {
            storage.addMedia((Audio) o);
            return true;
        }

        return false;
    }

    private void loadImpl(String address) throws IllegalAccessException, NullPointerException, IllegalArgumentException {

        if (null == address) {
            throw new NullPointerException("RandomAccess Address is null");
        }

        if (!address.contains(Storage.TYPE_OF_SOURCE)) {
            throw new IllegalArgumentException("Type of address is unknown");
        }

        AtomicLong seeker = new AtomicLong();
        seeker.set(-1);

        listOfAddresses.forEach((key, value) -> {
            if (value.equals(address)) {
                seeker.set(key);
            }
        });

        if (seeker.get() == -1) {
            throw new IllegalAccessException("Address not found in Random Access Table");
        }

        /**
         * https://stackoverflow.com/questions/22375924/how-the-randomaccessfile-class-returns-bytes-with-randomaccessfile-read-method
         */
        try (RandomAccessFile raf = new RandomAccessFile(FILE, "r")) {
            byte[] bytes = new byte[(int) raf.length()];
            raf.seek(seeker.get());
            raf.read(bytes);

            this.o = this.randomAccessHandle.convertFromBytes(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveImpl(String address) throws NullPointerException, IllegalArgumentException {

        if (null == address) {
            throw new NullPointerException("RandomAccess Address is null");
        }

        if (!address.contains(Storage.TYPE_OF_SOURCE)) {
            throw new IllegalArgumentException("Type of address is unknown");
        }

        AtomicLong seeker = new AtomicLong();

        //Search Address
        listOfAddresses.forEach((key, value) -> {
            if (value.equals(address)) {
                seeker.set(key);
            } else {
                seeker.set(seekNumber);
            }
        });

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(FILE, "rw")) {
            randomAccessFile.seek(seeker.get());
            randomAccessFile.write(this.randomAccessHandle.convertToBytes(address));
            this.listOfAddresses.put(seeker.get(), address);
            this.seekNumber++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
