package models;

import utils.ApartmentType;

/**
 * Created by caoquan on 4/4/17.
 */
public class Apartment {
    private int id;
    private Address address;
    private int numRooms;
    private int monthlyRent;
    private Person renter;
    private ApartmentType type;

    public Apartment(int id, Address address, int numRooms, int monthlyRent, Person renter, ApartmentType type) {
        this.id = id;
        this.address = address;
        this.numRooms = numRooms;
        this.monthlyRent = monthlyRent;
        this.renter = renter;
        this.type = type;
    }

    public ApartmentType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public int getMonthlyRent() {
        return monthlyRent;
    }

    public Person getRenter() {
        return renter;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", address=" + address +
                ", numRooms=" + numRooms +
                ", monthlyRent=" + monthlyRent +
                ", renter=" + renter +
                ", type=" + type +
                '}';
    }
}
