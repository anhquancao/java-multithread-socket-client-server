package client;

import actions.RequestApartmentAction;
import actions.RequestPersonAction;
import actions.RequestRentalAction;

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
