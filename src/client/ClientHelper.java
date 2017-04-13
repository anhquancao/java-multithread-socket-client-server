package client;

import java.util.Scanner;

/**
 * Created by caoquan on 4/13/17.
 */
public class ClientHelper {
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
}
