package controllers;

import daos.ApartmentDAO;
import daos.RentalDAO;
import exceptions.RentalReservedException;
import models.Apartment;
import models.Person;
import models.Rental;
import utils.RentalStatus;

import java.util.Date;
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

    public String requestAllRentals() {
        List<Rental> rentals = this.rentalDAO.findAll();
        return renderResult(rentals);
    }

    public String requestAllAvailableRentals() {
        List<Rental> rentals = this.rentalDAO.findAllAvailable();
        return renderResult(rentals);
    }

    public String requestRentalsByRenter(int renterId) {
        List<Rental> rentals = this.rentalDAO.findByRenterId(renterId);
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

    public String requestNewRental(int apartmentId, int renterId) {
        List<Apartment> list = apartmentDAO.findById(apartmentId);
        if (list.size() == 0) {
            return "This Apartment is not existed";
        } else {
            Apartment apartment = list.get(0);
            if (apartment.getRenter().getId() == renterId) {
                Rental rental = new Rental(RentalStatus.AVAILABLE, apartment, new Person(renterId));
                this.rentalDAO.insertRental(rental);
                return "New rental created";
            } else {
                return "This apartment does not belong to you";
            }

        }

    }

    public String requestAdminDeleteRental(int rentalId, int renterId) {
        List<Rental> list = this.rentalDAO.findById(rentalId);
        if (list.size() == 0) {
            return "This Rental is not existed";
        } else {
            Rental rental = list.get(0);
            this.rentalDAO.deleteRental(rentalId);
            return "Rental deleted";
        }
    }

    public String requestDeleteRental(int rentalId, int renterId) {
        List<Rental> list = this.rentalDAO.findById(rentalId);
        if (list.size() == 0) {
            return "This Rental is not existed";
        } else {
            Rental rental = list.get(0);
            if (rental.getApartment().getRenter().getId() == renterId) {
                this.rentalDAO.deleteRental(rentalId);
                return "Rental deleted";
            } else {
                return "This Rental is not belong to you";
            }

        }
    }

    public String requestReserve(int rentalId, int tenantId, Date startDate, Date endDate) throws RentalReservedException {
        List<Rental> list = this.rentalDAO.findById(rentalId);
        if (list.size() == 0) {
            throw new RentalReservedException("This rental is not exist");
        }
        Rental rental = list.get(0);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);
        if (rental.getTenant() != null) {
            throw new RentalReservedException("This rental is not available for reserve");
        }
//        this.rentalDAO.reserveRental(rental, ClientContext.getInstance().getLoggedInPerson().getId());
        this.rentalDAO.reserveRental(rental, tenantId);
        return "Rental reserved successfully";
    }

}
