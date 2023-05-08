package control;

import java.util.Scanner;

public class Validation 
{
    public static String getValue(Scanner sc, String msg)
    {
        System.out.println(msg);
        return sc.nextLine();
    }

    public static String getAndCheckValue(Scanner sc, String msg, String regex)
    {
        System.out.println(msg);
        while(!sc.hasNext(regex))
        {
            System.out.println("Invalid Input !");
            sc.next();
        }
        return sc.nextLine();
    }

    public static double getAndCheckScore(Scanner sc, String msg)
    {
        double number;
        do {
            System.out.println(msg);
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next();
            }
            number = sc.nextInt();
        } while (number <= 0);
        return number;
    }

    public static int getAndCheckInt(Scanner sc, String msg)
    {
        int number;
        do {
            System.out.println(msg);
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next();
            }
            number = sc.nextInt();
        } while (number < 0);
        return number;
    }

    public static int getAndCheckIntBorder(Scanner sc, String msg, int max)
    {
        int number;
        do {
            System.out.println(msg);
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.nextLine();
            }
            number = sc.nextInt();
        } while (number <= 0 || number > max);
        return number;
    }
}
