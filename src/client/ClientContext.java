package client;

import models.Person;

/**
 * Created by caoquan on 4/13/17.
 */
public class ClientContext {
    private Person loggedInPerson;

    private static ClientContext ourInstance = new ClientContext();

    public static ClientContext getInstance() {
        return ourInstance;
    }

    private ClientContext() {
    }

    public Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public void setLoggedInPerson(Person loggedInPerson) {
        this.loggedInPerson = loggedInPerson;
    }

    public static ClientContext getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(ClientContext ourInstance) {
        ClientContext.ourInstance = ourInstance;
    }

    public boolean isLoggedIn() {
        return this.loggedInPerson != null;
    }
}
