package controller.stream.randomAccess;

import controller.storage.Storage;
import modell.mediaDB.MediaContent;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomAccessHandle {

    Object convertFromBytes(byte[] bytes) {
        ObjectInputStream in = null;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes)) {
            //Catch seeker number
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            return o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    byte[] convertToBytes(String address) {
        List<MediaContent> list = Storage.getInstance().getMedia();
        MediaContent content = null;

        for (MediaContent m : list) {
            if (m.getAddress().equals(address)) {
                content = m;
                break;
            }
        }

        return toByte(content);
    }

    byte[] toByte(Object o) {
        byte[] bytes = new byte[2048];
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            oos.writeObject(o);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    void encoderToXML(String filename, Map<Long, String> map) {
        try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(filename))) {

            xmlEncoder.writeObject(map);
            xmlEncoder.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    HashMap<Long, String> decoderToXML(String filename) throws IOException {

        HashMap<Long, String> o = null;
        XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(filename));
        o = (HashMap<Long, String>) xmlDecoder.readObject();

        return o;
    }
}
