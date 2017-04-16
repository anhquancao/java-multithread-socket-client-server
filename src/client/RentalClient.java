package client;

import actions.Action;
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


    public void requestRentalOfRenter() {
        Action requestRentalAction = new RequestRentalAction(RequestRentalAction.RENTER, ClientContext.getInstance().getLoggedInPerson().getId());
        doAction(requestRentalAction);

    }

    public void requestTenantsOfRenter() {
        String email = getRenterEmail();
        Action requestPersonAction = new RequestPersonAction(RequestPersonAction.ALLTENANT, email);
        doAction(requestPersonAction);
    }

    public void requestDeleteTenant(int tenantId) {
        Action requestRentalAction = new RequestRentalAction(RequestRentalAction.DELETERENTAL, tenantId);
        doAction(requestRentalAction);
    }

}
