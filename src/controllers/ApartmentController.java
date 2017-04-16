package controllers;

import daos.ApartmentDAO;
import models.Apartment;

import java.util.List;

/**
 * Created by dosontung on 4/9/17.
 */
public class ApartmentController extends Controller {
    private ApartmentDAO apartmentDAO;

    public ApartmentController() {
        super();
        this.apartmentDAO = this.daoFactory.getApartmentDAO();
    }

    public String requestAllApartmentOfRenter(int renterId) {
        List<Apartment> apartments = this.apartmentDAO.findByRenterId(renterId);
        if (apartments.size() == 0) {
            return "empty";
        }
        return renderResult(apartments);
    }

    public String requestAllAvailableApartmentOfRenter(int renterId) {
        List<Apartment> apartments = this.apartmentDAO.findAvailableByRenterId(renterId);
        return renderResult(apartments);
    }

    public String requestAllApartmentOfRenterForPropose(int renterId) {
        List<Apartment> apartments = this.apartmentDAO.findAvailableByRenterIdForPropose(renterId);
        if (apartments.size() == 0) {
            return "empty";
        }
        return renderResult(apartments);
    }
}
