package client;

import actions.RequestApartmentAction;
import actions.RequestPersonAction;
import actions.RequestRentalAction;

/**
 * Created by caoquan on 4/5/17.
 */
public class TenantClient extends Client {

    public void requestAllAvailableRentals() {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.ALL);
        doAction(requestRentalAction);
    }

    public void requestAllBelow(int amount) {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.RENT, amount);
        doAction(requestRentalAction);
    }

    public void requestAllNumRoom(int num_rooms) {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.ROOM, num_rooms);
        doAction(requestRentalAction);
    }

    public void requestApartmentOfRenter(int renterId) {
        RequestApartmentAction requestApartmentAction = new RequestApartmentAction(RequestApartmentAction.RENTER, renterId);
        doAction(requestApartmentAction);
    }

    public void requestListAllTenant() {
        RequestPersonAction requestPersonAction = new RequestPersonAction(RequestPersonAction.ALLTENANT);
        doAction(requestPersonAction);
    }

    public void requestTenantOfRental(int rentalId) {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.TENANT, rentalId);
        doAction(requestRentalAction);
    }

    public void requestNewRental(int apartmentId) {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.NEWRENTAL, apartmentId);
        doAction(requestRentalAction);
    }
}
