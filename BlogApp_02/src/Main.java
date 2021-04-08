import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        final String FILE_NAME_ARTICLES = "articles.xml";
        final String FILE_NAME_CATEGORIES = "categories.xml";

        List<Article> articleList = new LinkedList<Article>();      //Speichert die Blogartikel
        List<String> categoryList = new ArrayList<String>();        //Speichert die möglichen Kategorien

        Scanner scanner = new Scanner(System.in);

        System.out.println("~~Deine BlogApp~~");

        // Vorhandene Blogartikel aus Datei importieren
        try {
            articleList = importList(FILE_NAME_ARTICLES);
        } catch (FileNotFoundException e) {                         //Datei für Blogeinträge neu anlegen
            exportList(FILE_NAME_ARTICLES, articleList);
            System.out.println("INFO:\nNeue Datei für Blogeinträge wurde angelegt.\n");
        }

        // Kategorien aus Datei importieren
        try {
            categoryList = importList(FILE_NAME_CATEGORIES);
        } catch (FileNotFoundException e) {                         //Datei für Kategorien neu anlegen
            categoryList.add("Familie");
            categoryList.add("Gesundheit");
            categoryList.add("Klatsch");
            categoryList.add("Politik");
            categoryList.add("Reisen");
            categoryList.add("Sport");
            categoryList.add("Technik");
            categoryList.add("Wirtschaft");
            categoryList.add("Wissenschaft");
            categoryList.add("Sonstiges");
            exportList(FILE_NAME_CATEGORIES, categoryList);
            System.out.println("INFO:\nListe mit Kategorien neu angelegt. Standard-Einträge hinzugefügt.");
        }

        while (true) {
            // Hauptmenü
            System.out.println(
                    "\nWas möchtest du tun? Gib eine Nummer ein:\n" +
                    "1: Blogeinträge anzeigen\n" +
                    "2: Neuer Blogeintrag\n" +
                    "3: Blogeintrag suchen\n" +
                    "4: Blogeintrag löschen\n" +
                    "5: Kategorien bearbeiten\n" +
                    "0: Beenden");

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("\n-Aktuelle Blogeinträge-\n");
                    for (int i = 1; i <= articleList.size(); i++) {
                        System.out.println("Nr.: " + i + "\n" + articleList.get(i -1));
                    }
                    break;
                case "2":
                    articleList.add(Article.newArticle(categoryList));
                    break;
                case "3":
                    System.out.println("Nach welchem Stichwort(Titel) suchst du?");
                    Article.searchArticle(articleList);
                    break;
                case "4":
                    System.out.println("Welcher Blogeintrag soll gelöscht werden? Nr. eingeben:");
                    try {
                        int userInput = scanner.nextInt() -1;
                        System.out.println("\nBlogeintrag \"" + articleList.get(userInput).getTitle() + "\" wurde gelöscht.\n");
                        articleList.remove(userInput);
                    } catch (Exception e) {
                        System.out.println("\nFehler: Ein Blogeintrag mit dieser Nummer existiert nicht.\n");
                    } finally {
                        scanner.nextLine();
                    }
                    break;
                case "5":
                    editCategories(categoryList);
                    break;
                default:
                    // Programm beenden
                    System.out.println("Änderungen speichern? (j/n)");
                    if(scanner.nextLine().equalsIgnoreCase("j")) {
                        exportList(FILE_NAME_ARTICLES, articleList);
                        exportList(FILE_NAME_CATEGORIES, categoryList);
                    }
                    return;
            }
        }
    }

    private static void editCategories (List<String> list) {

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\nKategorien - Was möchtest du tun?\n" +
                    "1: Kategorie hinzufügen\n" +
                    "2: Kategorie löschen\n" +
                    "3: Kategorien anzeigen\n" +
                    "0: Abbrechen");

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Wie soll die neue Kategorie heißen?");
                    String newCat = scanner.nextLine();
                    if (!newCat.isEmpty()) {
                        list.add(newCat);
                        System.out.println("Kategorie \"" + newCat + "\" wurde hinzugefügt.");
                    } else
                        System.out.println("Keine Änderungen.");
                    break;
                case "2":
                    System.out.println("Welche Kategorie möchtest du löschen?");
                    for (int i = 1; i <= list.size(); i++)
                        System.out.println(i + ": " + list.get(i -1));
                    System.out.println("0: Abbrechen");
                    try {
                        int userInput = scanner.nextInt();
                        if(userInput != 0) {
                            list.remove(userInput -1);
                            System.out.println("\nKategorie wurde entfernet.");
                        } else
                            System.out.println("\nKeine Änderungen.");
                    } catch (InputMismatchException e) {
                        System.out.println("Fehler: Ungültiger Wert.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Fehler: Hier gibt es keine Kategorie.");
                    } finally {
                        scanner.nextLine();
                    }
                    break;
                case "3":
                    System.out.println("\nKategorien:");
                    for (int i = 1; i <= list.size(); i++)
                        System.out.println(i + ": " + list.get(i -1));
                    break;
                default:
                    return;
            }
        }
    }

    private static <T> void exportList (String file, List<T> list) {
        try {
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file));
            encoder.writeObject(list);
            encoder.close();
        } catch (IOException e) {
            System.out.println("Fehler beim Export: " + e.getMessage());
        }
    }

    private static <T> List<T> importList(String file) throws FileNotFoundException {
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
