package client;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by caoquan on 4/13/17.
 */
public class ClientHelper {
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static String getStringInput(String prompt, String error, Scanner sc) {
        String input = null;
        try {
            System.out.print(prompt);
            input = sc.next();

        } catch (Exception e) {
            System.out.println(error);
        }
        return input;
    }

    public static int getIntegerInput(String prompt, String error, Scanner sc) {
        int input = 0;
        try {
            System.out.print(prompt);
            input = sc.nextInt();
        } catch (Exception e) {
            System.out.println(error);
        }
        return input;
    }

    public static int getInputChoice() {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Your Input is not valid");
            scanner.next();
        }
        return choice;
    }

}
