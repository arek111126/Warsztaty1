package pl.arek.warsztaty1.kostkaDoGry;

import java.util.Random;

public class Main1 {
    public static void main(String[] args) {
        System.out.println(cube("3D10-3"));
    }

    static int cube(String string) {
        String[] splittedInstruction = string.split("D");
        String cuttedString = string.substring(string.indexOf("D") + 1, string.length());
        String[] splittedInstruction2 = cuttedString.split("[+-]+");

        int x = splittedInstruction[0].isEmpty() ? 1 : Integer.parseInt(splittedInstruction[0]);
        int y = Integer.parseInt(splittedInstruction2[0]);
        int z = splittedInstruction2.length != 2 ? 0 : Integer.parseInt(splittedInstruction2[1]);

        Random random = new Random();
        int range = 0;
        int pom = 0;
        for (int i = 0; i < x; i++) {
            pom = random.nextInt(y) + 1;
            range += pom;
            System.out.println("wylosowałem " + pom);
        }

        return string.indexOf("-")== -1 ? range+z: range-z;
    }
}
