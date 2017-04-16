package client;

import actions.*;

/**
 * Created by caoquan on 4/5/17.
 */
public class RentalClient extends Client {

    public void requestAllAvailableRentals() {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.ALL);
        doAction(requestRentalAction);
    }

    private String getRenterEmail() {
        return ClientHelper.getStringInput("Please input renter email: ",
                "Error: Please input a valid email", this.sc);
    }


    public void requestRentalOfRenter() {
        Action requestRentalAction = new RequestRentalAction(RequestRentalAction.RENTER, ClientContext.getInstance().getLoggedInPerson().getId());
        doAction(requestRentalAction);

    }

    public void requestTenantsOfRenter() {
        Action requestPersonAction = new RequestPersonAction(RequestPersonAction.ALLTENANT, ClientContext.getInstance().getLoggedInPerson().getId());
        doAction(requestPersonAction);
    }

    public void removeRental() {
        try {
            System.out.print("Please input the id of rental (you can get it from option 1): ");
            int rentalId = Integer.parseInt(sc.next());
            Action action = new UpdateRentalAction(UpdateRentalAction.DELETE_RENTAL, rentalId, ClientContext.getInstance().getLoggedInPerson().getId());
            doAction(action);
        } catch (Exception exception) {
            System.out.println("Error: Please input an integer number");
        }
    }

    public void proposeNewRental() {
        System.out.println("Your apartments that are not proposed:");
        Action getAllRenterApartmentsForProposed = new RequestApartmentAction(RequestApartmentAction.FOR_PROPOSE, ClientContext.getInstance().getLoggedInPerson().getId());
        String result = doAction(getAllRenterApartmentsForProposed);
        if (result.equals("empty")) {
            System.out.println("You dont have any apartment to propose, you need to add apartment first");
        } else {
            try {
                System.out.print("Please input the id of apartment that you want to propose: ");
                int apartmentId = Integer.parseInt(sc.next());
                Action action = new UpdateRentalAction(UpdateRentalAction.NEW_RENTAL, apartmentId, ClientContext.getInstance().getLoggedInPerson().getId());
                doAction(action);
            } catch (Exception exception) {
                System.out.println("Error: Please input an integer number");
            }

        }

    }
}
