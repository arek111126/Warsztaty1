package pl.arek.warsztaty1.zgadywanie1;

import java.util.Random;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        System.out.println("Zgadywanie liczb");
        System.out.println("================");
        //Losujemy liczbe 1-100
        Random random = new Random();
        int number = random.nextInt(100) + 1;
        int guess;
        int count=0;

        guess= gueasnNumber();
        count++;
        while(isCorrect(number,guess)){
            String hint =getHint(number,guess);
            System.out.println(hint);
            guess= gueasnNumber();
            count++;
        }
        if (number== guess){
            System.out.println("zgadles");
            System.out.println("Ilosc prób: "+count);
        }

    }
    //Gracz 1 - zgadujący
    static int gueasnNumber(){
        Scanner scanner = new Scanner(System.in);
        int guess= scanner.nextInt();
        return guess;
    }
    //Grcz 2 - losujący
    static int drawNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }
    static  boolean isCorrect(int number, int guess){
        return guess != number;
    }
    static String getHint(int number, int guess){
        if (guess < number){
            return "Podana liczba jest zamala";
        }else{
            return "Podana liczba jest za duża";
        }
    }
}
