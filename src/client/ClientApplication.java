package client;

import actions.RequestApartmentAction;
import actions.RequestPersonAction;
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

            // show main menu
            // Are you a Tenant or Renter
            // 1. Tenant
            // 2. Renter
            // 3. Exit
            userInterface.showMainMenu();
            int choice = getInputChoice();
            userInterface.showDelimiter();
            switch (choice) {
                case 1:
                    // choose option 1. Tenant
                    int check = 1;
                    while (check == 1) {

                        // show tenant menu
                        userInterface.showTenantMenu();
                        int tenantChoice = getInputChoice();
                        switch (tenantChoice) {
                            case 1:
                                int criteriaCheck = 1;
                                while (criteriaCheck == 1) {
                                    // show criteria menu
                                    userInterface.showCriteriaMenu();
                                    int criteriaChoice = getInputChoice();
                                    switch (criteriaChoice) {
                                        case 1:
                                            // 1.Request all available rental
                                            client.requestAllAvailableRentals();
                                            break;
                                        case 2:
                                            break;
                                        case 3:
                                            break;
                                        case 4:
                                            criteriaCheck = 0;
                                            break;
                                    }
                                }


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

//        clientApplication.run();

//        clientApplication.requestAllAvailableRentals();
//        clientApplication.requestAllRentals();
//        clientApplication.requestAllBelow(1600);
//            clientApplication.requestAllNumRoom(2);
//        clientApplication.requestApartmentOfRenter(2);
//        clientApplication.requestListAllTenant();

        clientApplication.requestTenantOfRental(1);

    }
}
