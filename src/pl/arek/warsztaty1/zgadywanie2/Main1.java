package pl.arek.warsztaty1.zgadywanie2;

import java.util.Random;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        System.out.println("Zgadywanie liczb");
        System.out.println("================");
        int min=0 ,max=1000;

        System.out.println("Podaj liczbę a ja ją zgadne w 10 krokach");
        int number = gueasNumber();
        int guess = ((max-min) /2) + min;
        System.out.println("Zgaduję "+ guess);
        int count=0;
        count++;
        while(isCorrect(number,guess)){
            String hint =getHint(number,guess);
            System.out.println(hint);
            count++;

            if (guess > number){
                max = guess;
            }else if (guess < number){
                min = guess;
            }
            guess  = ((max-min)/2) +min;
            System.out.println(guess);
        }
        if (number== guess){
            System.out.println("zgadles");
            System.out.println("Ilosc prób: "+count);
            if (count>10){
                System.out.println("Ilośc prób większa niż 10 kłamałeś !!");
            }
        }

    }
    //Gracz 1 - zgadujący
    static int gueasNumber(){
        Scanner scanner = new Scanner(System.in);
        int guess= scanner.nextInt();
        return guess;
    }
    //Grcz 2 - losujący
    static int drawNumber() {
        Random random = new Random();
        return random.nextInt(1000) + 0;
    }
    static  boolean isCorrect(int number, int guess){
        return guess != number;
    }
    static String getHint(int number, int guess){
        if (guess < number){
            return "Podana liczba jest zamala";
        }else if (guess >number){
            return "Podana liczba jest za duża";
        }
        return "rowna";
    }
}
