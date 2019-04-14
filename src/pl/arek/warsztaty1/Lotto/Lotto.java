package pl.arek.warsztaty1.Lotto;

import java.util.*;

public class Lotto {
    public static void main(String[] args) {
        lotto();
    }
    static void lotto(){
        System.out.println("Witamy w Grze lotto, prosze o wytypowanie 6 liczb z zakresu 1-49");
        Scanner scanner = new Scanner(System.in);
        int numbers=0;
        int value;
        List<Integer> list= new ArrayList<>();
        List<Integer> randomList= new ArrayList<>();
        Random random = new Random();
        int randomValue;
        int count=0;
        System.out.println("Podaj 6 liczb (Nie mogą się powtarzać)");
        while (numbers < 6){
           try {
               System.out.println("Podaj liczbę");
               value = scanner.nextInt();

               if (value>=1 && value<=49 && (list.indexOf(value) ==-1)){
                   numbers++;
                   list.add(value);
               } else if (value<1 || value>49){
                   System.out.println("Liczba "+ value + " spoza zakresu!!");
               }

          }
           catch (InputMismatchException e){
               System.out.println("Błąd!! podaj liczbę całkowitą z zakresu 1-49 lub  nie jest to liczba!!!");
               scanner.next();
           }

        }
        Collections.sort(list);
        for (int i=0;i<6;i++){
            System.out.println(list.get(i));
        }

        System.out.println("Wylosowane liczby");
       while(count<6) {
           randomValue = random.nextInt(49) + 1;

           if (randomList.indexOf(randomValue) == -1) {
               randomList.add(randomValue);
               count++;
               System.out.println(randomValue);

           }
       }
           count =0;
           for (int i=0; i<6;i++){
               if (randomList.indexOf(list.get(i)) != -1){
                   count++;
               }
           }

               switch (count){
                   case 3:
                       System.out.println("trafiles 3!");
                       break;
                   case 4:
                       System.out.println("trafiles 4!");
                       break;
                   case 5:
                       System.out.println("trafiles 5!");
                       break;
                   case 6:
                       System.out.println("trafiles 6!");
                       break;
                       default:
                           System.out.println("Niczego nie trafiłeś");

           }






    }
}
