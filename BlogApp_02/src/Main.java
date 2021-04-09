import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Blog blog = new Blog();
        Scanner scanner = new Scanner(System.in);
        System.out.println("~~Deine BlogApp~~");

        while (true) {
            System.out.println(
                    "\nHAUPTMENÜ - Was möchtest du tun?\n" +
                    "1: Blogeinträge anzeigen\n" +
                    "2: Neuer Blogeintrag\n" +
                    "3: Blogeintrag suchen\n" +
                    "4: Blogeintrag löschen\n" +
                    "5: Entwürfe bearbeiten\n" +
                    "6: Kategorien bearbeiten\n" +
                    "0: Beenden");

            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("\n-Aktuelle Blogeinträge-\n");
                    for (int i = 0; i < blog.getArticleList().size(); i++) {
                        if(blog.getArticleList().get(i).isPublished())  // Keine Entwürfe anzeigen
                            System.out.println("Nr.: " + (i + 1) + "\n" + blog.getArticleList().get(i));
                    }
                    break;
                case "2":
                    blog.getArticleList().add(Article.newArticle(blog.getCategoryList()));
                    break;
                case "3":
                    blog.searchArticle();
                    break;
                case "4":
                    System.out.println("Welcher Blogeintrag soll gelöscht werden? Nr. eingeben:");
                    try {
                        int userInput = scanner.nextInt() -1;
                        System.out.println("\nBlogeintrag \"" + blog.getArticleList().get(userInput).getTitle() +
                                "\" wurde gelöscht.");
                        blog.getArticleList().remove(userInput);
                    } catch (Exception e) {
                        System.out.println("\nFehler: Ein Blogeintrag mit dieser Nummer existiert nicht.");
                    } finally {
                        scanner.nextLine();
                    }
                    break;
                case "5":
                    blog.editDraft();
                    break;
                case "6":
                    blog.editCategories();
                    break;
                case "0":
                    // Programm beenden
                    System.out.println("Änderungen speichern? (j/n)");
                    if(scanner.nextLine().equalsIgnoreCase("j")) {
                        blog.saveFiles();
                    }
                    return;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }
    }
}
