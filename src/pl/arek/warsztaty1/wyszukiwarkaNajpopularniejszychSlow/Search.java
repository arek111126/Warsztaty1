package pl.arek.warsztaty1.wyszukiwarkaNajpopularniejszychSlow;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Search {
    public static void main(String[] args) {
        Connection connect = Jsoup.connect("http://www.onet.pl/");
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            for (Element elem : links) {
                System.out.println(elem.text());
            }} catch (IOException e) {
            e.printStackTrace();}
    }
}
