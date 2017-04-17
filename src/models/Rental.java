package models;

import utils.RentalStatus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by caoquan on 4/4/17.
 */
public class Rental {
    private int id;
    private RentalStatus status;
    private Apartment apartment;
    private Person tenant;
    private Date startDate;
    private Date endDate;

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

    public Rental(int id, RentalStatus status, Apartment apartment, Person tenant, Date startDate, Date endDate) {
        this.id = id;
        this.status = status;
        this.apartment = apartment;
        this.tenant = tenant;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString = "";
        String endDateString = "";
        if (startDate != null) {
            startDateString = df.format(startDate);
        }
        if (endDate != null) {
            endDateString = df.format(endDate);
        }
        return "Rental{" +
                "id=" + id +
                ", status=" + status +
                ", apartment=" + apartment +
                ", tenant=" + tenant +
                ", startDate=" + startDateString +
                ", endDate=" + endDateString +
                '}';
    }
}
