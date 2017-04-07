package models;

import utils.PersonType;

/**
 * Created by caoquan on 4/4/17.
 */
public class Tenant extends Person {
    public Tenant(int id, String email) {
        super(id, email, PersonType.TENANT);
    }

    public Tenant(String email) {
        super(email, PersonType.TENANT);
    }

    @Override
    public String toString() {
        return "TENANT{" +
                "id=" + this.getId() +
                ", email='" + this.getEmail() + '\'' +
                '}';
    }
}
