package pl.arek.warsztaty1.wyszukiwarkaNajpopularniejszychSlow;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Search {
    private static void saveFileLongerThenThree(String path, ArrayList<String[]> text) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < text.size(); i++) {
            for (int j = 0; j < text.get(i).length; j++) {
                if (text.get(i)[j].length() >= 3) {
                    printWriter.append(text.get(i)[j].toLowerCase() + "\n");
                }

            }
        }
        printWriter.close();
    }

    private static void saveFile(String url, ArrayList<String> filteredPopularWords) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(url);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < filteredPopularWords.size(); i++) {
            printWriter.append(filteredPopularWords.get(i));
        }
        printWriter.close();
    }

    private static void readFile(String url, ArrayList<String> words) {
        File file = new File(url);
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine() + "\n");
        }
    }

    private static void excludeWords(ArrayList<String> popularWords, String[] excludedWords) {
        for (int i = 0; i < excludedWords.length; i++) {
            popularWords.remove(excludedWords[i] + "\n");
        }
    }

    static void selectWords(ArrayList<String> urls, String cssQuery) {
        Connection connect;
        Document document;
        Elements links;
        ArrayList<String> popularWords = new ArrayList<>();
        ArrayList<String[]> text = new ArrayList<>();
        String[] excludedWords = {"oraz", "ponieważ", "gdyż", "jakby", "także", "też", "nie", "tak"};
        try {
            for (int i = 0; i < urls.size(); i++) {
                connect = Jsoup.connect(urls.get(i));
                document = connect.get();
                links = document.select(cssQuery);

                for (Element elem : links) {
                    text.add(elem.text().replaceAll("[^\\wą-żĄ-ŻóÓ ]", " ").split(" "));
                }

            }

            saveFileLongerThenThree("popular_words.txt", text);
            readFile("popular_words.txt", popularWords);
            excludeWords(popularWords, excludedWords);
            saveFile("filtered_popular_words.txt", popularWords);

        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        ArrayList<String> urls = new ArrayList<>();

        urls.add("https://www.wp.pl/");
        urls.add("http://onet.pl/");
        urls.add("https://www.audanet.pl/cms/pl/web/ax-pl/home/");

        selectWords(urls, "span");
    }
}
