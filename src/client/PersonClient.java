package client;

import actions.LoginAction;
import actions.RequestRentalAction;
import actions.UpdatePersonAction;
import models.Person;
import utils.PersonType;

/**
 * Created by caoquan on 4/5/17.
 */
public class PersonClient extends Client {

    public void requestAllAvailableRentals() {
        RequestRentalAction requestRentalAction = new RequestRentalAction(RequestRentalAction.ALL);
        doAction(requestRentalAction);
    }

    public boolean login() {
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
//            System.out.println(result);
            return false;
        }
    }

    public boolean createPerson() {
        String email = ClientHelper.getStringInput("Please input email: ", "Invalid email", this.sc);
        while (!ClientHelper.isValidEmailAddress(email)) {
            email = ClientHelper.getStringInput("Your email is invalid, please reinput email: ", "Invalid email", this.sc);
        }
        int typeChoice = -1;
        while (typeChoice != 1 && typeChoice != 2) {
            typeChoice = ClientHelper.getIntegerInput("Are you a Renter or Tenant?\n1. Renter \n2. Tenant\n3. Cancel" +
                            "\nPlease select 1, 2 or 3: ",
                    "Please input 1 or 2", this.sc);
            if (typeChoice != 1 && typeChoice != 2) {
                System.out.println("You must input 1 or 2");
            }
            if (typeChoice == 3) {
                return false;
            }
        }
        PersonType type = null;
        if (typeChoice == 1) {
            type = PersonType.RENTER;
        } else {
            type = PersonType.TENANT;
        }
        String password = ClientHelper.getStringInput("Please input your password: ", "Invalid password", this.sc);
        UpdatePersonAction updatePersonAction = new UpdatePersonAction(UpdatePersonAction.CREATE, email, type, password);
        String result = doAction(updatePersonAction);
        String[] parsedResult = result.split(" ");
        if (parsedResult[0].equals("success")) {
            int id = Integer.parseInt(parsedResult[1]);
            Person newPerson = new Person(id, email, type);
            ClientContext.getInstance().setLoggedInPerson(newPerson);
            System.out.println("Person created successfully");
            return true;
        } else {
//            System.out.println(result);
            return false;
        }

    }


}
