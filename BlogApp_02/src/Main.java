import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

//    private static void exportList(LinkedList<Article> list) {
//        try {
//            XMLEncoder encoder = new XMLEncoder(new FileOutputStream("articles.xml"));
//            encoder.writeObject(list);
//            encoder.close();
//        } catch (IOException e) {
//            System.out.println("Fehler beim Export: " + e.getMessage());
//        }
//    }
//
//    private static LinkedList<Article> importList() {
//        LinkedList<Article> list = null;
//        try {
//            XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream("articles.xml")));
//            list = (LinkedList<Article>) dec.readObject();
//            dec.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("Fehler: " + e.getMessage());
//        } catch (ClassCastException e) {
//            System.out.println("Fehler: " + e.getMessage());
//        }
//        return list;
//    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        LinkedList<Article> newList = myXML.importList();
        boolean menu = true;

        System.out.println("~~Deine BlogApp~~\nWas möchtest du tun?");

        while (true) {
            System.out.println(
                    "1: Blogeinträge anzeigen\n" +
                    "2: Neuer Blogeintrag\n" +
                    "3: Eintrag löschen\n" +
                    "4: Kategorien bearbeiten\n" +
                    "Andere Taste: Beenden");

            switch (scanner.nextLine()) {
                case "1":
                    for (int i = 1; i <= newList.size(); i++) {
                        System.out.println("Nr.: " + i + "\n" + newList.get(i-1));
                    }
                    break;
                case "2":
                    newList.add(Article.newArticle());
                    break;
                case "3":
                    try {
                        System.out.println("Welcher Blogeintrag soll gelöscht werden? Nr. eingeben:");
                        newList.remove(scanner.nextInt() - 1);
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Ein Artikel mit dieser Nummer existiert nicht.");
                    }
                    break;
                case "4":
                    System.out.println("Kategorie löschen oder hinzufügen");
                    break;
                default:
                    return;
            }
        }


    }
}
