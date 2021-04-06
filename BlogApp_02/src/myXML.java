import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.LinkedList;

public class myXML {

    public static <T> void exportList (LinkedList<T> list) {
        try {
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream("articles.xml"));
            encoder.writeObject(list);
            encoder.close();
        } catch (IOException e) {
            System.out.println("Fehler beim Export: " + e.getMessage());
        }
    }

    public static <T> LinkedList<T> importList() {
        LinkedList<T> list = null;
        try {
            XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream("articles.xml")));
            list = (LinkedList<T>) dec.readObject();
            dec.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fehler: " + e.getMessage());
        } catch (ClassCastException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
        return list;
    }
}
