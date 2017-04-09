package client;

/**
 * Created by caoquan on 4/8/17.
 */
public class ClientRentalHandler {
    private static ClientRentalHandler ourInstance = new ClientRentalHandler();

    public static ClientRentalHandler getInstance() {
        return ourInstance;
    }

    private ClientRentalHandler() {

    }
}
