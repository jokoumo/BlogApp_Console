import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Article {
    private String author;
    private String date;
    private String category;
    private String title;
    private String content;
    private boolean published;

    public Article() {}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return ("Titel: " + title + '\n' +
                "von: " + author + " am " + date + "\n" +
                "Kategorie: " + category + "\n\n" +
                content + (!published ? "\n\n[Nicht veröffentlicht]":"\n\n[Veröffentlicht]") +
                "\n--------------------");
    }

    public static Article newArticle(List<String> categoryList) {
        Scanner scanner = new Scanner (System.in);
        Article article = new Article();

        System.out.print("-NEUER BLOGEINTRAG-\n");

        System.out.print("Wer ist der Autor? ");
        article.setAuthor(scanner.nextLine());
        if(article.getAuthor().isEmpty())
            article.setAuthor("Unbekannt");

        System.out.print("Wie lautet der Titel? ");
        article.setTitle(scanner.nextLine());
        if(article.getTitle().isEmpty())
            article.setTitle("Kein Titel");

        while(article.getContent() == null) {
            System.out.print("\nWillst du den Inhalt...\n" +
                    "1: selbst schreiben?\n" +
                    "2: aus einer Datei (*.txt) importieren?\n");

            switch (scanner.nextLine()) {
                case "1":   // Inhalt per Hand eintragen
                    System.out.println("Leg los:");
                    article.setContent(scanner.nextLine());
                    if (article.getContent().isEmpty())
                        article.setContent("Hier könnte ihre Werbung stehen.");
                    break;
                case "2":   // Inhalt aus einer .txt Datei lesen
                    String temp = "";
                    System.out.println("Gib den Dateipfad ein (z.B.: C\\User\\Documents\\Test.txt):");
                    try {
                        Scanner readFile = new Scanner(new File(scanner.nextLine()));
                        while (readFile.hasNextLine())
                            temp += (readFile.nextLine()) + (readFile.hasNextLine() ? '\n' : "");
                        article.setContent(temp);
                    } catch (FileNotFoundException e) {
                        System.out.println("Fehler: Keine gültige Datei gefunden.");
                    }
                    break;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }

        System.out.println("\nWähle eine Kategorie:");
        while (true) {
            for (int i = 1; i <= categoryList.size(); i++)    // Kategorien auflisten
                System.out.println(i + ": " + categoryList.get(i -1));
            System.out.println((categoryList.size() + 1) + ": Sonstiges");

            try {
                int userInput = scanner.nextInt() -1;
                if(userInput == categoryList.size())
                    article.setCategory("Sonstiges");
                else
                    article.setCategory(categoryList.get(userInput));
                break;
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println("\nUngültige Eingabe. Nochmal:\n");
            } finally {
                scanner.nextLine();
            }
        }

        System.out.println("\nBeitrag veröffentlichen? (j/n)");
        if (scanner.nextLine().equalsIgnoreCase("j")) {
            article.setPublished(true);
            System.out.println("\nDein Beitrag wurde veröffentlicht.");
        } else {
            article.setPublished(false);
            System.out.println("\nDein Beitrag wurde als Entwurf gespeichert.");
        }

        article.setDate(Date.valueOf(LocalDate.now()) + " " + Time.valueOf(LocalTime.now()));

        return article;
    }
}
