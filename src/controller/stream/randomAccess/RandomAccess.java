package controller.stream.randomAccess;

import modell.data.storage.Storage;
import modell.mediaDB.*;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

/**
 * https://www.tutorialspoint.com/java/io/randomaccessfile_readline.htm
 * https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
 */
public class RandomAccess {

    public static final String PATH = "testfiles/";
    public static final String XML_FILE = PATH + "RandomAccessMap.xml";
    public static final String FILE = PATH + "RandomAccess.txt";

    private long seekNumber = -1;
    private Object o;
    private RandomAccessHandle randomAccessHandle;
    private HashMap<Long, String> listOfAddresses;
    boolean isAddressInCollection = false;

    public RandomAccess() {
        this.randomAccessHandle = new RandomAccessHandle();
        this.prepareMap();
    }

    private void prepareMap() {
        File file = new File(FILE);
        if (file.exists()) {
            try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                String line = null;
                this.listOfAddresses = new HashMap<>();
                while ((line = raf.readLine()) != null) {
                    String newline = raf.readLine();
                    listOfAddresses.put(Long.parseLong(line), newline);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Save a Media Object per Address
     *
     * @param address
     */
    public void save(String address) throws NullPointerException, IllegalArgumentException {
        synchronized (Storage.class) {

            if (null == address) {
                throw new NullPointerException("RandomAccess Address is null");
            }

            if (!address.contains(Storage.TYPE_OF_SOURCE)) {
                throw new IllegalArgumentException("Type of address is unknown");
            }

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

            if (null == address) {
                throw new NullPointerException("RandomAccess Address is null");
            }

            if (!address.contains(Storage.TYPE_OF_SOURCE)) {
                throw new IllegalArgumentException("Type of address is unknown");
            }

            this.loadImpl(address);
            boolean check = this.addToMedia();
        }
    }

    private boolean addToMedia() {
        Storage storage = Storage.getInstance();

        if (this.o instanceof LicensedAudioVideo) {
            storage.addMedia((LicensedAudioVideo) o);
            return true;
        }

        if (this.o instanceof InteractiveVideo) {
            storage.addMedia((InteractiveVideo) o);
            return true;
        }

        if (this.o instanceof AudioVideo) {
            storage.addMedia((AudioVideo) o);
            return true;
        }

        if (this.o instanceof LicensedVideo) {
            storage.addMedia((LicensedVideo) o);
            return true;
        }

        if (this.o instanceof LicensedAudio) {
            storage.addMedia((LicensedAudio) o);
            return true;
        }

        if (this.o instanceof Video) {
            storage.addMedia((Video) o);
            return true;
        }

        if (this.o instanceof Audio) {
            storage.addMedia((Audio) o);
            return true;
        }

        return false;
    }

    private void loadImpl(String address) {
        /*
         * https://stackoverflow.com/questions/22375924/how-the-randomaccessfile-class-returns-bytes-with-randomaccessfile-read-method
         */
        try (RandomAccessFile raf = new RandomAccessFile(FILE, "r")) {
            byte[] bytes = new byte[(int) raf.length()];
            long seeker = raf.readLong();
            raf.seek(seeker);
            raf.read(bytes);
            this.o = this.randomAccessHandle.convertFromBytes(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveImpl(String address) {

        //Speichern von listOfAddresses
        try (RandomAccessFile raf = new RandomAccessFile(FILE, "rw")) {
            if (!isAddressInCollection) {
                raf.seek(raf.length());
                raf.writeLong(raf.length());
                raf.write(this.randomAccessHandle.convertToBytes(address));
            } else {

            }

            this.seekNumber++;
            System.out.println("After SeekerNumber:" + this.seekNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
