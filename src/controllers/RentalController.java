package controllers;

import daos.ApartmentDAO;
import daos.RentalDAO;
import models.Apartment;
import models.Person;
import models.Rental;
import utils.RentalStatus;

import java.util.List;

/**
 * Created by caoquan on 4/7/17.
 */
public class RentalController extends Controller {
    RentalDAO rentalDAO;
    ApartmentDAO apartmentDAO;

    public RentalController() {
        super();
        this.rentalDAO = this.daoFactory.getRentalDAO();
        this.apartmentDAO = this.daoFactory.getApartmentDAO();
    }

    public String requestAllAvailableRentals() {
        List<Rental> rentals = this.rentalDAO.findAllAvailable();
        return renderResult(rentals);
    }

    public String requestRentalBelowRent(int rent) {
        List<Rental> rentals = this.rentalDAO.findAllBelow(rent);
        return renderResult(rentals);
    }

    public String requestRenterByNumberOfRooms(int numberOfRooms) {
        List<Rental> rentals = this.rentalDAO.findAllNumberOfRoom(numberOfRooms);
        return renderResult(rentals);
    }

    public String requestTenantOfRental(int id) {
        List<Person> tenants = this.rentalDAO.findTenantOfRental(id);
        return renderResult(tenants);
    }

    public String requestNewRental(int apartmentId) {
        Apartment apartment = apartmentDAO.findById(apartmentId).get(0);
        Rental rental = new Rental(RentalStatus.AVAILABLE, apartment);
        this.rentalDAO.insertRental(rental);
        return "Created new rental";
    }

    public String requestDeleteRental(int rentalId) {
        Rental rental = this.rentalDAO.findById(rentalId).get(0);
        this.rentalDAO.deleteRental(rental);
        return "Rental Deleted";
    }

}
