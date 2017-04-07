package models;

import utils.PersonType;

/**
 * Created by caoquan on 4/4/17.
 */
public class Renter extends Person {
    public Renter(int id, String email) {
        super(id, email, PersonType.RENTER);
    }

    public Renter(String email) {
        super(email, PersonType.RENTER);
    }

    @Override
    public String toString() {
        return "RENTER{" +
                "id=" + this.getId() +
                ", email='" + this.getEmail() + '\'' +
                '}';
    }
}
