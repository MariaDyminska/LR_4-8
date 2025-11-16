package system;
import java.io.*;
public class DataFileManager {
    public static void saveObject(Object obj, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(obj);
        }
    }
    public static Object loadObject(String filename) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        }
    }
}
