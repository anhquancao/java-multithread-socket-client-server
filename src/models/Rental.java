package models;

import utils.RentalStatus;

/**
 * Created by caoquan on 4/4/17.
 */
public class Rental {
    private RentalStatus status;
    private Apartment apartment;
    private Person tenant;

    public Rental(RentalStatus status, Apartment apartment) {
        this.status = status;
        this.apartment = apartment;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public void setTenant(Person tenant) {
        this.tenant = tenant;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public Person getTenant() {
        return tenant;
    }
}
