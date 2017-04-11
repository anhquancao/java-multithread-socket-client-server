package client;

import actions.RequestPersonAction;
import actions.RequestRentalAction;

import java.util.Scanner;

/**
 * Created by caoquan on 4/5/17.
 */
public class TenantClient extends Client {
    Scanner sc;

    public TenantClient() {
        sc = new Scanner(System.in);
    }

    public void requestAllAvailableRentals() {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.ALL);
        doAction(requestRentalAction);
    }

    public void requestAllMonthlyRentBelow() {

        try {
            System.out.print("Please input the amount of monthly rent: ");
            int amount = sc.nextInt();
            RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.RENT, amount);
            doAction(requestRentalAction);
        } catch (Exception e) {
            System.out.println("Error: Please input an integer number");
        }


    }

    public void requestAllAvailableByNumRoom() {
        try {
            System.out.print("Please input the number of rooms: ");
            int numRooms = sc.nextInt();
            RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.ROOM, numRooms);
            doAction(requestRentalAction);
        } catch (Exception e) {
            System.out.println("Error: Please input an integer number");
        }
    }


    public void requestListAllTenant() {
        RequestPersonAction requestPersonAction = new RequestPersonAction(RequestPersonAction.ALLTENANT);
        doAction(requestPersonAction);
    }

    public void requestTenantOfRental(int rentalId) {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.TENANT, rentalId);
        doAction(requestRentalAction);
    }


}
