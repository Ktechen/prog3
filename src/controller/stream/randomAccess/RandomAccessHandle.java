package controller.stream.randomAccess;

import modell.data.storage.Storage;
import modell.mediaDB.MediaContent;

import java.io.*;
import java.util.List;

public class RandomAccessHandle {

    public Object convertFromBytes(byte[] bytes) {
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
                if(in != null)
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public byte[] convertToBytes(String address) {
        List<MediaContent> list = Storage.getInstance().getMedia();
        MediaContent content = null;

        for (MediaContent m : list) {
            if (m.getAddress().equals(address)) {
                content = m;
                break;
            }
        }

        byte[] bytes = new byte[2048];

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            oos.writeObject(content);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }
}
