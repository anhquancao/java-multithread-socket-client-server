package controllers;

import daofactory.DAOFactory;
import daofactory.DefaultDAOFactory;
import daos.RentalDAO;
import models.Rental;

import java.util.List;

/**
 * Created by caoquan on 4/7/17.
 */
public class RentalController {
    RentalDAO rentalDAO;
    private DAOFactory daoFactory;

    public RentalController() {
        this.daoFactory = DefaultDAOFactory.getInstance();
        this.rentalDAO = this.daoFactory.getRentalDAO();
    }

    public String requestAllAvailableRentals() {
        List<Rental> rentals = this.rentalDAO.findAllAvailable();
        StringBuilder results = new StringBuilder();
        for (Rental rental : rentals) {
            results.append(rental + "\n");
        }
        return results.toString();
    }

    public String requestRentalBelowRent(int rent) {
        List<Rental> rentals = this.rentalDAO.findAllBelow(rent);
        StringBuilder results = new StringBuilder();
        for (Rental rental : rentals) {
            results.append(rental + "\n");
        }
        return results.toString();
    }

    public String requestRenterByNumberOfRooms(int numberOfRooms) {
        return "number of rooms";
    }
}
