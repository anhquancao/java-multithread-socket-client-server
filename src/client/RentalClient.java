package client;

import actions.*;
import models.Address;
import models.Apartment;
import utils.ApartmentType;

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

    public void addApartment() {
        try {
            System.out.print("Please input street: ");
            String street = sc.next();
            System.out.print("Please input postal code: ");
            int postalCode = Integer.parseInt(sc.next());
            System.out.print("Please input number of room: ");
            int numRooms = Integer.parseInt(sc.next());
            System.out.print("Please input monthly rent: ");
            int monthlyRent = Integer.parseInt(sc.next());
            int choice = 0;
            while (choice < 1 || choice > 4) {
                System.out.println("Please select type of apartment (1 to 4)");
                System.out.println("1. Duplex");
                System.out.println("2. Loft");
                System.out.println("3. Room");
                System.out.println("4. Other");
                System.out.print("Please input from 1 to 4: ");
                choice = Integer.parseInt(sc.next());
            }
            ApartmentType type = null;
            switch (choice) {
                case 1:
                    type = ApartmentType.DUPLEX;
                    break;
                case 2:
                    type = ApartmentType.LOFT;
                    break;
                case 3:
                    type = ApartmentType.ROOM;
                    break;
                case 4:
                    type = ApartmentType.OTHER;
                    break;
            }
            Address address = new Address(street, postalCode);
            Apartment apartment = new Apartment(address, numRooms, monthlyRent, ClientContext.getInstance().getLoggedInPerson(), type);
            Action action = new UpdateApartmentAction(UpdateApartmentAction.NEW, apartment.toCommandString());
            doAction(action);
        } catch (Exception e) {
            System.out.println("Error: Please input an integer number");
        }

    }
}
