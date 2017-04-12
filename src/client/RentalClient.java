package client;

import actions.RequestApartmentAction;
import actions.RequestPersonAction;
import actions.RequestRentalAction;

import java.util.Scanner;

/**
 * Created by caoquan on 4/5/17.
 */
public class RentalClient extends Client {
    Scanner sc;

    public RentalClient() {
        sc = new Scanner(System.in);
    }

    public void requestAllAvailableRentals() {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.ALL);
        doAction(requestRentalAction);
    }

    private String getRenterEmail() {
        String email = null;
        try {
            System.out.print("Please input renter email: ");
            email = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error: Please input an integer number");
        }
        return email;
    }


    public void requestApartmentOfRenter() {
        String email = getRenterEmail();
        RequestApartmentAction requestApartmentAction = new RequestApartmentAction(RequestApartmentAction.RENTER, email);
        doAction(requestApartmentAction);

    }

    public void requestTenantsOfRenter() {
        String email = getRenterEmail();
        RequestPersonAction requestPersonAction = new RequestPersonAction(RequestPersonAction.ALLTENANT, email);
        doAction(requestPersonAction);
    }

    public void requestDeleteTenant(int tenantId) {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.DELETERENTAL, tenantId);
        doAction(requestRentalAction);
    }

}
