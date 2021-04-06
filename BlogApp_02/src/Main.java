import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Article> blogList = new LinkedList<Article>();
        List<String> categoryList = new ArrayList<>();

        System.out.println("~~Deine BlogApp~~\n");

        try {
            blogList = myXML.importList("articles.xml");
        } catch (FileNotFoundException e) {                         //Datei für Blogeinträge anlegen
            myXML.exportList("articles.xml", blogList);
        }

        try {
            categoryList = myXML.importList("categories.xml");
        } catch (FileNotFoundException e) {                         //Datei für Kategorien anlegen
            categoryList.add("Familie");
            categoryList.add("Klatsch");
            categoryList.add("Politik");
            categoryList.add("Reisen");
            categoryList.add("Sport");
            categoryList.add("Wirtschaft");
            categoryList.add("Wissenschaft");
            categoryList.add("Sonstiges");
            myXML.exportList("categories.xml", categoryList);
            System.out.println("INFO:\nListe mit Kategorien neu angelegt. Standard-Einträge hinzugefügt.\n");
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Was möchtest du tun?");

        while (true) {
            System.out.println(
                    "1: Blogeinträge anzeigen\n" +
                    "2: Neuer Blogeintrag\n" +
                    "3: Eintrag löschen\n" +
                    "4: Kategorien bearbeiten\n" +
                    "Andere Taste: Beenden");

            switch (scanner.nextLine()) {
                case "1":
                    for (int i = 1; i <= blogList.size(); i++) {
                        System.out.println("Nr.: " + i + "\n" + blogList.get(i-1));
                    }
                    break;
                case "2":
                    blogList.add(Article.newArticle(categoryList));
                    break;
                case "3":
                    try {
                        System.out.println("Welcher Blogeintrag soll gelöscht werden? Nr. eingeben:");
                        blogList.remove(scanner.nextInt() - 1);
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
