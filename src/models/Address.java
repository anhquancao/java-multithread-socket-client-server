package models;

/**
 * Created by caoquan on 4/4/17.
 */
public class Address {
    private int id;
    private String street;
    private int postalCode;

    public Address(int id, String street, int postalCode) {
        this.id = id;
        this.street = street;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + street + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
