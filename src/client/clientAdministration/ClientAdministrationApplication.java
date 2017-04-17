package client.clientAdministration;

import client.ClientHelper;
import client.UserInterface;

import java.util.Scanner;

/**
 * Created by dosontung on 4/16/17.
 */
public class ClientAdministrationApplication {
    private ClientAdministration clientAdministration;
    private Scanner scanner;
    private UserInterface userInterface;

    public ClientAdministrationApplication() {
        this.clientAdministration = new ClientAdministration();
        userInterface = new UserInterface();
        scanner = new Scanner(System.in);
    }

    public void run() {
        userInterface.showWelcomeAdmin();
        while (true) {
//            if (clientAdministration.adminLogin()){
//                System.out.println("Logged in");
//            }
            userInterface.showAdminMainMenu();
            int choice = ClientHelper.getInputChoice();
            switch (choice) {
                case 1:
                    clientAdministration.requestAllRental();
                    break;
                case 2:
                    int rentalId = ClientHelper.getIntegerInput("Please input an ID for rental you want to delete: ", "Your input was not an integer or not a valid ID", scanner);
                    if (rentalId != 0) {
                        clientAdministration.requestDeleteRental(rentalId);
                    }
                    break;
                case 3:
                    userInterface.showGoodBye();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Your choice in invalid");
                    break;
            }
        }
    }

    public static void main(String agrs[]) {
        ClientAdministrationApplication client = new ClientAdministrationApplication();
        client.run();
    }
}
