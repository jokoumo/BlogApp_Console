import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Article {
    private static ArrayList<String> categoryList = new ArrayList<>();
    private int articleID = 0;
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
        this.articleID = (int) (System.currentTimeMillis()/1000);
        this.author = author;
        this.date = Date.valueOf(LocalDate.now()).toString() + " " + Time.valueOf(LocalTime.now()).toString();
        this.category = category;
        this.title = title;
        this.content = content;
        this.published = published;
    }

    public void setArticleID(Integer articleID) {
        if (this.articleID == 0)
            this.articleID = articleID;
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

    public Integer getArticleID() {
        return articleID;
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
        return ("\n[ID: " + articleID + "] " + title + '\n' +
                "von: " + author + " am " + date + "\n" +
                "Kategorie: " + category + "\n\n" +
                content + (published == false ? "\n\n[Nicht veröffentlicht]":"\n\n[Veröffentlicht]") +
                "\n--------------------");
    }

    public static Article newArticle() {
        Scanner scanner = new Scanner (System.in);
        Article article = new Article();

        System.out.print("-NEUER BLOGEINTRAG-\nWer ist der Autor? ");
        article.setAuthor(scanner.nextLine());
        System.out.print("Wie lautet der Titel? ");
        article.setTitle(scanner.nextLine());
        System.out.print("Was ist der Inhalt? ");
        article.setContent(scanner.nextLine());
        System.out.println("Wähle eine Kategorie:");
        for (int i = 1; i < Article.categoryList.size(); i++) {
            System.out.println(i + ": " + Article.categoryList.get(i -1));
        }
        article.setCategory(Article.getCategoryList().get(scanner.nextInt() -1));

        article.setArticleID((int) (System.currentTimeMillis()/1000));
        article.setDate(Date.valueOf(LocalDate.now()).toString() + " " + Time.valueOf(LocalTime.now()).toString());
        article.setPublished(true);


        return article;
    }
}
