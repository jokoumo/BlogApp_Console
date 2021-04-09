import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Blog {
    private final String stdFileNameArticles = "articles.xml";      // Standard Dateiname für gespeicherte Artikel
    private final String stdFileNameCategories = "categories.xml";  // Standart Dateiname für gespeicherte Kategorien
    private List<Article> articleList = new LinkedList<>();     // Liste zum Speichern der einzelnen Artikel
    private List<String> categoryList = new ArrayList<>();      // Liste zum Speichern der Kategorien

    // Standard Dateien importieren
    public Blog() {
        importLists(stdFileNameArticles, stdFileNameCategories);
    }

    public Blog(String fileArticles, String fileCategories) {
        importLists(fileArticles, fileCategories);
    }

    public List<Article> getArticleList() {
        return articleList;
    }
    public List<String> getCategoryList() {
        return categoryList;
    }

    // Vorhandene Artikel und Kategorien importieren bzw. neu erstellen
    public void importLists(String fileArticles, String fileCategories) {
        try {
            XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileArticles)));
            articleList = (List<Article>) dec.readObject();
            dec.close();
        } catch (FileNotFoundException e) {
            exportList(fileArticles, this.articleList);
            System.out.println("INFO:\nNeue Datei \"" + fileArticles + "\" für Blogeinträge wurde angelegt.\n");
        } catch (ClassCastException e) {
            System.out.println("Fehler: " + e.getMessage());
        }

        try {
            XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileCategories)));
            categoryList = (List<String>) dec.readObject();
            dec.close();
        } catch (FileNotFoundException e) {
            categoryList.add("Familie");
            categoryList.add("Gesundheit");
            categoryList.add("Klatsch");
            categoryList.add("Politik");
            categoryList.add("Reisen");
            categoryList.add("Sport");
            categoryList.add("Technik");
            categoryList.add("Wirtschaft");
            categoryList.add("Wissenschaft");
            exportList(fileCategories, categoryList);
            System.out.println("INFO:\nDatei \"" + fileCategories + "\" neu angelegt. Standard-Kategorien hinzugefügt.\n");
        } catch (ClassCastException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    // Artikel oder Kategorien exportieren
    public <T> void exportList(String fileName, List<T> list) {
        try {
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream(fileName));
            encoder.writeObject(list);
            encoder.close();
        } catch (IOException e) {
            System.out.println("Fehler beim Export: " + e.getMessage());
        }
    }

    public void editCategories() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\nKategorien - Was möchtest du tun?\n" +
                    "1: Kategorie hinzufügen\n" +
                    "2: Kategorie löschen\n" +
                    "3: Kategorien anzeigen\n" +
                    "0: Zurück");

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Wie soll die neue Kategorie heißen?");
                    String newCat = scanner.nextLine();
                    if (!newCat.isEmpty()) {
                        categoryList.add(newCat);
                        Collections.sort(categoryList);
                        System.out.println("Kategorie \"" + newCat + "\" wurde hinzugefügt.");
                    } else
                        System.out.println("Keine Änderungen.");
                    break;
                case "2":
                    System.out.println("Welche Kategorie möchtest du löschen?");
                    for (int i = 1; i <= categoryList.size(); i++)
                        System.out.println(i + ": " + categoryList.get(i -1));
                    System.out.println("0: Abbrechen");
                    try {
                        int userInput = scanner.nextInt();
                        if(userInput != 0) {
                            categoryList.remove(userInput -1);
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
                    for (int i = 1; i <= categoryList.size(); i++)
                        System.out.println(i + ": " + categoryList.get(i -1));
                    System.out.println((categoryList.size() + 1) + ": Sonstiges");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }

    public void searchArticle() {
        System.out.println("Nach welchem Stichwort(Titel) suchst du?");
        Scanner scanner = new Scanner(System.in);
        String keyword = scanner.nextLine();
        boolean foundArticle = false;
        // Alle Artikel auflisten, die den Suchbegriff im Titel haben und veröffentlicht sind
        for (Article article : articleList) {
            if (article.getTitle().toLowerCase().contains(keyword.toLowerCase()) && article.isPublished()) {
                if (!foundArticle)
                    System.out.println("\nSuchergebnisse:\n");
                System.out.println(article);
                foundArticle = true;
            }
        }
        if(!foundArticle)
            System.out.println("\nEs gab keinen Treffer für deine Suche.\n");
    }

    public void editDraft() {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;

        while(true) {
            System.out.println("\nEntwürfe - Was möchtest du tun?\n" +
                    "1: Entwürfe anzeigen\n" +
                    "2: Entwurf veröffentlichen\n" +
                    "3: Entwurf löschen\n" +
                    "0: Zurück");

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("\n-Gespeicherte Entwürfe-\n");
                    for (int i = 0; i < this.getArticleList().size(); i++) {
                        if(!this.articleList.get(i).isPublished())
                            System.out.println("Nr.: " + (i + 1) + "\n" + this.articleList.get(i));
                    }
                    break;
                case "2":
                    while(true) {
                        System.out.println("\nWelcher Entwurf soll veröffentlicht werden? Nr. eingeben:");
                        try {
                            userInput = scanner.nextInt() -1;
                            if (!this.articleList.get(userInput).isPublished()) {
                                this.articleList.get(userInput).setPublished(true);
                                this.articleList.get(userInput).setDate(Date.valueOf(LocalDate.now()) +
                                        " " + Time.valueOf(LocalTime.now()));
                                System.out.println("Entwurf \"" + this.articleList.get(userInput).getTitle() +
                                        "\" wurde veröffentlicht.");
                            } else
                                System.out.println("Artikel \"" + this.articleList.get(userInput).getTitle() +
                                        "\" wurde bereits veröffentlicht.");
                            break;
                        } catch (InputMismatchException | IndexOutOfBoundsException e) {
                            System.out.println("Ungültige Eingabe.");
                        } finally {
                            scanner.nextLine();
                        }
                    }
                    break;
               case "3":
                    while(true) {
                        System.out.println("\nWelchen Entwurf willst du löschen? Nr. eingeben:");
                        try {
                            userInput = scanner.nextInt() -1;
                            if (!this.articleList.get(userInput).isPublished()) {
                                this.articleList.remove(userInput);
                                System.out.println("Entwurf \"" + this.articleList.get(userInput).getTitle() +
                                        "\" wurde gelöscht.");
                            } else
                                System.out.println("Artikel \"" + this.articleList.get(userInput).getTitle() +
                                        "\" wurde bereits veröffentlicht.");
                            break;
                        } catch (InputMismatchException | IndexOutOfBoundsException e) {
                            System.out.println("Ungültige Eingabe.");
                        } finally {
                            scanner.nextLine();
                        }
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }

    public void saveFiles() {
        exportList(stdFileNameArticles, articleList);
        exportList(stdFileNameCategories, categoryList);
    }

    public void saveFiles(String fileArticles, String fileCategories) {
        exportList(fileArticles, articleList);
        exportList(fileCategories, categoryList);
    }
}
