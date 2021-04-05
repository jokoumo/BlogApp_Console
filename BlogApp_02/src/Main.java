import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.LinkedList;

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

        LinkedList<Article> listArticle = new LinkedList<>();
        listArticle.add(new Article("Max", "Sonstiges", "Leute Heute", "jsajg kfj kfivkh ivihv\nkdabfk akdbgk akbka\nkdbkdja lafbkeab.", false));
        listArticle.add(new Article("Bert", "Lifestyle", "Eine Schrift", "nadgljbae osapü\nasbka adgkbx üsdo\ncvmdsk apois epej.", true));
        listArticle.add(new Article("Heidi", "Sport", "Nochmal", "dkgbaek xpaoj qpo\nsokasa dkdva ldiaefa\nyxlmq dyvpaekn aeofn.", false));

        System.out.println(listArticle);

        listArticle.add(Article.newArticle());

        exportList(listArticle);

        LinkedList<Article> newList = importList();

        System.out.println("\nImportierte Liste:\n" + newList);
    }
}
