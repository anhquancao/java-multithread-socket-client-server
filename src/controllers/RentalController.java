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
        List<Rental> rentals = this.rentalDAO.findAll();
        StringBuilder results = new StringBuilder();
        for (Rental rental : rentals) {
            results.append(rental + "\n");
        }
        return results.toString();
    }

    public String requestRentalBelowRent(int rent) {
        return "below rent";
    }

    public String requestRenterByNumberOfRooms(int numberOfRooms) {
        return "number of rooms";
    }
}
