import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Article {
    private static ArrayList<String> categoryList = new ArrayList<>();
    private String author;
    private String date;
    private String category;
    private String title;
    private String content;
    private boolean published;

    public Article() {
        if (Article.categoryList.isEmpty()) {
            Article.categoryList.add("Familie");
            Article.categoryList.add("Sport");
            Article.categoryList.add("Finanzen");
            Article.categoryList.add("Reisen");
            Article.categoryList.add("Wohnen");
            Article.categoryList.add("Tiere");
            Article.categoryList.add("Sonstiges");
        }
    }

    public Article(String author, String category, String title, String content, boolean published) {
        this.author = author;
        this.date = Date.valueOf(LocalDate.now()).toString() + " " + Time.valueOf(LocalTime.now()).toString();
        this.category = category;
        this.title = title;
        this.content = content;
        this.published = published;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public static ArrayList<String> getCategoryList() {
        return categoryList;
    }

    public static void setCategoryList(ArrayList<String> categoryList) {
        Article.categoryList = categoryList;
    }

    public static void addCategoryList(String category) {
        Article.categoryList.add(category);
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isPublished() {
        return published;
    }

    @Override
    public String toString() {
        return ("Titel: " + title + '\n' +
                "von: " + author + " am " + date + "\n" +
                "Kategorie: " + category + "\n\n" +
                content + (published == false ? "\n\n[Nicht veröffentlicht]":"\n\n[Veröffentlicht]") +
                "\n--------------------");
    }

    public static Article newArticle() {
        Scanner scanner = new Scanner (System.in);
        Article article = new Article();

        System.out.print("-NEUER BLOGEINTRAG-\n");
        System.out.print("Wer ist der Autor? ");
        article.setAuthor(scanner.nextLine());
        System.out.print("Wie lautet der Titel? ");
        article.setTitle(scanner.nextLine());
        System.out.print("Was ist der Inhalt? ");
        article.setContent(scanner.nextLine());
        System.out.println("Wähle eine Kategorie:");

        while (true) {
            try {
                for (int i = 1; i < Article.categoryList.size(); i++)
                    System.out.println(i + ": " + Article.categoryList.get(i -1));
                article.setCategory(Article.getCategoryList().get(scanner.nextInt() -1));
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Bitte eine gültige Zahl eingeben.");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Bitte einen gültigen Wert eingeben.");
                scanner.nextLine();
            }
        }

        System.out.println("Beitrag veröffentlichen? (j/n)");
        if (scanner.nextLine().toLowerCase() == "j") {
            article.setPublished(true);
            System.out.println("Dein Beitrag wurde veröffentlicht.");
        } else {
            article.setPublished(false);
            System.out.println("Dein Beitrag wurde als Entwurf gespeichert.");
        }

        article.setDate(Date.valueOf(LocalDate.now()).toString() + " " + Time.valueOf(LocalTime.now()).toString());

        return article;
    }
}
