package models;

import utils.RentalStatus;

/**
 * Created by caoquan on 4/4/17.
 */
public class Rental {
    private int id;
    private RentalStatus status;
    private Apartment apartment;
    private Person tenant;

    public Rental(Integer id, RentalStatus status, Apartment apartment, Person tenant) {

        this.id = id;
        this.status = status;
        this.apartment = apartment;
        this.tenant = tenant;
    }

    public Rental(Integer id, RentalStatus status, Apartment apartment) {
        this.id = id;
        this.status = status;
        this.apartment = apartment;
    }

    public Rental(RentalStatus status, Apartment apartment, Person tenant) {
        this.status = status;
        this.apartment = apartment;
        this.tenant = tenant;
    }

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


    public Integer getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", status=" + status +
                ", apartment=" + apartment +
                ", tenant=" + tenant +
                '}';
    }
}
