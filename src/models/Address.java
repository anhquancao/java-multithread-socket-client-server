package models;

/**
 * Created by caoquan on 4/4/17.
 */
public class Address {
    private int id;
    private String address;
    private int postalCode;

    public Address(int id, String address, int postalCode) {
        this.id = id;
        this.address = address;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
