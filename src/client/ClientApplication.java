package client;

import actions.RequestApartmentAction;
import actions.RequestRentalAction;
import jdk.nashorn.internal.ir.RuntimeNode;

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

    public void requestAllBelow(int amount) {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.RENT, amount);
        client.doAction(requestRentalAction);
    }

    public void requestAllNumRoom(int num_rooms){
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.ROOM, num_rooms);
        client.doAction(requestRentalAction);
    }

    public void requestApartmentOfRenter(int renterId){
        RequestApartmentAction requestApartmentAction = new RequestApartmentAction(RequestApartmentAction.RENTER, renterId);
        client.doAction(requestApartmentAction);
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
                    System.out.println("Tenant");
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
            clientApplication.requestApartmentOfRenter(2);
    }
}
