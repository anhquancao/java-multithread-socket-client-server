package client;

import actions.RequestRentalAction;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by caoquan on 4/5/17.
 */
public class ClientApplication {
    private TenantClient client;
    private Scanner scanner;
    private UserInterface userInterface;

    public ClientApplication() {
        scanner = new Scanner(System.in);
        client = new TenantClient();
        userInterface = new UserInterface();
    }

    public void requestAllAvailableRentals() {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.ALL);
        client.doAction(requestRentalAction);
    }

    public int getInputChoice() {
        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException ex) {
            userInterface.showDelimiter();
            System.out.println("Your Input is not valid");
            scanner.next();
        }
        return choice;
    }


    public void run() {
        while (true) {
            userInterface.showMainMenu();
            int choice = getInputChoice();
            userInterface.showDelimiter();
            switch (choice) {
                case 1:
                    int check = 1;
                    while (check == 1) {
                        userInterface.showTenantMenu();
                        int tenantChoice = getInputChoice();
                        switch (tenantChoice) {
                            case 1:
                                userInterface.showCriteriaMenu();
                                int criteriaChoice = getInputChoice();
                                switch (criteriaChoice) {
                                    case 1:
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                        break;
                                }
                                this.requestAllAvailableRentals();
                                break;
                            case 2:
                                break;
                            case 3:
                                check = 0;
                                break;
                        }
                    }

                    break;
                case 2:
                    System.out.println("Renter");
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Your choice is not valid");
                    break;
            }

        }
    }


    public static void main(String[] args) {
        ClientApplication clientApplication = new ClientApplication();
        clientApplication.run();
//        clientApplication.requestAllAvailableRentals();
//        clientApplication.requestAllRentals();

    }
}
