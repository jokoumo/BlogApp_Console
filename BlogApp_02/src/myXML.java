import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;

public class myXML {

    public static <T> void exportList (String file, List<T> list) {
        try {
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file));
            encoder.writeObject(list);
            encoder.close();
        } catch (IOException e) {
            System.out.println("Fehler beim Export: " + e.getMessage());
        }
    }

    public static <T> List<T> importList(String file) throws FileNotFoundException {
        List<T> list = null;
        try {
            XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
            list = (List<T>) dec.readObject();
            dec.close();
        } catch (ClassCastException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
        return list;
    }
}
