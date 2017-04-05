package client;

import actions.RequestRentalAction;

/**
 * Created by caoquan on 4/5/17.
 */
public class TestTenantClient {
    private TenantClient client;

    public TestTenantClient(){
        client = new TenantClient();
    }
    public void requestAllRentals() {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.ALL);
        client.doAction(requestRentalAction);
    }
}
