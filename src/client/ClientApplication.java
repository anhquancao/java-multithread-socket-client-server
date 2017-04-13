package client;

import utils.PersonType;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by caoquan on 4/5/17.
 */
public class ClientApplication {
    private TenantClient tenantClient;
    private RentalClient rentalClient;
    private PersonClient personClient;

    private Scanner scanner;
    private UserInterface userInterface;

    public ClientApplication() {
        scanner = new Scanner(System.in);
        tenantClient = new TenantClient();
        rentalClient = new RentalClient();
        userInterface = new UserInterface();
        personClient = new PersonClient();
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


    private void handleTenant() {
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
                                // 1. Request all available rental
                                tenantClient.requestAllAvailableRentals();
                                break;
                            case 2:
                                // 2. Request all available rental monthly below
                                tenantClient.requestAllMonthlyRentBelow();
                                break;
                            case 3:
                                // 3. All available rental monthly with number of rooms.
                                tenantClient.requestAllAvailableByNumRoom();
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
    }

    private void handleRenter() {
        int renterCheck = 1;
        while (renterCheck == 1) {
            userInterface.showRenterMenu();
            int renterChoice = getInputChoice();
            switch (renterChoice) {
                case 1:
                    rentalClient.requestApartmentOfRenter();
                    break;
                case 2:
                    rentalClient.requestTenantsOfRenter();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    renterCheck = 0;
                    break;
            }
        }
    }

    private void handleRegisterAccount() {
        System.out.println("Please");
    }

    public void run() {
        while (true) {
            userInterface.showMainMenu();
            int choice = getInputChoice();
            userInterface.showDelimiter();
            switch (choice) {
                case 1:
                    //1. Login
                    System.out.println("Login");
//                    handleTenant();
                    break;
                case 2:
                    //2. Register new account
                    if (personClient.createPerson()) {
                        if (ClientContext.getInstance().getLoggedInPerson()
                                .getPersonType().equals(PersonType.RENTER)) {
                            handleRenter();
                        } else {
                            handleTenant();
                        }
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Your choice is not valid");
                    break;
            }
//            switch (choice) {
//                case 1:
//                    // choose option 1. Tenant
//                    handleTenant();
//                    break;
//                case 2:
//                    // choose option 2. Renter
//                    handleRenter();
//                    break;
//                case 3:
//                    // Register account
//                    handleRegisterAccount();
//                    break;
//                case 4:
//                    System.exit(0);
//                    break;
//                default:
//                    System.out.println("Your choice is not valid");
//                    break;
//            }

        }
    }


    public static void main(String[] args) {
//        TenantClient tenantClient = new TenantClient();
//        ClientApplication application = new ClientApplication();
//        application.run();

//        clientApplication.run();

//        clientApplication.requestAllAvailableRentals();
//        clientApplication.requestAllRentals();
//        clientApplication.requestAllBelow(1600);
//            clientApplication.requestAllNumRoom(2);
//        clientApplication.requestApartmentOfRenter(2);
//        clientApplication.requestListAllTenant();
//        client.requestTenantOfRental(1);
//        client.requestNewRental(1);

        RentalClient client = new RentalClient();
        client.requestDeleteTenant(5);

    }
}
