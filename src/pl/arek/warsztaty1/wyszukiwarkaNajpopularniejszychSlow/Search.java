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
    private static void saveFileLongerThenThree(String path, ArrayList<String[]> text) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(path);
        for (int i = 0; i < text.size(); i++) {
            for (int j = 0; j < text.get(i).length; j++) {
                if (text.get(i)[j].length() >= 3) {
                    printWriter.append(text.get(i)[j].toLowerCase() + "\n");
                }

            }
        }
        printWriter.close();
    }
    private static void saveFile(String url,ArrayList<String> filteredPopularWords) throws FileNotFoundException {
        PrintWriter printWriter  = new PrintWriter(url);
        for (int i=0;i<filteredPopularWords.size();i++){
            printWriter.append(filteredPopularWords.get(i));
        }
        printWriter.close();
    }

    private static void readFile(String url, ArrayList<String> words) throws FileNotFoundException {
        File file = new File(url);
        Scanner scanner = null;

            scanner = new Scanner(file);

            System.out.println("Problem z otwarciem pliku");


        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine() + "\n");
        }
    }

    private static void excludeWords(ArrayList<String> popularWords,String[] excludedWords){
        for (int i=0; i< excludedWords.length;i++){
            popularWords.remove(excludedWords[i]+"\n");
        }
    }

    static void selectWords(String url, String cssQuery) {
        Connection connect = Jsoup.connect(url);
        ArrayList<String> popularWords = new ArrayList<>();
        ArrayList<String[]> text = new ArrayList<>();
        String[] excludedWords = {"oraz", "ponieważ", "gdyż", "jakby", "także", "też", "nie", "tak","pożar","dni"};
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            for (Element elem : links) {
                text.add(elem.text().replaceAll("[^\\wą-żĄ-ŻóÓ ]", " ").split(" "));
            }
            saveFileLongerThenThree("popular_words.txt", text);
            readFile("popular_words.txt", popularWords);
            excludeWords(popularWords,excludedWords);
            System.out.println(popularWords.get(0));
            saveFile("filtered_popular_words.txt",popularWords);

        }
        catch (FileNotFoundException e){
            System.out.println("Problem z otwarciem pliku");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        selectWords("http://www.onet.pl/", "span.title");
    }
}
