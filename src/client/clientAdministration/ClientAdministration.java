package client.clientAdministration;

import actions.*;
import client.Client;
import client.ClientContext;
import client.ClientHelper;
import models.Person;
import utils.Constant;
import utils.PersonType;

/**
 * Created by dosontung on 4/16/17.
 */
public class ClientAdministration extends Client {
    public ClientAdministration() {
        super();
        this.port = Constant.PORT_ADMINISTRATION;
    }

    public boolean adminLogin() {
        String email = ClientHelper.getStringInput("Please input email: ", "Invalid email", this.sc);
        String password = ClientHelper.getStringInput("Please input your password: ", "Invalid password", this.sc);
        LoginAction loginAction = new LoginAction(email, password);
        String result = doAction(loginAction);
        String[] parsedResult = result.split(" ");
        if (parsedResult[0].equals("success")) {
            int id = Integer.parseInt(parsedResult[1]);
            Person loggedInPerson = new Person(id, email, PersonType.valueOf(parsedResult[2]));
            ClientContext.getInstance().setLoggedInPerson(loggedInPerson);
            System.out.println("Login Successfully");
            return true;
        } else {
            return false;
        }
    }

    public void requestDeleteRental(int rentalId) {
        Action updateRentalAction = new UpdateRentalAction(UpdateRentalAction.DELETE_RENTAL, rentalId);
        doAction(updateRentalAction);
    }

    public void requestAllRental() {
        Action requestRentalAction = new RequestRentalAction(RequestRentalAction.ALL);
        doAction(requestRentalAction);
    }
}
