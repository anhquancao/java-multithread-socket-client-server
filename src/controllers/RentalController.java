package controllers;

import daofactory.DAOFactory;
import daofactory.DefaultDAOFactory;
import daos.RentalDAO;
import models.Rental;

import java.util.List;

/**
 * Created by caoquan on 4/7/17.
 */
public class RentalController extends Controllers {
    RentalDAO rentalDAO;

    public RentalController() {
        super();
        this.rentalDAO = this.daoFactory.getRentalDAO();
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
}
