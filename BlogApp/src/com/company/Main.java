/*
Blog (LinkedList)   - Attribute: Artikel (class)
                    - Methoden: Sortieren(Datum, Beliebtheit), Filtern(Kategorien, Autor), Suchen, Blogartikel löschen,

Blogartikel     - Attribute:    Kategorie (String),
                                Datum (String),
                                Autor (String),
                                Veröffentlicht (bool),
                                Titel (String),
                                Body (String),
                                Titelbild (),
                                In Bearbeitung (bool),
                                Kommentare (ArrayList),
                                Favorit (bool)
                - Methoden: Veröffentlichen, Verbergen, Bild einfügen, Bild löschen, Bearbeiten

Oberfläche      - Artikelübersicht: Artikelliste (LinkedList), Suche, Sortieren, Filtern
                - Artikel: Datum, Autor, Titel, Text, Kommentar schreiben, Kommentar absenden

 */

package com.company;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Article article = new Article();
        article.setDate();
        System.out.println(article.getDate());
    }
}
