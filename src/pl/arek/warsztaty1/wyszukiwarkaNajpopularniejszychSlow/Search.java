package pl.arek.warsztaty1.wyszukiwarkaNajpopularniejszychSlow;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Search {
    private static void saveFileLongerThenThree(String path,ArrayList<String[]> text ) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(path);
        for (int i=0; i<text.size();i++){
            for (int j=0; j<text.get(i).length;j++){
                if(text.get(i)[j].length()>3 ){
                    printWriter.append(text.get(i)[j]+"\n");
                }

            }
        }
        printWriter.close();
    }

    static void popularWords(String url,String cssQuery){
        Connection connect = Jsoup.connect(url);
        ArrayList<String[]> text = new ArrayList<>();
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            for (Element elem : links) {
                text.add(elem.text().replaceAll("[^\\wą-żĄ-ŻóÓ ]"," ").split(" "));
                saveFileLongerThenThree("popular_words.txt",text);
            }
        } catch (IOException e) {
            e.printStackTrace();}
    }
    public static void main(String[] args) {
        popularWords("http://www.onet.pl/","span.title" );
    }
}
