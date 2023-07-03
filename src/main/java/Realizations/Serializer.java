package Realizations;

import java.io.*;
import java.util.Base64;

public class Serializer {
    public static <T> String Serialize(T toServerObject) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(toServerObject);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public static <T extends Serializable> T Deserialize(String fromSenderString) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(fromSenderString);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        T serverdata = (T) (ois.readObject());
        ois.close();
        return serverdata;
    }
}
