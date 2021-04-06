import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    private static void exportList(LinkedList<Article> list) throws IOException {
        FileOutputStream fos = new FileOutputStream("articles.xml");
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                System.out.println("Exception! :"+e.toString());
            }
        });
        encoder.writeObject(list);
        encoder.close();
        fos.close();
    }

    private static LinkedList<Article> importList () {
        LinkedList<Article> list = null;
        try {
            XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream("articles.xml")));
            list = (LinkedList<Article>) dec.readObject();
            dec.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ClassCastException e) {
            System.out.println("Error 2: " + e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        LinkedList<Article> newList = importList();
        Stream<Article> searchList = newList.stream().filter(a -> Objects.equals(a.getArticleID(), scanner.nextInt()));

        System.out.println("~~Deine BlogApp~~\nWas möchtest du tun?");
        System.out.println("1: Blogeinträge anzeigen\n" +
                "2: Neuer Blogeintrag\n" +
                "3: Eintrag löschen\n" +
                "4: Kategorien bearbeiten");

        switch(scanner.nextInt()) {
            case 1:
                System.out.println("\nImportierte Liste:\n" + newList);
                break;
            case 2:
                newList.add(Article.newArticle());
                break;
            case 3:
                System.out.println("Welcher Blogeintrag soll gelöscht werden? ID eingeben:");
                break;
            case 4:
                System.out.println("Kategorie löschen oder hinzufügen");
                break;
            default:
        }


    }
}
